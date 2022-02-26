package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="RENTS")
public class Rent {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="RENT_DATE")
    private LocalDateTime rentDate;

    @NotNull
    @Column(name="RETURN_DATE")
    private LocalDateTime returnDate;

    @OneToMany(
            targetEntity = Copy.class,
            mappedBy = "rent",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private final List<Copy> copies = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="READER_ID")
    private Reader reader;
}
