# FilmQueryProject

## Description
Command-line based application where a user can browse a collection of films stored on a relational database. 

## Technologies Used
- Java
- JDBC
- Maven
- MySQL

## Lessons Learned

- Using the JDBC library to 
  - Connect to a database from within a Java program
  - Execute queries inside a java program
  - Extract information from the DB response
  - Using object-Relational Mapping to store DB content as Java Objects

- Using the Auto Closable Try Catch to automatically close Objects that implement the AutoClosable interface
- Using Prepared Statements to reduce the risk of SQL injection
- Setting the parameters of a Prepared Statement inside an AutoClosable Try Catch
- Writing SQL queries involving joins
- Managing dependencies with Maven
