package ru.megazlo.flea.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author iv - 24.01.2016
 */
@Entity
@Getter
@Setter
@Table(name = "persistent_logins")
// эта сущность нужна для хранения бехопасности spring, в реальности нигде не используется
public class PersistentRememberMeTokenDb {

    @Id
    @Column(length = 64)
    private String series;
    @Column(length = 64)
    private String username;
    @Column(length = 64)
    private String token;
    @Column
    private LocalDateTime last_used;
}
