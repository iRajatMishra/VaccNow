package com.vaccnow.entity;

import com.vaccnow.enums.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder(builderClassName = "Builder", setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
@Where(clause = "deleted = false")
public class Customer {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    public Long id;

    @OneToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private Slot slot;

    @OneToOne
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "paymentMethod")
    private PaymentMethod paymentMethod;

    @Column(name = "applied")
    private Boolean applied;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

}
