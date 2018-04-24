package com.castle.entity;

public enum CabType {

	DEFAULT("default"), X("x"), GO("go");

	private CabType(String value) {
		this.value = value;
	}

	public String value;

	public String getValue() {
		return value;
	}
}
