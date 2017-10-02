Step3: Spring 4 MVC + Hibernate 4 + MySQL + Maven integration
(http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-example-using-annotations/)

1. add dependencies in pom.xml
    (1) Spring dependencies : spring-tx (Transaction), spring-orm (Object Relational Mapping)
    (2) Hibernate dependencies : hibernate-core
    (3) MySQL dependencies : mysql-connector-java
    (4) Joda-Time : joda-time
    (5) To map JodaTime with database Type : usertype.core
    (6) Testing dependencies : spring-test, testng, mockito-all, h2, dbunit

2. configuration.HibernateConfiguration
    (1) add HibernateConfiguration in configuration package (db configuration and transaction manager)
    (2) add application.properties in resources (DB info - driver, user, password)

3. configuration.AddConfig (servlet.xml)
    (1) add views folder under WEB-INF
    (2) add message.properties in resources

4. configuration.AppInitializer

5. model.Employee
    (1) model.Employee

6. controller.AppController
    (1) AppController
        -. listEmployee
        -. newEmployee
        -. saveEmployee
        -. editEmployee
        -. updateEmployee
        -. deleteEmployee

7. dao.AbstractDao
    (1) dao.AbstractDao
        -. getSession()
        -. getByKey()
        -. persist()
        -. delete()
        -. createEntityCriteria

    (2) dao.EmployeeDao (interface)
        -. findById()
        -. saveEmployee()
        -. deleteEmployeeBySsn()
        -. findAllEmployees()

    (3) dao.impl.EmployeeDaoImpl

8. service.EmployeeService
    (1) service.EmployeeService
        --. findById()
        -. saveEmployee()
        -. deleteFromBySsn()
        -. findAllEmployee()
        -. isEmployeeBySsn()

    (2) service.impl.EmployeeServiceImpl

9. jsp
    (1) allemployee.jsp
    (2) registration.jsp
    (3) success.jsp

10. EMPLOYEE table

