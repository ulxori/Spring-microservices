# Spring microservices


### Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [How to use it](#how-to-use-it)

---

## Description
A project developed during ISA laboratories at the GUT, allows user to store authors and their books. The project is divided into stand-alone modules based on microservices architecture. Each of the two microservices uses its own database. The app includes Spring Cloud Gateway, which contains routing rules. In front of the application runs the ReverseProxy server.


[Back To The Top](#spring-microservices)

---

## Technologies

 - Java
 - Spring
 - Hibernate
 - MySQL
 - Docker
 - Nginx
 - JavaScript
 - Html
 - CSS
 
[Back To The Top](#spring-microservices)

---



## How to use it


Make sure you have all required files and go to the folder with docker-compose.yml file, then run the command below.

    docker-compose up -d
   This may take a while.
   After starting all containers it's necessary to start spring boot aplication on the following containers: authors_app, books_app and gateway. 
   

    docker exec -d CONTAINER ID ./start.sh
  To check the container id run this command
  

    docker ps
  
You can acess app(frontend+backend) via port 8000

    localhost:8000
Backend is available via port 8080

To close application run

    docker-compose down
[Back To The Top](#spring-microservices)
