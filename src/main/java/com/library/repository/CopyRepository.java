package com.library.repository;

import com.library.domain.Copy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {
    @Override
    List<Copy> findAll();

    @Override
    Copy save(Copy copy);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Copy> findById(Long id);

    @Query(nativeQuery = true)
    Integer howManyCopiesOfGivenTitleAvailableToRent(@Param("TITLE_ID") Long titleId);


}
