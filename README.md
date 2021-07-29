# kafka-poc

Spring Boot application providing Kafka producer and consumer, validating messages through Schema Registry.

## How to start?

Start the Kafka, Zookeeper and Schema Registry services:

```bash
docker-compose up -d
```

Create the Schema Registry:

```bash
curl --location --request POST 'localhost:8081/subjects/my-topic-value/versions' \
--header 'Content-Type: application/json' \
--data-raw '{
    "references": [],
    "schemaType": "JSON",
    "schema": "{\"type\":\"object\",\"name\":\"MessageDto\",\"namespace\":\"com.co.kafkapoc.model\",\"additionalProperties\":false,\"properties\":{\"message\":{\"type\":\"string\"}},\"required\":[\"message\"]}"
}'
```

Compile the project:

```bash
mvn clean package
```

Start the application:

```bash
mvn spring-boot:run -DskipTests=true
```

## Resources

To convert JSON to String online:
https://tools.knowledgewalls.com/jsontostring

