1. This is a web application based on the following stack:
   - Spring Boot 
   - RESTEasy framework
   - Hibernate
   - in-memory H2 database
   - Orika mapper
   
   There is one integration test based on @SpringBootTest, and one unit test based on JUnit and AssertJ. 
   
   The list of entities is following:
   - DeveloperEntity
   - StoryEntity
   - BugEntity 
     
   Three stories, two developers and a bug are created upon starting.

   **To calculate the plan** :
    -  open http://localhost:8080/api/v1/issue-tracker/plan in a web browser
    -  OR curl --location --request GET "http://localhost:8080/api/v1/issue-tracker/plan"
       use `| jq` to print in a pretty format 
       The Swagger 2.0 specification of the service can be found on http://localhost:8080/api/v1/swagger.json
       
    The algorithm to calculate the plan is following:
    a. Take the next story from the list of stories
    b. Find the first developer with the minimal load and assign the story to this developer
    c. Go to the first step 
   
2. How to build

    Type 'gradlew clean build' to build tracker-1.0.jar.

    The server port is specified in application.properties (server.port=8080)

3. How to run

    Type either of the following commands:
      -  gradlew clean build && java -jar build/libs/tracker-1.0.jar
      -  gradlew bootRun

4. The Swagger 2.0 specification of the service can be found on http://localhost:8080/api/v1/swagger.json
         
    The REST service exposes the following endpoints:

    - To calculate the plan
      curl --location --request GET "http://localhost:8080/api/v1/issue-tracker/plan"
      
    - Add or update a story
      curl --location --request POST "http://localhost:8080/api/v1/issue-tracker/story" --header "Content-Type: application/json" --data-raw "{ \"title\": \"Design Rest API\", \"description\": \"Design Rest API\", \"points\": 7 }"

    - List all existing stories
      curl --location --request GET "http://localhost:8080/api/v1/issue-tracker/story"

    - Delete a story
      curl --location --request DELETE "http://localhost:8080/api/v1/issue-tracker/story/{uuid}" 

    - Add or update a developer
      curl --location --request POST "http://localhost:8080/api/v1/issue-tracker/developer" --header "Content-Type: application/json" --data-raw "{ \"name\": \"Frodo Baggings\"}"

    - List all existing developers
      curl --location --request GET "http://localhost:8080/api/v1/issue-tracker/developer"

    - Delete a developer
      curl --location --request DELETE "http://localhost:8080/api/v1/issue-tracker/developer/{uuid}"

    - Add or update a bug
      curl --location --request POST "http://localhost:8080/api/v1/issue-tracker/bug" --header "Content-Type: application/json" --data-raw "{ \"title\": \"Bug title1\", \"description\": \"Bug description1\", \"status\": \"NEW\", \"priority\": \"MAJOR\" }"

    - List all existing bugs
      curl --location --request GET "http://localhost:8080/api/v1/issue-tracker/bug"

    - Delete a bug
      curl --location --request DELETE "http://localhost:8080/api/v1/issue-tracker/bug/{uuid}" 
      
    - Assign a bug to a developer
      curl --location --request GET "http://localhost:8080/api/v1/issue-tracker/developer/{developerId}/bug/{bugId}"

