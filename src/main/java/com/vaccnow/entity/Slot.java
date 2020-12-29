package com.vaccnow.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Builder(builderClassName = "Builder", setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "slot")
@Where(clause = "deleted = false")
public class Slot {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    public Long id;

    @Column(name = "timeFrame")
    private Date slot;

    @Column(name = "isBooked")
    private boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

}
