FROM openjdk:17-alpine
ARG JAR_PATH=./adc/build/libs
COPY ${JAR_PATH}/demo-0.0.1-SNAPSHOT.war ${JAR_PATH}/demo-0.0.1-SNAPSHOT.war
CMD ["java","-jar","./build/libs/demo-0.0.1-SNAPSHOT.war"]
