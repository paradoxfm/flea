package ru.megazlo.flea.entity.support;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 Created by iGurkin on 17.08.2016.
 Класс ключа для паттерна ClosureTable
 */
@Getter
@Setter
public class CompositeAdvertCategoryTreePathId implements Serializable {

	/** Доолжен иметь такое же имя как в имплиментации */
	private Long parent;
	/** Доолжен иметь такое же имя как в имплиментации */
	private Long child;

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof CompositeAdvertCategoryTreePathId)) {
			return false;
		}
		final CompositeAdvertCategoryTreePathId other = (CompositeAdvertCategoryTreePathId) o;
		return other.getChild().equals(getChild()) && other.getParent().equals(getParent());
	}

	@Override
	public int hashCode() {
		return getChild().hashCode() * 31 + getParent().hashCode();// потому что 42 ))
	}
}
