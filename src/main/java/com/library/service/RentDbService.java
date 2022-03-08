package com.library.service;

import com.library.domain.Rent;
import com.library.exception.RentNotFoundException;
import com.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RentDbService {
    private final RentRepository rentRepository;

    public List<Rent> getRents() {
        return rentRepository.findAll();
    }

    public Optional<Rent> getRent(final Long id) {
        return rentRepository.findById(id);
    }

    public Rent saveRent(final Rent rent) {
        return rentRepository.save(rent);
    }

    public void deleteRent(final Long id) {
        rentRepository.deleteById(id);
    }

    public Rent returnBook(final Long id) throws RentNotFoundException {
        Rent rent = rentRepository.findById(id).orElseThrow(RentNotFoundException::new);
        rent.getCopy().setStatus("AT_LIBRARY");
        rent.setReturnDate(LocalDateTime.now());

        return rent;
    }
}
