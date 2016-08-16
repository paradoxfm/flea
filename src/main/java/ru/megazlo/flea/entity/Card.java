package ru.megazlo.flea.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author paradoxfm - 21.01.2016
 */
//@Entity
@Getter
@Setter
//@Table(name = "cards")
//@EntityListeners({AuditingEntityListener.class})
public class Card extends AbstractPersistable<Long> {

    @Column
    private String name;
    @Column
    private String comment;

    @CreatedDate
	@Column(name = "creation_time")
	private ZonedDateTime createDate;
	@LastModifiedDate
	@Column(name = "modification_time")
	private ZonedDateTime modificationTime;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cards")
    private Set<User> users;
}
