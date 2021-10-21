package com.univ.workbulk.board;

import com.univ.workbulk.column.BoardColumn;
import com.univ.workbulk.workgroup.Workgroup;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="workgroup_id")
    Workgroup workgroup;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardColumn> columns = new ArrayList<>();

}
