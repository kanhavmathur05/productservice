FROM openjdk:8
ADD target/productservice-0.0.1-SNAPSHOT.jar product-service-image
EXPOSE 8081
ENTRYPOINT ["java","-jar","product-service-image"]