package ru.megazlo.flea.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "fl_advert")
public class Advert extends AbstractPersistable<Long> {

	private Boolean enabled;

	private Date dateCreate;

	private Date dateEdit;

	private String title;

	private String text;

	private User owner;

}
