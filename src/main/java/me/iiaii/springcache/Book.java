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