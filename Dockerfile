FROM maven:3.9.6-amazoncorretto-21-al2023 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn clean package
FROM tomcat:9.0
EXPOSE 8080
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]