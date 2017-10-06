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

