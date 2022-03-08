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
public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime createDate;
    //private Long rentId;
}
