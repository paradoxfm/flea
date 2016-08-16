package ru.megazlo.flea.entity.enums;

/** Created by iGurkin on 16.08.2016. */
interface IValueEnum<K> {
	/** Получить значение представления enum`а в базе */
	K getVal();
}
