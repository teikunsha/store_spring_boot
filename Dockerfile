#
# Build stage
#
FROM maven AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk
COPY --from=build /target/store-0.0.1-SNAPSHOT.jar store.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","store.jar"]