package com.platifon.mycards.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author paradoxfm - 21.01.2016
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners({AuditingEntityListener.class})
public class User extends AbstractPersistable<Long> {
    @Column
    @Length(min = 4, max = 14)
    private String login;
    @Column
    private String name;
    @Email
    @Column(nullable = false)
    private String email;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @CreatedDate
    @Column(name = "creation_time")
    private ZonedDateTime createDate;
    @LastModifiedDate
    @Column(name = "modification_time")
    private ZonedDateTime modificationTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Card> created;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_cards", joinColumns = {
            @JoinColumn(name = "userId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "cardId", nullable = false, updatable = false)})
    private Set<Card> cards;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoleEnum.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId"))
    @Column(name = "roleName", nullable = false)
    private Set<RoleEnum> roles;
}
