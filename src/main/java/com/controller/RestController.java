package com.controller;


import com.domain.dto.BookDto;
import com.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1/books")
@Slf4j
public class RestController {
    private final BookService bookService;

    //생성자 DI
    public RestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String getAll() {
        StringBuilder stringBuilder = new StringBuilder();
        List<BookDto> bookDtos =bookService.getAll();
        List<ResponseEntity<BookDto>> responseEntities = new ArrayList<>();
        BookDto bookDto = bookDtos.get(0);
        log.info("id={}, name={}, authorName={}", bookDto.getId(), bookDto.getName(), bookDto.getAuthorName());

        for (BookDto dto : bookDtos) {
            stringBuilder.append(
                    String.format("{\"id\":\"%d\", \"name\":\"%s\", \"authorName\":\"%s\"}<br>",
                            dto.getId(),
                            dto.getName(),
                            dto.getAuthorName())+"");
        }
        String response = String.valueOf(stringBuilder);

        return response;
    }
}