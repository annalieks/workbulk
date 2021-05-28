package com.univ.workbulk.column;

import com.univ.workbulk.card.Card;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table
public class BoardColumn {

    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Card> cards;

}
