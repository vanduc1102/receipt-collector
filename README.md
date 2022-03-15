# spring-boot-rest-template
Spring template for CRUD API

# Development

## Configurations
```
cp src/main/resources/application-sample.properties src/main/resources/application-local.properties 
```

## Start database

```
docker compose up
```

## Start API

```
./mvnw spring-boot:run
```

# Deployment
## Build docker
```
docker build -t receipt-collector .
```

## Test 

```
docker run -p 8080:8080 receipt-collector
```

# Postman collection

https://www.postman.com/collections/f77475ad1b3ba084a4b3
