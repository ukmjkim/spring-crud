Step 8: Spring MVC 4 + Spring Security 4 + Hibernate Example
(http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/)

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

