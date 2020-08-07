package me.iiaii.springcache;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/* Copyright (c) 2020 ZUM Internet, Inc.
 * All right reserved.
 * http://www.zum.com
 * This software is the confidential and proprietary information of ZUM
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with ZUM.
 *
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   iiaii                2020-08-07
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    Logger logger = LoggerFactory.getLogger(BookServiceTest.class);

    @BeforeEach
    void init() {
        bookRepository.deleteAll();
    }

    @Test
    public void 캐시없는_책가져오기() throws Exception {
        // given
        Book book = Book.builder()
                .name("스프링 캐시")
                .price(10000)
                .isbn(12345L)
                .build();
        bookRepository.save(book);

        // when
        // then
        for (int i = 0; i < 5; i++) {
            Book findBook = bookService.findBookByName(book.getName());
            Thread.sleep(1000);
            logger.info(i + ". " + findBook);
            assertEquals(book, findBook);
        }
    }

    @Test
    public void 캐시있는_책가져오기() throws Exception {
        // given
        Book book = Book.builder()
                .name("스프링 캐시")
                .price(10000)
                .isbn(12345L)
                .build();
        bookRepository.save(book);

        // when
        // then
        for (int i = 0; i < 5; i++) {
            Book findBook = bookService.findBookByNameWithCache(book.getName());
            Thread.sleep(1000);
            logger.info(i + ". " + findBook);
            assertEquals(book, findBook);
        }
    }


}