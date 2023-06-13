package uz.pdp.springsecurity.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookEntity extends BaseEntity{
    private String name;
    private String author;
    private Integer page;
}
