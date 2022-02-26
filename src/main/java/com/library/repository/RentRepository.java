package com.library.repository;

import com.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface RentRepository extends CrudRepository<Rent, Long> {
    @Override
    List<Rent> findAll();
}
