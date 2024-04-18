# Rinha de Backend - 2023 Q3
 
Desafio original:
https://github.com/zanfranceschi/rinha-de-backend-2023-q3


## ✔️ Required
* Java version: 17
* Docker version: 24.0.5
* Docker-compose version: 1.29.2


## 💻 Getting started

### Stress Test
```bash
docker-compose up -d --build --force-recreate
sleep 30
./run-gatling.sh
```



### Local Execution
```bash
# Spin up database
$ docker-compose up -d --build db

# Start application
$ ./gradlew bootRun
```
