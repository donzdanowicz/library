package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;
import com.library.exception.RentNotFoundException;
import com.library.repository.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReaderMapper {
    private RentRepository rentRepository;

    public Reader mapToReader(final ReaderDto readerDto) throws RentNotFoundException {
        return Reader.builder()
                .id(readerDto.getId())
                .firstName(readerDto.getFirstName())
                .lastName(readerDto.getLastName())
                .createDate(readerDto.getCreateDate())
                //.rent(rentRepository.findById(readerDto.getRentId()).orElseThrow(RentNotFoundException::new))
                .build();
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return ReaderDto.builder()
                .id(reader.getId())
                .firstName(reader.getFirstName())
                .lastName(reader.getLastName())
                .createDate(reader.getCreateDate())
                //.rentId(reader.getRent().getId())
                .build();
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readers) {
        return readers.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }
}
