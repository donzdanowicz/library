package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="COPIES")
public class Copy {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="STATUS")
    private Status status;

    @ManyToOne
    @JoinColumn(name="TITLE_ID")
    private Title title;

    @ManyToOne
    @JoinColumn(name="RENT_ID")
    private Rent rent;
}
