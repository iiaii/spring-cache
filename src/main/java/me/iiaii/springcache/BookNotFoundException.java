package me.iiaii.springcache;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String name) {
        super(name+" 이라는 이름의 책이 존재하지 않습니다");
    }
}