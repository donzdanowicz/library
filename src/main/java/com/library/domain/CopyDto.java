package com.library.domain;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CopyDto {
    private Long id;
    //private String status;
    private Status status;
    private Long titleId;
    //private Long rentId;
}
