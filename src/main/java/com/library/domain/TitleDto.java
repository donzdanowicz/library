package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    //private List<Long> copyIds;
}
