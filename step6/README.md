Step 6: Creating Maven multi-module project with Eclipse
(http://websystique.com/maven/creating-maven-multi-module-project-with-eclipse/)

1. new a project for parent
    * achetype : maven-archetype-quickstart
2. change pom.xml (<packacing>pom</packaging>)
3. new projects for child
    * webapp1 : maven-archetype-webapp
    * webapp2 : maven-archetype-webapp
    * model-lib : maven-archetype-quickstart
4. add child modules in parent pom.xml
5. add parent modules in child pom.xml

