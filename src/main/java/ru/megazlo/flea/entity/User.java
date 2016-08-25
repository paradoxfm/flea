package ru.megazlo.flea.entity;

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
import javax.validation.constraints.Pattern;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fl_users")
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners({AuditingEntityListener.class})
/*@FetchProfile(name = "usr-with-role", fetchOverrides = {
		@FetchProfile.FetchOverride(entity = User.class, association = "roles", mode = FetchMode.JOIN)
})*/
public class User extends AbstractPersistable<Long> {
	@Column(nullable = false, length = 100)
	@Length(min = 4, max = 14)
	private String login;
	@Column(length = 150)
	private String name;
	@Email
	@Column(nullable = false, length = 100)
	private String email;
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	@CreatedDate
	@Column(name = "register_date", nullable = false)
	private ZonedDateTime registerDate;
	@LastModifiedDate
	@Column(name = "modification_time")
	private ZonedDateTime modificationTime;
	@Column(name = "user_prone_num", length = 10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNumber;

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private Set<Card> created;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_cards", joinColumns = {
			@JoinColumn(name = "userId", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "cardId", nullable = false, updatable = false)})
	private Set<Card> cards;*/

	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = RoleEnum.class)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId"))
	@Column(name = "roleName", nullable = false)
	private Set<RoleEnum> roles;
}
