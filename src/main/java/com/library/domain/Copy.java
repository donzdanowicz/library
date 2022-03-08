package com.library.domain;

import lombok.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(
        name = "Copy.howManyCopiesOfGivenTitleAvailableToRent",
        query = "SELECT COUNT(*) FROM COPIES" +
                " WHERE STATUS = \"AT_LIBRARY\" AND TITLE_ID = :TITLE_ID",
        resultClass = Copy.class
)

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="COPIES")
public class Copy {

    /*public Copy(Status status) {
        this.status = status;
    }*/

    public Copy(String status) {
        this.status = status;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="STATUS")
    //private Status status;
    private String status;

    @ManyToOne
    @JoinColumn(name="TITLE_ID")
    private Title title;

    @OneToOne
    @JoinColumn(name="RENT_ID")
    private Rent rent;
}
