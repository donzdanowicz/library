package com.library.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(
        name = "Copy.howManyCopiesOfGivenTitleAvailableToRent",
        query = "SELECT COUNT(*) FROM COPIES" +
                " WHERE STATUS = \"AT_LIBRARY\" AND TITLE_ID = :TITLE_ID"
)

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="COPIES")
public class Copy {

    public Copy(Status status) {
        this.status = status;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private Status status;

    @ManyToOne
    @JoinColumn(name="TITLE_ID")
    private Title title;

    @OneToOne
    @JoinColumn(name="RENT_ID")
    private Rent rent;
}
