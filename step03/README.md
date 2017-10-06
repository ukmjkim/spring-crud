Step3: Spring 4 MVC + Hibernate 4 + MySQL + Maven integration
(http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-example-using-annotations/)

1. add dependencies in pom.xml
    * Spring dependencies : spring-tx (Transaction), spring-orm (Object Relational Mapping)
    * Hibernate dependencies : hibernate-core
    * MySQL dependencies : mysql-connector-java
    * Joda-Time : joda-time 
    * To map JodaTime with database Type : usertype.core
    * Testing dependencies : spring-test, testng, mockito-all, h2, dbunit

2. configuration.HibernateConfiguration
    * add HibernateConfiguration in configuration package (db configuration and transaction manager
    * add application.properties in resources (DB info - driver, user, password)

3. configuration.AppConfig (servlet.xml)
    * add views folder under WEB-INF
    * add message.properties in resources

4. configuration.AppInitializer

5. model.Employee
    * model.Employee

6. controller.AppController
    * AppController
        * listEmployee
        * newEmployee
        * saveEmployee
        * editEmployee
        * updateEmployee
        * deleteEmployee

7. dao.AbstractDao
    * dao.AbstractDao
        * getSession()
        * getByKey()
        * persist()
        * delete()
        * createEntityCriteria
        
    * dao.EmployeeDao (interface)
        * findById()
        * saveEmployee()
        * deleteEmployeeBySsn()
        * findAllEmployees()

    * dao.impl.EmployeeDaoImpl

8. service.EmployeeService
    * service.EmployeeService
        * findById()
        * saveEmployee()
        * deleteFromBySsn()
        * findAllEmployee()
        * isEmployeeBySsn()

    * service.impl.EmployeeServiceImpl

9. jsp
    * allemployee.jsp
    * registration.jsp
    * success.jsp

10. EMPLOYEE table

