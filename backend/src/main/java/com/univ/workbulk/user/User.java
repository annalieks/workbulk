package com.univ.workbulk.user;

import com.univ.workbulk.workgroup.Workgroup;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Workgroup> workgroups;

}
