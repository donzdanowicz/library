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
@Table(name="RENTS")
public class Rent {

    public Rent(LocalDateTime rentDate, LocalDateTime returnDate) {
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="RENT_DATE")
    private LocalDateTime rentDate;

    @Column(name="RETURN_DATE")
    private LocalDateTime returnDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="COPY_ID")
    private Copy copy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="READER_ID")
    private Reader reader;
}
