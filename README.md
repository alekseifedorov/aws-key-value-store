1. This is a simple key-value store with two HTTP endpoints. 

This application is based on the following stack:
- **AWS SAM** as a framework to build and deploy the application
- **AWS Lambda**
- **Redis** as persistent data storage.
- **Spring Boot / Spring MVC**
- **Gradle**
   
Prerequisites:
   -  localstack (https://docs.localstack.cloud/get-started/)
   -  samlocal (https://docs.localstack.cloud/integrations/aws-sam/)
   -  Redis (docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest)
      
2. How to build and deploy
   Please, specify the hostname and port of Redis installed in advance in template.yaml as follows:
   
```
   Environment:
     Variables:
       REDIS_CLUSTER_MODE_ON: false
       REDIS_HOST: docker.for.win.localhost
       REDIS_PORT: 6379
```

Type the following commands:
   -  samlocal build
   -  samlocal deploy --guided
   -  samlocal local start-api
   -  'samlocal delete' to delete the stack 

3. How to use
   **To set a key** :
   -  curl --location --request POST "http://localhost:3000/store/key/value"

   **To get a key** :
   -  curl --location --request GET "http://localhost:3000/store/key"
   -  OR open "http://localhost:3000/store/key" in a web browser
