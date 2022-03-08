package com.library.mapper;

import com.library.domain.Rent;
import com.library.domain.RentDto;
import com.library.exception.CopyNotFoundException;
import com.library.exception.ReaderNotFoundException;
import com.library.repository.CopyRepository;
import com.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentMapper {
    private CopyRepository copyRepository;
    private ReaderRepository readerRepository;

    public Rent mapToRent(final RentDto rentDto) throws CopyNotFoundException, ReaderNotFoundException {
        return Rent.builder()
                .id(rentDto.getId())
                .rentDate(rentDto.getRentDate())
                .returnDate(rentDto.getReturnDate())
                .copy(copyRepository.findById(rentDto.getCopyId()).orElseThrow(CopyNotFoundException::new))
                .reader(readerRepository.findById(rentDto.getReaderId()).orElseThrow(ReaderNotFoundException::new))
                .build();
    }

    public RentDto mapToRentDto(final Rent rent) {
        return RentDto.builder()
                .id(rent.getId())
                .rentDate(rent.getRentDate())
                .returnDate(rent.getReturnDate())
                .copyId(rent.getCopy().getId())
                .readerId(rent.getReader().getId())
                .build();
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rents) {
        return rents.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }

}
