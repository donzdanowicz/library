package com.library.repository;

import com.library.domain.Copy;
import com.library.domain.Reader;
import com.library.domain.Rent;
import com.library.domain.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.library.domain.Status.AT_LIBRARY;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class LibraryRepositoriesTestSuite {

    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private TitleRepository titleRepository;
    private Title title;
    private Rent rent;
    private Reader reader;
    private Copy copy;
    private List<Copy> copies = new ArrayList<>();

        @Test
        void testCopyRepository() {
            //Given
            Title title = new Title(1L, "Gunslinger", "Stephen King", 1982);
            Rent rent = new Rent(1L, LocalDateTime.now(), LocalDateTime.now(), reader);
            Reader reader = new Reader(1L, "John", "Wilkes", LocalDateTime.now(), rent);
            Copy copy = new Copy(1L, AT_LIBRARY, title, rent);

            //When
            copyRepository.save(copy);

            //Then
            long id = copy.getId();
            Optional<Copy> readCopy = copyRepository.findById(id);
            assertTrue(readCopy.isPresent());

            //CleanUp
            copyRepository.deleteById(id);
        }

}
