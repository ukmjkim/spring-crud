Step1: the first Spring4 MVC project

Environment Setting and create a maven webapp project
1. Start Eclipse, click on File > New > Maven Project
2. In Select a Archetype window, select "maven-archetype-webapp"
3. Give Group Id and Artifact Id
4. Eclipse Project Properties setting
    go to Project > Properties > Java BuildPath > Libraries, select/change-to appropriate Java version
    Add Library > JRE System Library > Java SE 8
    Maven Dependencies
5. Remove jsp error
    Preferences > Web > JSP Files > Validation, uncheck validate JSP fragments
6. Open pom.xml
    Add dependencies, spring-mvc, servlet, servlet-jsp, jstl
    Add plugins, maven-compiler-plugin, maven-war-plugin
    (after adding the dependencies, jsp error will be gone)
7. Configure Tomcat Server in Eclipse
    (1) go to servers tab using contextual menus select Add > server. Select Tomcat v8.0 server
    (2) Configure a new Run time server environment
    (3) Double click the new Tomcat server in servers tab
    (4) Select the new server and right click, go to General > Switch Location
    (5) Server Location > Select "Use Tomcat installation" and click Browse and select tomcat webapps
    (6) On your project, open properties
    (7) Deployment Assembly and add Java Build Path Entries > Maven Dependencies and finish


Programming
1. Add a new package, configuration and controller
2. Add configurations, HelloWorldConfiguration.java (a replacement of servlet.xml) and HelloWorldInitializer.java (a replacement of web.xml)
3. Add a controller, HelloWorldController.java
4. Add a folder "views" under WEB-INF and add a jsp file called "welcome.jsp"
5. Delete web.xml
6. Run As > mvn install
7. Copy the war file to tomcat webapps
8. Run As > Run on server

For your convenience
add maven copy plugin and copy the war file to tomcat during mvn install.









