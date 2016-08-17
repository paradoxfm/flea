package ru.megazlo.flea.entity.support;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.AbstractPersistable;
import ru.megazlo.flea.entity.enums.AddressType;
import ru.megazlo.flea.entity.enums.AddressTypeHib;

import javax.persistence.Column;
import javax.persistence.Table;

/** Created by iGurkin on 16.08.2016. */
@Getter
@Setter
@Table(name = "fl_address")
@TypeDef(name = "addressTypeHib", typeClass = AddressTypeHib.class)
public class Address extends AbstractPersistable<Long> {

	@Column(name = "type")
	@Type(type = "addressTypeHib")
	private AddressType type;
	/** Почтовый индекс */
	@Column(name = "zip")
	private String zip;
	/** Код по КЛАДР */
	@Column(name = "kladr")
	private String kladr;
	/** Код по ФИАС */
	@Column(name = "fias")
	private String fias;
	/** Страна */
	@Column(name = "country")
	private String country;
	/** Регион */
	@Column(name = "region")
	private String region;
	/** Район */
	@Column(name = "area")
	private String area;
	/** Город */
	@Column(name = "city")
	private String city;
	/** Населенный пункт */
	@Column(name = "nas_punkt")
	private String naspunkt;
	/** Улица */
	@Column(name = "street")
	private String street;
	/** Нормер дома (не понятно зачем если есть фиас) */
	@Column(name = "building")
	private Integer building;
	/** Корпус */
	@Column(name = "korp")
	private String korp;
	/** Строение */
	@Column(name = "build")
	private String build;
	/** Нормер квартиры */
	@Column(name = "flat")
	private Integer flat;
	/** Комментарий */
	@Column(name = "comment")
	private String comment;
}
