# Quotation Management
---
## Introduction
The objective of this project is create a `REST` api application using Java and Spring Boot.

This application allow the user register the price of a quotation, update that price and addicionate more quotes of a determined stock.

## Depenencies
This project needs to Docker containers to work properly.

### MySQL
To run this container you will need to execute the follow code in terminal:
```bash
docker container run -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql:8
```

### Stock-Manager
To run this container you will need to execute the follow code in terminal:
```bash
docker container run -p 8080:8080 -d lucasvilela/stock-manager
```

### Quotation Management application
Thats is the main application and after clone the project (`git clone https://github.com/dmax101/icc-idp-prova-quotation-management.git`) you have to run the follow code in terminal, but you have to garantee the containers of MySQL and Stock Manager is running.

```bash
mvn clean install
```

And:
```bash
docker build -t quotation-management.jar .
```

And after docker built you image you can run with follow command:

```bash
docker run --rm -d  -p 8081:8081/tcp quotation-management.jar:latest
```
## Usage
### Creating a Stock Quote
To create a Stock Quote you have to send a `POST` request through the path `http:\\localhost:8081\stock` with a Json body like the follow example:
```json
{
    "id": "fbdbd254-18dc-45b8-8a99-2252977c83e5",
    "stockId": "petr4",
    "quotes": {
      "2023-11-30": 100
    }
}
```
You will receive a response with the object created.

Obs.: Stock-Manager controls the valid stockId and if you whant to know if the stockId is valid you can send a `GET` requisition to the `http:\\localhost:8080\stock`. That will allow to know witch one is valid.

### List
#### List all
To List all registred Stock Quotes send a `GET` requisition to the `http:\\localhost:8080\stock`

#### List with parameters
To List a especific record you can use the `id` and `stockId` params. You just have to send a `GET` requisition just like this example: `localhost:8081/stock?id=fbdbd254-18dc-45b8-8a99-2252977c83e5&stockId=petr4`.

Obs.: You can omit one or another parameter.

### Delete cache
To delete the cache from the aplication you can send a `DELETE` requisition to the `localhost:8081/stockcache`.