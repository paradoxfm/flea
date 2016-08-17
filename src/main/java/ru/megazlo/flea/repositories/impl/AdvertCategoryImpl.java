package ru.megazlo.flea.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megazlo.flea.entity.AdvertCategory;
import ru.megazlo.flea.entity.AdvertCategoryTreePath;
import ru.megazlo.flea.repositories.custom.AdvertCategoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/** Created by iGurkin on 17.08.2016. */
@Repository
@Transactional
public class AdvertCategoryImpl implements AdvertCategoryCustom {

	@PersistenceContext
	private EntityManager em;

	public void insertRoot(AdvertCategory cat) {
		AdvertCategoryTreePath path = new AdvertCategoryTreePath();
		path.setChild(cat);
		path.setParent(cat);
		em.persist(path);
	}

	@Override
	public void insertCatTree(AdvertCategory cat) {
		/*
		insert into fl_advert_cat_tree (ancestor_id, descendant_id, level)
		select ancestor_id, :this_id, level + 1 from fl_advert_cat_tree
		where descendant_id = :parent_id
		union all select :this_id, :this_id, 0
*/
		final Query q = em.createQuery("insert into fl_advert_cat_tree ancestor_id, descendant_id, level values " +
				"select ancestor_id, :this_id, level + 1 from fl_advert_cat_tree where descendant_id = :parent_id union :this_id, :this_id, 0 ");

		q.setParameter("this_id", cat.getId());
		q.setParameter("parent_id", cat.getParent().getId());
		q.executeUpdate();
	}
}
