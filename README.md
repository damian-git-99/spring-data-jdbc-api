
# REST API JDBC

REST API using Spring Data JDBC.


## Authors

- [@damian](https://github.com/damian-git-99)


# 
[![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)



## Installation

Create Database

```bash
CREATE DATABASE jdbc_database;
```

Create Table
```bash
CREATE TABLE `jdbc_database`.`users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NULL,
    `email` VARCHAR(45) NULL,
    `phone` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);
```

Run application
```bash
  mvn spring-boot:run
```

    
## Running Tests

To run tests, run the following command

```bash
  mvn test
```


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


