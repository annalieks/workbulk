package com.univ.workbulk.workgroup;

import com.univ.workbulk.board.Board;
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
public class Workgroup {

    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Board> boards;

}
