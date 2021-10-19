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
	public CoinsTray calculateChangeForABill(double bill, boolean minCoins) throws ChangeRequestorException {

		boolean contains = ChangeRequestorConstants.availableBills.contains((int) bill);

		CoinsTray coinsTray = new CoinsTray();

		if (contains) {
			List<Coins_Detail> coinsAvailable = coinRepository.findAll();
			// Map to have type of coins as Key and no of coins as Values
			Map<Double, Integer> map = coinsAvailable.stream()
					.collect(Collectors.toMap(Coins_Detail::getCoinType, Coins_Detail::getCoinCount));
			// Total amount based on available coins
			double availableAmount = ChangeRequestorUtils.availableAmount(coinsAvailable);
			if (availableAmount < bill) {
				throw new ChangeRequestorException("Not enough coins available for " + bill + " 's  bill ");
			}
			List<Coins> coins = coinsTray.getCoins();
			// Retrieved values of each type of Coins
			int availableQuarters = map.get(ChangeRequestorConstants.QUARTER);
			int availableDimes = map.get(ChangeRequestorConstants.DIME);
			int availableNickels = map.get(ChangeRequestorConstants.NICKEL);
			int availablePennies = map.get(ChangeRequestorConstants.PENNY);

			if (minCoins) {
				bill = getChange(bill, coins, availableQuarters, ChangeRequestorConstants.QUARTER);
				bill = getChange(bill, coins, availableDimes, ChangeRequestorConstants.DIME);
				bill = getChange(bill, coins, availableNickels, ChangeRequestorConstants.NICKEL);
				bill = getChange(bill, coins, availablePennies, ChangeRequestorConstants.PENNY);
			} else {
				bill = getChange(bill, coins, availablePennies, ChangeRequestorConstants.PENNY);
				bill = getChange(bill, coins, availableNickels, ChangeRequestorConstants.NICKEL);
				bill = getChange(bill, coins, availableDimes, ChangeRequestorConstants.DIME);
				bill = getChange(bill, coins, availableQuarters, ChangeRequestorConstants.QUARTER);

			}
			coinsTray.setCoins(coins);
		} else {
			throw new ChangeRequestorException("Bill of " + bill + " is not in available Bills ");
		}

		return coinsTray;
	}

	/**
	 * get change
	 * 
	 * @param bill
	 * @param coins
	 * @param availableQuarters
	 * @param type
	 * @return remaining bill's value
	 */
	private double getChange(double bill, List<Coins> coins, int availableQuarters, double type) {
		int quarters;
		if (availableQuarters > 0) {
			quarters = (int) (bill / type);
			int updatedQuarters = (availableQuarters - quarters);
			if (quarters != 0) {
				if (updatedQuarters <= 0) {
					quarters = availableQuarters;
					updatedQuarters = 0;
				}
				bill = bill - (type * Double.valueOf(quarters));
				Coins quarter = new Coins(type, quarters);
				coins.add(quarter);
				if (quarters >= 0)
					coinRepository.updateCounts(type, updatedQuarters);
			}
		}
		return bill;
	}

}
