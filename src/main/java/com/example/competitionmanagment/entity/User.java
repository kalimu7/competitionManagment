package com.example.competitionmanagment.entity;



import com.example.competitionmanagment.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_accounts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String password;
    @Column(unique = true)
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Roles role;
    @Column(unique = true)
    private String identityNumber;

//    private int num;
//    private String name;
//    private String familyName;
//    private LocalDate accessionDate;
//    private String nationality ;
//    @Enumerated(EnumType.STRING)
//    private identiyDocumentType identity;
//    @Column(unique = true)
//    private String identityNumber;




    @ManyToMany
    @JoinTable(
            name = "Rank",
            joinColumns = @JoinColumn(name = "memeberNum"),
            inverseJoinColumns = @JoinColumn(name = "competitionCode")
    )
    private List<Competition> competitions;

    @OneToMany(mappedBy = "user")
    private List<Hunting> huntings;


}
