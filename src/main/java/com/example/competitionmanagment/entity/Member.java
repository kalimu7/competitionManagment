package com.example.competitionmanagment.entity;
import com.brief.cmanagment.enums.identiyDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality ;
    private identiyDocumentType identity;
    private String identityNumber;

    @ManyToMany
    @JoinTable(
            name = "Rank",
            joinColumns = @JoinColumn(name = "memeberNum"),
            inverseJoinColumns = @JoinColumn(name = "competitionCode")
    )
    private List<Competition> competitions;

    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;


}
