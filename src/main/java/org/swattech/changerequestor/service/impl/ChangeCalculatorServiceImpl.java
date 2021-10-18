package org.swattech.changerequestor.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swattech.changerequestor.exception.ChangeRequestorException;
import org.swattech.changerequestor.model.Coins;
import org.swattech.changerequestor.model.CoinsTray;
import org.swattech.changerequestor.model.Coins_Detail;
import org.swattech.changerequestor.repository.CoinRepository;
import org.swattech.changerequestor.service.ChangeCalculatorService;
import org.swattech.changerequestor.util.ChangeRequestorConstants;
import org.swattech.changerequestor.util.ChangeRequestorUtils;

@Component
public class ChangeCalculatorServiceImpl implements ChangeCalculatorService {

	@Autowired
	public CoinRepository coinRepository;

	@Override
	public CoinsTray calculateChangeForABill(double bill) throws ChangeRequestorException {

		boolean contains = ChangeRequestorConstants.availableBills.contains((int) bill);

		CoinsTray coinsTray = new CoinsTray();

		if (contains) {
			int quarters = 0;
			int dimes = 0;
			int nickels = 0;
			int pennies = 0;
			List<Coins_Detail> coinsAvailable = coinRepository.findAll();
			Map<Double, Integer> map = coinsAvailable.stream()
					.collect(Collectors.toMap(Coins_Detail::getCoinType, Coins_Detail::getCoinCount));
			System.out.println(map);
			double availableAmount = ChangeRequestorUtils.availableAmount(coinsAvailable);
			System.out.println(availableAmount);
			if (availableAmount < bill) {
				throw new ChangeRequestorException("Not enough coins available for " + bill + " 's  bill ");
			}
			System.out.println("bill :: "+bill);
			if ((int) map.get(ChangeRequestorConstants.QUARTER) > 0) {
				quarters = (int) (bill / 0.25);
				if (quarters > 0) {
					bill = bill % 0.25;
					System.out.println(quarters + " quarter coin(s)");
				}
			}
			System.out.println("bill :: "+bill);
			if ((int) map.get(ChangeRequestorConstants.DIME) > 0) {
				dimes = (int) (bill / 0.10);
				if (dimes > 0) {
					bill = bill % 0.10;
					System.out.println(dimes + " dime coin(s)");
				}
			}
			System.out.println("bill :: "+bill);
			if ((int) map.get(ChangeRequestorConstants.NICKEL) > 0) {
				nickels = (int) (bill / 0.05);
				if (nickels > 0) {
					bill = bill % 0.05;
					System.out.println(nickels + " nickel coin(s)");
				}
			}
			pennies = (int) bill;

			List<Coins> coins = coinsTray.getCoins();

			int updatedQuarters = ((int) map.get(ChangeRequestorConstants.QUARTER) - quarters);
			updatedQuarters = updatedQuarters > 0 ? updatedQuarters : 0;
			if (quarters != 0) {
				Coins quarter = new Coins(ChangeRequestorConstants.QUARTER, quarters);
				coins.add(quarter);
				coinRepository.updateCounts(ChangeRequestorConstants.QUARTER, updatedQuarters);
			}
			int updatedDimes = ((int) map.get(ChangeRequestorConstants.DIME) - dimes);
			updatedDimes = updatedDimes > 0 ? updatedDimes : 0;
			if (dimes != 0) {
				Coins dime = new Coins(ChangeRequestorConstants.DIME, dimes);
				coins.add(dime);
				coinRepository.updateCounts(ChangeRequestorConstants.DIME, updatedDimes);
			}
			int updatedNickels = ((int) map.get(ChangeRequestorConstants.NICKEL) - nickels);
			updatedNickels = updatedNickels > 0 ? updatedNickels : 0;
			if (nickels != 0) {
				Coins nickel = new Coins(ChangeRequestorConstants.NICKEL, nickels);
				coins.add(nickel);
				coinRepository.updateCounts(ChangeRequestorConstants.NICKEL, updatedNickels);
			}
			int updatedPennies = ((int) map.get(ChangeRequestorConstants.PENNY) - pennies);
			updatedPennies = updatedPennies > 0 ? updatedPennies : 0;
			if (pennies != 0) {
				Coins penny = new Coins(ChangeRequestorConstants.PENNY, pennies);
				coins.add(penny);
				coinRepository.updateCounts(ChangeRequestorConstants.PENNY, updatedPennies);
			}

			coinsTray.setCoins(coins);
		} else {
			throw new ChangeRequestorException("Bill of " + bill + " is not in available Bills ");
		}

		return coinsTray;
	}

}
