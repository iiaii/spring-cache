# spring-cache

스프링 부트 캐시 적용

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
