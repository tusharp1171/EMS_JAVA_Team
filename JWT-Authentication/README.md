## Spring Boot Login example with Spring Security, MySQL and JWT
Build a Spring Boot Login and Registration example (Rest API) that supports JWT with HttpOnly Cookie. You’ll know:

- Appropriate Flow for User Login and Registration with JWT and HttpOnly Cookies
- Spring Boot Rest Api Architecture with Spring Security
- How to configure Spring Security to work with JWT
- How to define Data Models and association for Authentication and Authorization
- Way to use Spring Data JPA to interact with MySQL Database
## Dependency
– MySQL:
```xml
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>
```
– jwt:
```xml
<dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
```
## Configure Spring Datasource, JPA, App properties
- For MySQL
```
#By Default mySQL port No is 3306 in my case i have change my port no 3307
spring.datasource.url= jdbc:mysql://localhost:3307/testdb?useSSL=false
spring.datasource.username= root
spring.datasource.password= root

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

# App Properties
bezkoder.app.jwtCookieName= bezkoder
bezkoder.app.jwtSecret= ======================BezKoder=Spring===========================
bezkoder.app.jwtExpirationMs= 30000
```
