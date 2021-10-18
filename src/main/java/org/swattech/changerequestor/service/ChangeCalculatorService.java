package org.swattech.changerequestor.service;

import org.springframework.stereotype.Service;
import org.swattech.changerequestor.exception.ChangeRequestorException;
import org.swattech.changerequestor.model.CoinsTray;

@Service
public interface ChangeCalculatorService {

	CoinsTray calculateChangeForABill(double bill) throws ChangeRequestorException;

//	void addCoins(List<Coins_Detail> coinsDetails);
//
//	List<Coins_Detail> findAvailableCoins();

}
