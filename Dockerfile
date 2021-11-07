FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./target/MoexStockService-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","MoexStockService-0.0.1-SNAPSHOT.jar"]