FROM openjdk:8
ADD target/rangamahal.jar rangamahal.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "rangamahal.jar"]