package uz.pdp.springsecurity.service;

import uz.pdp.springsecurity.dto.BookRequestDto;
import uz.pdp.springsecurity.entity.BookEntity;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<BookEntity> getAll(int page, int size);
    List<BookEntity> searchBook(int page, int size, String name);
    BookEntity save(BookRequestDto bookDto);
    BookEntity update(BookRequestDto bookRequestDto, UUID bookId);
    void deleteById(UUID id);
}
