package ru.megazlo.flea.entity.support;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/** Created by iGurkin on 16.08.2016. */
@Getter
@Setter
@Embeddable
public class FullName {
	/** Фамилия */
	private String lastName;
	/** Имя */
	private String firstName;
	/** Отчество */
	private String middleName;
}
