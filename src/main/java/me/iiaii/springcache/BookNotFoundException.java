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

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String name) {
        super(name+" 이라는 이름의 책이 존재하지 않습니다");
    }
}