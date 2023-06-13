package uz.pdp.springsecurity.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.dto.BookRequestDto;
import uz.pdp.springsecurity.entity.BookEntity;
import uz.pdp.springsecurity.service.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/get-all")
    public ResponseEntity<List<BookEntity>> getAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.status(200).body(bookService.getAll(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookEntity>> searchBookByName(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String name
    ) {
        return ResponseEntity.status(200).body(bookService.searchBook(page, size, name));
    }

    @PostMapping("/add")
    public ResponseEntity<BookEntity> addBook(
            @RequestBody BookRequestDto bookRequestDto
    ) {
        return ResponseEntity.ok(bookService.save(bookRequestDto));
    }

    @PatchMapping("/{bookId}/update")
    public ResponseEntity<BookEntity> updateBook(
            @RequestBody BookRequestDto bookRequestDto,
            @PathVariable UUID bookId
    ) {
        return ResponseEntity.ok(bookService.update(bookRequestDto, bookId));
    }

    @DeleteMapping("/{bookId}/delete")
    public ResponseEntity<BookEntity> deleteBook(@PathVariable UUID bookId) {
        bookService.deleteById(bookId);
        return ResponseEntity.status(204).build();
    }

}
