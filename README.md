## Basic Autocomplete with elasticsearch

- Start infra

```
docker-compose up -d
```

- Run the application to populate data

```
./mvnw spring-boot:run
```

- Curl gets endpoint to get data. Search by author start with i

```
http://localhost:8881/history?author=i
```

- Install chrome extension for elasticsearch
ElasticSearch Head 
https://chrome.google.com/webstore/detail/elasticsearch-head/ffmkiejjmecolpfloofpjologoblkegm





