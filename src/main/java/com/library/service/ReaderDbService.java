package com.library.service;

import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReaderDbService {
    private final ReaderRepository readerRepository;

    public List<Reader> getReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReader(final Long id) {
        return readerRepository.findById(id);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public void deleteReader(final Long id) {
        readerRepository.deleteById(id);
    }
}
