package ru.megazlo.flea.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by IGurkin on 16.05.2016.
 */
@Entity
@Table(name = "fl_advert")
public class Advert extends AbstractPersistable<Long> {

}
