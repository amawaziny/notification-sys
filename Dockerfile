FROM openjdk:11-jdk-alpine
MAINTAINER amawaziny@gmail.com
COPY target/notification-sys.jar notification-sys.jar
ENTRYPOINT ["java","-jar","/notification-sys.jar"]