package org.swattech.changerequestor.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ChangeRequestorConstants {

	public static final double DIME = 0.10;
	public static final double NICKEL = 0.05;
	public static final double QUARTER = 0.25;
	public static final double PENNY = 0.01;

	// Available bills are (1, 2, 5, 10, 20, 50, 100)
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int FIVE = 5;
	public static final int TEN = 10;
	public static final int TWENTY = 20;
	public static final int FIFTY = 50;
	public static final int HUNDRED = 100;

	public static final List<Integer> availableBills = Collections.unmodifiableList(new ArrayList<Integer>() {
		{
			add(ONE);
			add(TWO);
			add(FIVE);
			add(TEN);
			add(TWENTY);
			add(FIFTY);
			add(HUNDRED);
		}
	});

	public static final SortedSet<Double> availableCoins = Collections.unmodifiableSortedSet(new TreeSet<Double>() {
		{
			add(PENNY);
			add(NICKEL);
			add(DIME);
			add(QUARTER);
		}
	});

}
