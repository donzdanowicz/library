package com.library.mapper;

import com.library.domain.Title;
import com.library.domain.TitleDto;
import com.library.exception.CopyNotFoundException;
import com.library.repository.CopyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TitleMapper {
    private final CopyMapper copyMapper;

    public Title mapToTitle(final TitleDto titleDto) throws CopyNotFoundException {
        return Title.builder()
                .id(titleDto.getId())
                .title(titleDto.getTitle())
                .author(titleDto.getAuthor())
                .publicationYear(titleDto.getPublicationYear())
                //.copies(copyMapper.mapToCopies(titleDto.getCopyIds()))
                .build();
    }

    public TitleDto mapToTitleDto(final Title title) {
        return TitleDto.builder()
                .id(title.getId())
                .title(title.getTitle())
                .author(title.getAuthor())
                .publicationYear(title.getPublicationYear())
                //.copyIds(copyMapper.mapToCopyIds(title.getCopies()))
                .build();
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titles) {
        return titles.stream()
                .map(this::mapToTitleDto)
                .collect(Collectors.toList());
    }
}
