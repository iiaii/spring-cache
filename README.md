# Spring Cache


캐시는 비용이 큰 요청 (DB 접근, 외부 요청) 등에서 사용한다. 하지만 반복적으로 동일한 결과를 주는 작업에 사용되지 않으면, 즉 매번 다른 결과를 반환하는 작업에는 캐시가 적용되더라도 오히려 성능이 떨어진다. (캐시는 hit 해야 의미가 있음)

스프링 캐시는 추상화 인터페이스를 제공하고 스프링 캐시 PSA 내부적으로 AOP가 사용된다. 캐시 기능을 담은 어드바이스는 스프링이 제공하고, 이를 적용할 대상 빈과 메서드를 선정하고 속성을 부여하는 작업은 기본 AOP 설정 방법을 이용하거나, 어노테이션을 사용한다. 

스프링 캐시는 실제로 캐싱 데이터를 저장하는 Provider를 설정할 수 있다. 

- Generic
- JCache
- EhCache
- Hazelcast
- Redis
- ...


---
### 주요 Annotations

- @EnableCaching : 메인 클래스 위에 붙여서 캐시 사용 여부
- @Cacheable : 캐시 적용 (이 어노테이션이 선언된 메서드는 결과를 캐시에 저장하고, 다음 호출에서는 메서드의 내부 로직을 수행하지 않고 캐싱된 결과를 바로 리턴)
- @CacheEvict : 캐시 제거
- @CachePut : 메서드 실행을 방해하지 않고 캐시를 업데이트
- @Caching : 메서드에 적용할 여러 캐시 조작을 그룹화
- @CacheConfig : 클래스 레벨에서 캐시 관련 설정 (캐시 작업에 사용할 캐시 이름을 단일 클래스로 정의할 수 있음)


---
### 주요 특징

- @Cacheable에서 value는 해당 캐시의 이름(아이디)이다. 기존 데이터를 삭제해야 할때 @CacheEvict(value= "XXX")를 사용하는데, value 값이 동일한 캐시를 지워버린다.
- default로 메서드의 파라미터를 캐시 값으로 구성한다. (같은 메서드가 불리더라도 파라미터가 다르면 다른 캐시를 생성)
- `@Cacheable(value = "product", key = "#productType")` 처럼 key 속성을 지정하면 해당 파라미터를 캐시 값으로 할 수 있다. (파라미터가 여러개인 경우)
- `@Cacheable(key = "'jeongpro'  + #param1 + #param2")` 처럼 여러 파라미터를 하나의 키로 지정할 수 있다.
- Ehcache의 경우 별도의 xml 파일 같은 것을 만들어서 임시저장경로, 힙에 생성될 최대 객체수, 캐시 삭제 여부, 캐시가 유지될 시간, 캐시 삭제 전략 등을 설정한다.


---
### 스프링 부트 캐시 적용

1. 의존성 추가

```maven
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

2. 프로젝트에 @EnableCaching 추가

```java
@EnableCaching
@SpringBootApplication
public class SpringCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCacheApplication.class, args);
    }

}
```

3. 캐시하고 싶은 메서드에 @Cacheable 추가

```java
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
```

---
### Ref

- [EHCache vs Hazelcast](https://roynus.tistory.com/913)
- [Hazelcast](https://brunch.co.kr/@springboot/56)
- [EHCache](https://javacan.tistory.com/entry/133)
- [Cassandra](https://nicewoong.github.io/development/2018/02/11/cassandra-feature/)

---
### 참고

- Cassandra : Key-space > Table > Row > Column name : Column value
- Mongodb : db > collection > document > key : value
- RDBMS : DB > Table > row > column
- ElasticSearcvh : index > type > document > key : value
