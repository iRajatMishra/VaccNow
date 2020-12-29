package com.vaccnow.entity;


import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder(builderClassName = "Builder", setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vaccines")
@Where(clause = "deleted = false")
public class Vaccine {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    public Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

}
