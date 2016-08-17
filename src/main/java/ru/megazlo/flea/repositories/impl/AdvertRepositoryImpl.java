package ru.megazlo.flea.repositories.impl;

import org.springframework.stereotype.Repository;
import ru.megazlo.flea.entity.Advert;
import ru.megazlo.flea.entity.AdvertCategory;
import ru.megazlo.flea.entity.support.ClosureTableTreeNode;
import ru.megazlo.flea.model.AdvertSerach;
import ru.megazlo.flea.repositories.custom.AdvertCustom;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings({"unchecked", "SqlNoDataSourceInspection", "SqlResolve"})
public class AdvertRepositoryImpl implements AdvertCustom {

	private final static int page_limit = 30;
	private final static int UNDEFINED_POSITION = -1;

	@PersistenceContext
	private EntityManager em;


	public List<AdvertSerach> findByCustomQuery() {
		//final Query q = em.createNamedStoredProcedureQuery("searchAdvert");
		final StoredProcedureQuery q = em.createStoredProcedureQuery("search_advert", AdvertSerach.class);
		q.registerStoredProcedureParameter(0, String.class, ParameterMode.IN);
		q.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
		q.setParameter(0, "qweq");
		return q.getResultList();
	}

	public List<Advert> findTopLastAdvert() {
		return null;
	}

	@Override
	public List<Advert> findAdvert(String textSearch) {
		final Query q = em.createNativeQuery("select * from fl_advert ad, to_tsquery(:qr) quer " +
				"where ad.ftv @@ quer limit :lim;");
		q.setParameter("qr", textSearch);
		q.setParameter("lim", page_limit);
		return q.getResultList();
	}
}
