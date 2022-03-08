package com.library.controller;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;
import com.library.exception.ReaderNotFoundException;
import com.library.exception.RentNotFoundException;
import com.library.mapper.ReaderMapper;
import com.library.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/reader")
public class ReaderController {
    private final ReaderDbService readerDbService;
    private final ReaderMapper readerMapper;

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders() {
        List<Reader> readers = readerDbService.getReaders();
        return readerMapper.mapToReaderDtoList(readers);
    }

    @GetMapping(value = "getReader/{id}")
    public ReaderDto getReader(@PathVariable Long id) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(
                readerDbService.getReader(id).orElseThrow(ReaderNotFoundException::new)
        );
    }

    @DeleteMapping(value = "deleteReader/{id}")
    public void deleteReader(@PathVariable Long id) {
        readerDbService.deleteReader(id);
    }

    @PostMapping(value = "createReader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) throws RentNotFoundException {
        Reader reader = readerMapper.mapToReader(readerDto);
        readerDbService.saveReader(reader);
    }

    @PutMapping(value = "updateReader")
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto) throws RentNotFoundException {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader updatedReader = readerDbService.saveReader(reader);
        return readerMapper.mapToReaderDto(updatedReader);
    }

}