Step4: Spring 4 MVC ContentNegotiatingViewResolver example
(http://websystique.com/springmvc/spring-4-mvc-contentnegotiatingviewresolver-example/)

1. add dependencies in pom.xml
    * springframework:spring-core
    * springframework:spring-web
    * springframework:spring-webmvc

    * springframework:spring-oxm (XML View)
    * com.fasterxml.jackson.core:jackson-databind (JSON View)
    * com.fasterxml.jackson.core:jackson-annotations (JSON View)
    * com.lowagie:itext (PDF View)
    * org.apache.poi:poi (XLS View)

    * javax.servlet:javax.servlet-api (Servlet)
    * javax.servlet.jsp:javax.servlet.jsp-api (Servlet)
    * javax.servlet:jstl (Servlet)

2. AppConfig
    * to create the ContentNegotiationManager which is used to determine the requested media types of a request by delegating to a list of ContentNegotiationStrategy instances. By default PathExtensionContentNeogotiationStrategy (URL extension e.g. .xml, .pdf, .json...) ParameterContentNegotiationStrategy (format=xls), HeaderContentNegotiationStrategy (HTTP Accept Headers)

3. create different view resolvers
    * JSON View Resolver
    * PDF View Resolver
    * XLS View Resolver

4. create controller class

5. create initialization class

6. build and deploy the application


