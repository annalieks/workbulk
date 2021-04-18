package com.univ.workbulk.board;

import com.univ.workbulk.column.BoardColumn;
import com.univ.workbulk.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table
public class Board {

    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardColumn> columns;

}
