package ru.megazlo.flea.entity;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * Created by IGurkin on 16.05.2016.
 */
public abstract class BasePersistable<PK extends Serializable> implements Persistable<PK> {
}
