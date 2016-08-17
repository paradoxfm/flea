package ru.megazlo.flea.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import ru.megazlo.flea.entity.support.ClosureTableTreeNode;
import ru.megazlo.flea.entity.support.CompositeAdvertCategoryTreePathId;
import ru.megazlo.flea.entity.support.TreePath;

import javax.persistence.*;

/** Created by iGurkin on 17.08.2016. */
@Getter
@Setter
@Entity
@Table(name = "fl_advert_cat_tree")
@IdClass(CompositeAdvertCategoryTreePathId.class)
public class AdvertCategoryTreePath extends AbstractPersistable<Long> implements TreePath {
	@Id
	@ManyToOne(targetEntity = AdvertCategory.class)
	@JoinColumn(name = "ancestor_id", nullable = false)
	private ClosureTableTreeNode parent;

	@Id
	@ManyToOne(targetEntity = AdvertCategory.class)
	@JoinColumn(name = "descendant_id", nullable = false)
	private ClosureTableTreeNode child;

	@Column(name = "level", nullable = false)
	private int level;

	@Column(name = "order")
	private int orderIndex;
}
