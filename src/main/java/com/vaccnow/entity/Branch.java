package com.vaccnow.entity;

import com.vaccnow.util.TimeSlots;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder(builderClassName = "Builder", setterPrefix = "with")
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "branches")
@Where(clause = "deleted = false")
public class Branch {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    public Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Vaccine> vaccines = new ArrayList<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Slot> slots = new ArrayList<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Customer> customers = new ArrayList<>();

    public Branch() throws ParseException {
        TimeSlots timeSlots = new TimeSlots();
        slots = timeSlots.getSlots(LocalDate.now().toString(), LocalDate.now().toString());
    }
}
