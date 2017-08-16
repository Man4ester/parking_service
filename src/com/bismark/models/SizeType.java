package com.bismark.models;

public enum SizeType {
	SMALL(0), MEDIUM(1), BIG(2);

	private int weight;

	private SizeType(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

}
