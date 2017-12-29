# Spring Boot My Batis Implementation

**Project uses following package/technologies**

1. Spring-Boot 1.5.8
2. Java 1.8
3. MySql
4. MyBatis 1.0
5. Angular Js 1.6
6. JsonWebToken jjwt
7. Swagger-ui/Swagger2 (Api Documentation)
8. Spring-boot Thymeleaf
9. Embeded Tomcat container

## How to Install or Use

*Setup Database*

Import database into mysql which is available in `src/main/resources/sql/spring_demo.sql` file.

*Setup IDE*

Import project as maven project in your favourite ide (eclipse/Idea)

*Run*

Use `mvn clean package` command on terminal/cmd/powershell to create .jar package

run command on terminal/cmd/powershell `java -jar target/springbootmybatis-1.0-SNAPSHOT.jar` from root folder where pom.xml resides

## Troubleshooting

If running `mvn clean package` would ran into problem check all dependency are resolved

If app failed to start after running `java -jar target/springbootmybatis-1.0-SNAPSHOT.jar` command check any application already running on 8080 port

#Credits

All the credits for the guys at Yuktamedia for creating the original implementation.

#License

Mit License: http://www.opensource.org/licenses/mit-license.php
