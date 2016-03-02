package com.platifon.mycards.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * @author paradoxfm - 02.03.2016
 */
@Entity
@Getter
@Setter
@Table(name = "card_field")
public class CardField extends AbstractPersistable<Long> {

    private String title;

    private String value;

    private Float posTop;

    private Float posLeft;

    private Integer color;

    private Integer fontSize;

    private String fontName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}
