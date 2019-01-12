# TodoList Chat App #

Todo list chat application based on Spring boot and line java sdk.

Front end is based on React application.

## To start the server ##

From command line:
```bash
mvn spring-boot:run
```

From Docker:
```bash
mvn clean package
docker build -t todoapp .
docker run -p 8080:8080 todoapp

```

### Server configuration ###
open application.yml

```properties
app.baseurl=<server url>
app.report.job.cron=<cron scheduler to send user reports>
```

## To build frontend ##
```bash
cd src/main/resources/todofront
yarn build

```