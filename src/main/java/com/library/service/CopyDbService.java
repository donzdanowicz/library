package com.library.service;

import com.library.domain.Copy;
import com.library.exception.CopyNotFoundException;
import com.library.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CopyDbService {
    private final CopyRepository copyRepository;

    public List<Copy> getCopies() {
        return copyRepository.findAll();
    }

    public Optional<Copy> getCopy(final Long id) {
        return copyRepository.findById(id);
    }

    public Copy saveCopy(final Copy copy) {
        return copyRepository.save(copy);
    }

    public void deleteCopy(final Long id) {
        copyRepository.deleteById(id);
    }

    public Copy updateStatus(final Long id, final String status) throws CopyNotFoundException {
        Copy copy = copyRepository.findById(id).orElseThrow(CopyNotFoundException::new);

       copy.setStatus(status);
       copyRepository.save(copy);
       return copy;
    }

    /*public Integer howManyCopiesOfGivenTitleAvailableToRent(Long titleId) {
        return copyRepository.howManyCopiesOfGivenTitleAvailableToRent(titleId);
    }*/




}
