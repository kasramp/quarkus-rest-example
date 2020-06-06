# Quarkus RESt API example

This project uses Quarkus to build REST APIs. 

For the tutorials check the below links,
- [Building REST APIs with Quarkus](https://www.geekyhacker.com/2020/06/06/building-rest-apis-with-quarkus/)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```bash
./mvnw quarkus:dev
```

The app runs on `localhost:8080`. You can interact with the APIs as follows,

```bash
# get list of users
$ curl localhost:8080/v1/users/

# get a specific user
$ curl localhost:8080/v1/users/2

# create a user
$ curl --request POST 'localhost:8080/v1/users' --header 'Content-Type: application/json' \
--data-raw '{
	"firstName": "Tom",
	"lastName": "Cruise",
	"age": 57
}'

# edit a user
$ curl --request PUT 'localhost:8080/v1/users/1' --header 'Content-Type: application/json' \
--data-raw '{
	"firstName": "Leonardo",
	"lastName": "DiCaprio",
	"age": 46
}'

# delete a user
$ curl --request DELETE 'localhost:8080/v1/users/2'
```
