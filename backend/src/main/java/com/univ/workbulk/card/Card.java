package com.univ.workbulk.card;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table
public class Card {

    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String text;

}
