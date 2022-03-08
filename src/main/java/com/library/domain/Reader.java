package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="READERS")
public class Reader {

    public Reader(String firstName, String lastName, LocalDateTime createDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createDate = createDate;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name="LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name="CREATE_DATE")
    private LocalDateTime createDate;

    /*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="RENT_ID")
    private Rent rent;*/

}
