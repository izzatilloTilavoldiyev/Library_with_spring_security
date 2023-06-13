package uz.pdp.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.dto.BookRequestDto;
import uz.pdp.springsecurity.entity.BookEntity;
import uz.pdp.springsecurity.exception.DataNotFoundException;
import uz.pdp.springsecurity.repository.BookRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookEntity> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable).getContent();
    }

    @Override
    public List<BookEntity> searchBook(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.searchBookEntitiesByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public BookEntity save(BookRequestDto bookDto) {
        BookEntity bookEntity = modelMapper.map(bookDto, BookEntity.class);
        return bookRepository.save(bookEntity);
    }

    @Override
    public BookEntity update(BookRequestDto bookRequestDto, UUID bookId) {
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("book not found"));
        modelMapper.map(bookRequestDto, book);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }
}
