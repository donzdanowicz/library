package com.library.controller;

import com.library.domain.Title;
import com.library.domain.TitleDto;
import com.library.exception.CopyNotFoundException;
import com.library.exception.TitleNotFoundException;
import com.library.mapper.TitleMapper;
import com.library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/title")
public class TitleController {
    private final TitleDbService titleDbService;
    private final TitleMapper titleMapper;

    @GetMapping(value = "getTitles")
    public List<TitleDto> getTitles() {
        List<Title> titles = titleDbService.getTitles();
        return titleMapper.mapToTitleDtoList(titles);
    }

    @GetMapping(value = "getTitle/{id}")
    public TitleDto getTitle(@PathVariable Long id) throws TitleNotFoundException {
        return titleMapper.mapToTitleDto(
                titleDbService.getTitle(id).orElseThrow(TitleNotFoundException::new)
        );
    }

    @DeleteMapping(value = "deleteTitle/{id}")
    public void deleteTitle(@PathVariable Long id) {
        titleDbService.deleteTitle(id);
    }

    @PostMapping(value = "createTitle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody TitleDto titleDto) throws CopyNotFoundException {
        Title title = titleMapper.mapToTitle(titleDto);
        titleDbService.saveTitle(title);
    }

    @PutMapping(value = "updateTitle")
    public TitleDto updateTitle(@RequestBody TitleDto titleDto) throws CopyNotFoundException {
        Title title = titleMapper.mapToTitle(titleDto);
        Title updatedTitle = titleDbService.saveTitle(title);
        return titleMapper.mapToTitleDto(updatedTitle);
    }

}
