package ru.megazlo.flea.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import ru.megazlo.flea.entity.support.ClosureTableTreeNode;

import javax.persistence.*;

/** Created by iGurkin on 17.08.2016. */
@Getter
@Setter
@Entity
@Table(name = "fl_advert_cat")
public class AdvertCategory extends AbstractPersistable<Long> implements ClosureTableTreeNode {

	@Column(name = "title")
	private String title;

	@Column(name = "active")
	private boolean active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private AdvertCategory parent;
}
