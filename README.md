# ACME Learning Application

ACME Learning is an online education platform that helps Instructors to create Courses and make them available for Students to enroll

## Installation

Download this repository, go to the source folder and execute the following command to generate a jar file with the application.

```bash
./mvnw clean package
```
To run the generated jar file execute :

```bash
java -jar target/acme-learning-0.0.1-SNAPSHOT.jar
```

## Usage
To use most of the funtionalities in the application you need to be authenticated, so you need to pass a Bearer token in the Headers. To get a token you can use this endpoint: 


`<link>` : <http://localhost:8080/oauth/token>

Theese are some users that you can use to your tests: 
- Instructors: samuel.pena, adriana.jaramillo 
- Students: andres.perez, maria.gomez 

To make it easier to test the password is the same for all of them (password123) but you can create a new instructor or student with a different password if needed.


## Important!
In the route folder of the project you will find a file called "ACME.postman_collection.json" this a collection of postman that contains the test cases for the application. You can import it in postman to use them.
