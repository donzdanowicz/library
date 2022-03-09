package com.library.controller;

import com.library.domain.Copy;
import com.library.domain.Rent;
import com.library.domain.RentDto;
import com.library.domain.Status;
import com.library.exception.CopyNotFoundException;
import com.library.exception.ReaderNotFoundException;
import com.library.exception.RentNotFoundException;
import com.library.mapper.CopyMapper;
import com.library.mapper.RentMapper;
import com.library.service.CopyDbService;
import com.library.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/rent")
public class RentController {
    private final RentDbService rentDbService;
    private final RentMapper rentMapper;
    private final CopyDbService copyDbService;
    private final CopyMapper copyMapper;

    @GetMapping(value = "getRents")
    public List<RentDto> getRents() {
        List<Rent> rents = rentDbService.getRents();
        return rentMapper.mapToRentDtoList(rents);
    }

    @GetMapping(value = "getRent/{id}")
    public RentDto getRent(@PathVariable Long id) throws RentNotFoundException {
        return rentMapper.mapToRentDto(
                rentDbService.getRent(id).orElseThrow(RentNotFoundException::new)
        );
    }

    @DeleteMapping(value = "deleteRent/{id}")
    public void deleteRent(@PathVariable Long id) {
        rentDbService.deleteRent(id);
    }

    @PostMapping(value = "rentBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void rentBook(@RequestBody RentDto rentDto) throws ReaderNotFoundException, CopyNotFoundException {
        Rent rent = rentMapper.mapToRent(rentDto);
        Copy copy = rent.getCopy();
        copyDbService.updateStatus(copy.getId(), Status.RENTED);
        //copyDbService.saveCopy(copy);
        //rentDbService.saveRent(rent);
    }

    @PutMapping(value = "updateRent")
    public RentDto updateRent(@RequestBody RentDto rentDto) throws ReaderNotFoundException, CopyNotFoundException {
        Rent rent = rentMapper.mapToRent(rentDto);
        Rent updatedRent = rentDbService.saveRent(rent);
        return rentMapper.mapToRentDto(updatedRent);
    }

    @PutMapping(value = "returnBook/{id}")
    public RentDto returnBook(@PathVariable Long id) throws RentNotFoundException, CopyNotFoundException {
        Rent rent = rentDbService.getRent(id).orElseThrow(RentNotFoundException::new);
        rent.setReturnDate(LocalDateTime.now());
        Copy copy = rent.getCopy();
        copyDbService.updateStatus(copy.getId(), Status.AT_LIBRARY);
        copyDbService.saveCopy(copy);
        rentDbService.saveRent(rent);

        return rentMapper.mapToRentDto(rent);
    }

}
