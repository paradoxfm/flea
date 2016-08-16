package ru.megazlo.flea.entity.enums;

/** Created by iGurkin on 16.08.2016. */
public enum AddressType implements IValueEnum<Integer> {

	/** Адрес регистрации */
	REG(0),
	/** Адрес фактического проживания */
	FACT(1),
	/** Временный адрес */
	TEMP(2);

	private Integer val;

	AddressType(Integer val) {
		this.val = val;
	}

	@Override
	public Integer getVal() {
		return val;
	}
}
