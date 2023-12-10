package com.example.competitionmanagment.entity;



import com.example.competitionmanagment.enums.identiyDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
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
    @FutureOrPresent(message = "date shouldnt be passed")
    private LocalDate accessionDate;
    private String nationality ;
    @Enumerated(EnumType.STRING)
    private identiyDocumentType identity;
    @Column(unique = true)
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
