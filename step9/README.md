Step 8: Spring MVC 4 + Spring Security 4 + Hibernate Example
(http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/)

How to deploy war to tomcat using maven command

1. maven settings.xml
Mijungs-MBP-2:conf MijungKimMacPro$ cat ~/.m2/settings.xml
<settings>
  <servers>
    <server>
      <id>tomcatserver</id>
      <username>tomcat</username>
      <password>admin</password>
    </server>
  </servers>
</settings>

Mijungs-MBP-2:conf MijungKimMacPro$

2. tomcat admin setup
Mijungs-MBP-2:conf MijungKimMacPro$ cat tomcat-users.xml | grep role
  NOTE:  By default, no user is included in the "manager-gui" role required
<role rolename="manager-script"/>
<role rolename="manager-gui"/>
<user username="tomcat" password="admin" roles="manager-script"/>
<user username="manager" password="admin" roles="manager-gui"/>

3. add plugin in pom.xml
      <!-- mvn tomcat7:deploy -->
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
          <url>http://localhost:8080/manager/text</url>
          <server>tomcatserver</server>
          <path>/${project.artifactId}</path>
          <update>true</update>
        </configuration>
      </plugin>

4. mvn tomcat7:deploy





========================================================================================

User List part is same to step7

1. add dependencies in pom.xml
    * spring-security-web
    * spring-security-config
    * spring-security-taglibs

2. create SecurityConfiguration extends WebSecurityConfigurerAdapter
    * @Autowired UserDetailsService
    * @Autowired PersistentTokenRepository
    * @Autowired configGlobalSecurity
    * @Override configure
        * http.authorizeRequests (give roles to pattern matched pages)
        * login pages and field name (username, password)
        * rememberMe
        * csrf
        * accessDeniedPage
    * @Bean PasswordEncoder
    * @Bean DaoAuthenticationProvider
    * @Bean PersistentTokenBasedRememberMeServices
    * @Bean AuthenticationTrustResolver

3. create PersistentLogin model

4. create HibernateTokenRepositoryImpl extends AbstractDao implements PersistTokenRepository
    * @Repository("tokenRepositoryDao")
    * @Transactional
    * createNewToken
    * getTokenForSeries
    * removeUserTokens
    * updateToken

5. create CustomUserDetailsServiceImpl implements UserDetailsService
    * @Autowired UserService
    * @Transactional("readOnly=true") loadUserByUsername(String ssoId)
    * getGrantedAuthorities

6. create com.mjkim.springmvc.step9.security.SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer

7. add rememberMe and authenticationTrustResolver in AppController
    * @Autowired PersistentTokenBasedRememberMeServices
    * @Autowired AuthenticationTrustResolver
    * add new RequestMapping
        * accessDeniedPage
        * loginPage
        * logoutPage
    * add private methods
        * getPrincipal()
        * isCurrentAuthenticationAnonymous()

8. add jsp and modify the current pages to have login / logout button



Database Schema

(MySQL User: root, Password: )

create database springmvc;

/*All User's gets stored in APP_USER table*/
create table APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);
   
/* USER_PROFILE table contains all possible roles */ 
create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
   
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);
  
/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('USER');
  
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
  
INSERT INTO USER_PROFILE(type)
VALUES ('DBA');
  
  
/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email)
VALUES ('sam','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Sam','Smith','samy@xyz.com');
  
  
/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='sam' and profile.type='ADMIN';
 
/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

