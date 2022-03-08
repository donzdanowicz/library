package com.library.mapper;

import com.library.domain.Copy;
import com.library.domain.CopyDto;
import com.library.exception.CopyNotFoundException;
import com.library.exception.RentNotFoundException;
import com.library.exception.TitleNotFoundException;
import com.library.repository.CopyRepository;
import com.library.repository.RentRepository;
import com.library.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CopyMapper {
    private TitleRepository titleRepository;
    private RentRepository rentRepository;
    private CopyRepository copyRepository;

    public Copy mapToCopy(final CopyDto copyDto) throws TitleNotFoundException, RentNotFoundException {
        return Copy.builder()
                .id(copyDto.getId())
                .status(copyDto.getStatus())
                .title(titleRepository.findById(copyDto.getTitleId()).orElseThrow(TitleNotFoundException::new))
                //.rent(rentRepository.findById(copyDto.getRentId()).orElseThrow(RentNotFoundException::new))
                .build();
    }

    public CopyDto mapToCopyDto(final Copy copy) {
        return CopyDto.builder()
                .id(copy.getId())
                .status(copy.getStatus())
                //.rentId(copy.getRent().getId())
                .titleId(copy.getTitle().getId())
                .build();
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copies) {
        return copies.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }

    public List<Copy> mapToCopies(final List<Long> copyIds) throws CopyNotFoundException {
        List<Copy> copies = new ArrayList<>();

        if(copyIds.size() > 0) {
            for(Long copyId : copyIds) {
                Copy copy = copyRepository.findById(copyId).orElseThrow(CopyNotFoundException::new);
                copies.add(copy);
            }
        }
        return copies;
    }

    public List<Long> mapToCopyIds(final List<Copy> copies) {
        List<Long> copyIds = new ArrayList<>();

        if(copies.size() > 0) {
            copyIds = copies.stream()
                    .map(Copy::getId)
                    .collect(Collectors.toList());
        }
        return copyIds;
    }

}
