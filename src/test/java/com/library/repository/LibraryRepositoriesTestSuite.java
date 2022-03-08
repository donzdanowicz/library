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
import java.util.Optional;
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


    @Test
    void testCopyRepository() {
        //Given
        Copy copy = new Copy("AT_LIBRARY");

        //When
        copyRepository.save(copy);

        //Then
        long id = copy.getId();
        Optional<Copy> readCopy = copyRepository.findById(id);
        System.out.println(readCopy);
        assertTrue(readCopy.isPresent());

        //CleanUp
        copyRepository.deleteById(id);
    }

    @Test
    void testReaderRepository() {
        //Given
        Reader reader = new Reader("John", "Wilkes", LocalDateTime.now());

        //When
        readerRepository.save(reader);

        //Then
        long id = reader.getId();
        Optional<Reader> readReader = readerRepository.findById(id);
        assertTrue(readReader.isPresent());

        //CleanUp
        readerRepository.deleteById(id);
    }

    @Test
    void testRentRepository() {
        //Given
        Rent rent = new Rent(LocalDateTime.now(), LocalDateTime.now());

        //When
        rentRepository.save(rent);

        //Then
        long id = rent.getId();
        Optional<Rent> readRent = rentRepository.findById(id);
        assertTrue(readRent.isPresent());

        //CleanUp
        rentRepository.deleteById(id);
    }

    @Test
    void testTitleRepository() {
        //Given
        Title title = new Title("Gunslinger", "Stephen King", 1982);

        //When
        titleRepository.save(title);

        //Then
        long id = title.getId();
        Optional<Title> readTitle = titleRepository.findById(id);
        assertTrue(readTitle.isPresent());

        //CleanUp
        titleRepository.deleteById(id);
    }

}
