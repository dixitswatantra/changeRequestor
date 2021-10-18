package org.swattech.changerequestor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "COINS_DETAIL")
@Table
public class Coins_Detail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@Column(name = "ID")
	private Long id;

	@Column(name = "COIN_TYPE", unique = true)
	private double coinType;

	@Column(name = "COIN_COUNT")
	private int coinCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCoinType() {
		return coinType;
	}

	public void setCoinType(double coinType) {
		this.coinType = coinType;
	}

	public int getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(int coinCount) {
		this.coinCount = coinCount;
	}

}
