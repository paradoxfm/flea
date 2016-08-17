package ru.megazlo.flea.entity.support;

/** Created by iGurkin on 17.08.2016. */
public interface TreePath {
	/**
	 One of the ancestor tree nodes of the descendant, or the descendant itself.
	 The private Java property name for this MUST BE "ancestor" in any implementation,
	 as that name is used in DAO queries.
	 */
	ClosureTableTreeNode getParent();

	void setParent(ClosureTableTreeNode parent);

	/**
	 The (descendant) tree node (of the ancestor).
	 The private Java property name for this MUST BE "descendant" in any implementation,
	 as that name is used in DAO queries.
	 */
	ClosureTableTreeNode getChild();

	void setChild(ClosureTableTreeNode child);

	/**
	 The 0-n level this descendant tree node occurs, 0 is self-reference.
	 The private Java property name for this MUST BE "depth" in any implementation,
	 as that name is used in DAO queries.
	 */
	int getLevel();

	void setLevel(int level);

	/**
	 The 0-n child position this descendant occurs at.
	 The private Java property name for this MUST BE "orderIndex" in any implementation,
	 as that name is used in DAO queries.
	 */
	int getOrderIndex();

	void setOrderIndex(int orderIndex);
}
