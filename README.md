
# REST API JDBC

REST API using Spring Data JDBC.


## Authors

- [@damian](https://github.com/damian-git-99)

# 
[![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)

## Run application
- Install Mysql
- - Add your username and password in application.properties

- Create Database
```bash
CREATE DATABASE jdbc_database;
```

Run
```bash
  mvn spring-boot:run
```
App Runs on port 8080

## Run application with Docker
- Install [docker](https://www.docker.com/products/docker-desktop/)
- cd to the root of the project
- Run the command `docker-compose up -d`
- App Runs on port 8080


## API Reference

| Methods | Urls     | Actions                |
| :-------- | :------- | :------------------------- |
| `POST` | `/api/v1/users` | `create new user` |
| `GET` | `/api/v1/users` | `retrieve all users` |
| `GET` | `/api/v1/users/{id}` | `retrieve a user by id` |
| `DELETE` | `/api/v1/users/{id}` | `delete a user by id` |
| `PUT` | `/api/v1/users` | `update a user` |


## License

[MIT](https://choosealicense.com/licenses/mit/)


## Feedback

If you have any feedback please contact me damiangalvan66@gmail.com


