Step2: Spring 4 MVC form validation with hibernate jsr validator using annotations
(http://websystique.com/springmvc/spring-4-mvc-form-validation-with-hibernate-jsr-validator-resource-handling-using-annotations/)

1. add dependencies in pom.xml
    (1) spring-core, spring-web
    (2) javax.validation, hibernate-validator
2. add a model, Student.java
    (1) Use constraints annotation for each field
        @Size, @NotNull, @Past (javax.validation.constraints)
        @Email, @NotEmpty (hibernate.validator.constraints)
        @DateTimeFormat (springframework.format.annotaion)
    (2) add getters/setters
3. add new request mapping in controller for registration form page and result page
    (1) added newRegistration, saveRegistration
    (3) added ModelAttribute (generate List<String> for some fields)
4. change HelloWorldConfiguration.java inherited from WebMvcConfigurerAdapter to register resource handler (for static resources like css, javascript, etc)
5. add enroll.jsp and success.jsp
6. add css under webapps
7. add messages.properties under resources (ResourceHanlder)

