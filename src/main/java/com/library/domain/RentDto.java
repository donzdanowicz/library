package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RentDto {
    private Long id;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
    private Long copyId;
    private Long readerId;
}
