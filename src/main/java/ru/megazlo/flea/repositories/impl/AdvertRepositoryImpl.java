package ru.megazlo.flea.repositories.impl;

import org.springframework.stereotype.Repository;
import ru.megazlo.flea.model.AdvertSerach;
import ru.megazlo.flea.repositories.AdvertCustom;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class AdvertRepositoryImpl implements AdvertCustom {

	@PersistenceContext
	private EntityManager em;

	public List<AdvertSerach> findByCustomQuery() {
		//final Query q = em.createNamedStoredProcedureQuery("searchAdvert");
		final StoredProcedureQuery q = em.createStoredProcedureQuery("search_advert", AdvertSerach.class);
		q.registerStoredProcedureParameter(0 , String.class , ParameterMode.IN);
		q.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
		q.setParameter(0, "qweq");
		return q.getResultList();
	}
}
