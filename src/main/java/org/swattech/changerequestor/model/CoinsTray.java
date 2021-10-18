package org.swattech.changerequestor.model;

import java.util.ArrayList;
import java.util.List;

public class CoinsTray {

	public List<Coins> coins = null;

	public List<Coins> getCoins() {
		if (coins == null) {
			return coins = new ArrayList<Coins>();
		}
		return coins;
	}

	public void setCoins(List<Coins> coins) {
		this.coins = coins;
	}

}
