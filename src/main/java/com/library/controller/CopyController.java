package com.library.controller;

import com.library.domain.Copy;
import com.library.domain.CopyDto;
import com.library.exception.CopyNotFoundException;
import com.library.exception.RentNotFoundException;
import com.library.exception.TitleNotFoundException;
import com.library.mapper.CopyMapper;
import com.library.service.CopyDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/copy")
public class CopyController {
    private final CopyDbService copyDbService;
    private final CopyMapper copyMapper;

    @GetMapping(value = "getCopies")
    public List<CopyDto> getCopies() {
        List<Copy> copies = copyDbService.getCopies();
        return copyMapper.mapToCopyDtoList(copies);
    }

    @GetMapping(value = "getCopy/{id}")
    public CopyDto getCopy(@PathVariable Long id) throws CopyNotFoundException {
        return copyMapper.mapToCopyDto(
            copyDbService.getCopy(id).orElseThrow(CopyNotFoundException::new)
        );
    }

    @DeleteMapping(value = "deleteCopy/{id}")
    public void deleteCopy(@PathVariable Long id) {
        copyDbService.deleteCopy(id);
    }

    @PostMapping(value = "createCopy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCopy(@RequestBody CopyDto copyDto) throws TitleNotFoundException, RentNotFoundException {
        Copy copy = copyMapper.mapToCopy(copyDto);
        copyDbService.saveCopy(copy);
    }

    @PutMapping(value = "updateCopy")
    public CopyDto updateCopy(@RequestBody CopyDto copyDto) throws TitleNotFoundException, RentNotFoundException {
        Copy copy = copyMapper.mapToCopy(copyDto);
        Copy updatedCopy = copyDbService.saveCopy(copy);
        return copyMapper.mapToCopyDto(updatedCopy);
    }

    @PutMapping(value = "updateCopyStatus/{id}")
    public CopyDto updateCopyStatus(@PathVariable Long id, @RequestParam String status) throws CopyNotFoundException {
        return copyMapper.mapToCopyDto(copyDbService.updateStatus(id, status));
    }

    /*@GetMapping(value = "howManyCopiesOfGivenTitleAvailableToRent/{id}")
    public Integer howManyCopiesOfGivenTitleAvailableToRent(@PathVariable Long id) {
        return copyDbService.howManyCopiesOfGivenTitleAvailableToRent(id);
    }*/
}
