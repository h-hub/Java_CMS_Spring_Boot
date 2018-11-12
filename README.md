# JAVA CMS
https://harshajayamanna.com/2018/11/08/spring-boot-mvc-file-upload-with-exception-handling/

Requirments

  - Java 10
  - Maven > 3.5.3
  - Mysql

# Steps

  - Import project as a maven project to eclipse
  - Import the mysql dump in the git repo to mysql database
  - Edit the \src\main\resources\application.properties file to match your mysql properties. (Edit below fields accordingly)
    - spring.datasource.url=jdbc:mysql://localhost:3306/cms_crud
    - spring.datasource.username=root
    - spring.datasource.password=admin1234
    - spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    - spring.jpa.database-platform = org.hibernate.dialect.MySQL5Dialect
    - spring.jpa.show-sql = true
   - Run maven install
   - Then you can run the application as a java application (Run as -> Java application) or
   - Go to the target folder and execute the jar file (java -jar crudApp-0.0.1-SNAPSHOT.jar)


# How to test:
  - Go to this url  http://localhost:8089 (Tested only with chrome)
  - Use the below credentiona which i have included in the DB dump
    - username :  harsha.kj89@gmail.com
    - Password : 123123123
