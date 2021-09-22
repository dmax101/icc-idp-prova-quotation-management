FROM openjdk:11
EXPOSE 8081
ADD target/quotation-management.jar quotation-management.jar
ENTRYPOINT ["java","-jar","quotation-management.jar"]