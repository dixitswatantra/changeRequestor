package org.swattech.changerequestor.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author swdixit
 *
 */
public final class Coins {

	public Coins(double coin, int noOfCoins) {
		super();
		this.coin = coin;
		this.noOfCoins = noOfCoins;
	}

	private double coin = 0;

	public void setCoin(double coin) {
		this.coin = coin;
	}

	public void setNoOfCoins(int noOfCoins) {
		this.noOfCoins = noOfCoins;
	}

	public double getCoin() {
		return coin;
	}

	public int getNoOfCoins() {
		return noOfCoins;
	}

	private int noOfCoins = 0;

}
