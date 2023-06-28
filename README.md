
# National Data Analysis

Project written in Java (with usage of Spring Boot, Spring Security, Spring Data JPA, Hibernate and other related technologies) and Python. It consists of two parts, one written in Python for fetching population data from national API, and generating estimations and the other one in Java, sharing processed data for users.




## Installation

Since project is still in development, you can download files directly by cloning the repository via HTTPS.
```http
git clone https://github.com/Thorvas/java-python-data-analysis.git
```

Spring Boot part is possible to be launched via IntellIJ.
## API Instruction

The project consists of constant number of 380 poviats updated and saved within database.

#### Retrieve all estimations
REST API for estimation retrieval is based on dynamic queries. It means that we have one endpoints with configurable results through query parameters.
```http
  GET /api/estimation
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `voivodeship` | `string` | Desired voivodeship for results |
| `poviat` | `string` | Desired poviats for results |

Example GET query:
```http
  localhost:8080/api/estimation?voivodeship=Mazowieckie&poviat=pruszkowski
```

That will allow us to retrieve list of estimation objects which have voivodeship equals to "Mazowieckie" and poviats to "pruszkowski". Since more parameters will be potentially added, it allows us customizing our results greatly.

#### Post estimation

```http
  POST /api/postEstimation
```

| Header | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `SECRET_API_KEY`      | `string` | **Required**. An API key for data posting |

JWT implementation for security is not yet implemented, but simple implementation of API key is present. Application accepts and returns JSON format. 

#### Current example posted entity:
```json
{
    "id": 1,
    "name": "Powiat ząbkowicki",
    "voivodeship": "świętokrzyskie",
    "population": {
    "2013": 67875,
    "2014": 67458,
    "2015": 66971,
    "2016": 66527,
    "2017": 66009,
    "2018": 65428,
    "2019": 64802,
    "2020": 62561,
    "2021": 61846,
    "2022": 61087
  },
  "estimation": 0,
  "last_updated": "2023-06-09"
}
```

Estimation is yet to be implemented as map constisting of analysis for upcoming years


## Authors

- [@Thorvas](https://www.github.com/Thorvas)
- [@micnow815](https://www.github.com/micnow815)

