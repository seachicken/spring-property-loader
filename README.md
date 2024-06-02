# spring-property-loader

Load property values from the Spring Boot application.

## Usage

Get jar from [releases](https://github.com/seachicken/spring-property-loader/releases) and run

```
java -jar spring-property-loader.jar
```

## Message format

spring-property-loader listens on `stdin` and writes messages back to `stdout`.

**stdin:**

```json
{"from":"./your/spring-module/root-path","profiles":["prod"]}
```

| Key | Requirement | Description |
| ---- | ----------- | ----------- |
| `from` | required | Path of spring project to be retrieved |
| `profiles` | optional | Filter the profiles to be retrieved |

**stdout:**

Return flat keys and values per property file.

```json
[{"spring.profiles":"prod","server.port":"8081"}]
```
