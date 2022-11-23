package com.service;

import com.domain.dto.BookDto;
import com.domain.entity.Author;
import com.domain.entity.Book;
import com.domain.repository.AuthorRepository;
import com.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    //생성자 DI
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDto> getAll(){
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book:books) {
            Optional<Author> optAuthor = authorRepository.findById(book.getAuthorId());

            if(optAuthor.isEmpty()) {
                bookDtos.add(new BookDto(book.getId(), book.getName(), "null"));
            } else {
                bookDtos.add(new BookDto(book.getId(), book.getName(), optAuthor.get().getName()));
            }
        }
        return bookDtos;
    }
}
