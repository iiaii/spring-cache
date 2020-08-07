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
package me.iiaii.springcache;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    Logger logger = LoggerFactory.getLogger(BookService.class);

    public Book findBookByName(String name) {
        logger.info("캐시 없는 findBookByName");
        try {
            logger.info("3초 딜레이");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return bookRepository.findByName(name).orElseThrow(() -> new BookNotFoundException(name));
    }

    @Cacheable(value = "findBookByName")
    public Book findBookByNameWithCache(String name) {
        logger.info("캐시 있는 findBookByName");
        try {
            logger.info("3초 딜레이");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bookRepository.findByName(name).orElseThrow(() -> new BookNotFoundException(name));
    }
}