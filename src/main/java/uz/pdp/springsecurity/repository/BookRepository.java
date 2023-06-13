package uz.pdp.springsecurity.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.BookEntity;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    List<BookEntity> searchBookEntitiesByNameContainingIgnoreCase(String name, Pageable page);
}
