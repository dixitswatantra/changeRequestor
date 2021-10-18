package org.swattech.changerequestor.util;

import java.util.List;

import org.swattech.changerequestor.model.Coins_Detail;

public class ChangeRequestorUtils {

	public static double availableAmount(List<Coins_Detail> coinsDetails) {
		double availableAmount = 0;
		for (Coins_Detail c : coinsDetails) {
			availableAmount = availableAmount + (c.getCoinType() * c.getCoinCount());
		}
		return availableAmount;
	}

}
