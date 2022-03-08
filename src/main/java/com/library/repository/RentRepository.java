package com.library.repository;

import com.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
    @Override
    List<Rent> findAll();

    @Override
    Rent save(Rent rent);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Rent> findById(Long id);
}
