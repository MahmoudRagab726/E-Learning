# E-learning
 this project for creating simple CRUD operations using spring boot, spring data and spring security
## what this project contain 
* Spring Boot <br>
* Spring Data <br>
* Spring Security <br>
* JWT for authentication <br>
* Swagger2 for doc <br>
* H2 inmemory DB<br>
* AOP for creating Logging Aspect <br>
* Logback for logging <br>
* Java Mail <br>
* Some unite test using Junit and Mockito 
## Important Links 
* swagger : http://localhost:8080/swagger-ui.html <br>
* H2 DB : http://localhost:8080/h2-console <br>
* Logging file : three2one.log <br>
## Notes 
* There is validation on email format and password(must contain uppercase, lowercase, numbers, special char)
## Status Codes Mapping 
* Code   --   Descreption <br>

* 000    --   Success <br>
* 100    --   Inactive student <br>
* 101    --   Student already exist  <br>
* 102    --   Invalid email format <br>
* 103    --   Invalid password format <br>
* 104    --   Password and conformed password un matched <br>
* 105    --   Fail to delete <br>
* 106    --   Course doesn't exist <br>
* 107    --   Missing data in the request <br>
* 108    --   Student doesn't exist <br>
* 99     --   General failure 
