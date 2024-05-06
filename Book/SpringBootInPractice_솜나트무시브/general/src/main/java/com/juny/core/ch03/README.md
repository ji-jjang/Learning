implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'

```yml
spring:
    data:
        mongodb:
            url: mongodb://localhost:27017/testdb
```


docker run --name mongodb-container -d -p 27017:27017 mongo

docker exec -it mongodb-container /bin/bash

mongosh

```sql
db.createUser
(
    {
        user: "book",
        pwd:  "1234",
        roles: 
        [
            { "role" : "root", "db" : "admin" },
        ]
    }
)
```
mongosh -u book