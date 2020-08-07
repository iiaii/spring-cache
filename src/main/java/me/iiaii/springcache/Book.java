package me.iiaii.springcache;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private Long isbn;

    @Builder
    public Book(String name, int price, Long isbn) {
        this.name = name;
        this.price = price;
        this.isbn = isbn;
    }
}