package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TITLES")
public class Title {

    public Title(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="TITLE")
    private String title;

    @NotNull
    @Column(name="AUTHOR")
    private String author;

    @NotNull
    @Column(name="PUBLICATION_YEAR")
    private int publicationYear;

    @OneToMany(
            targetEntity = Copy.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Copy> copies;
}
