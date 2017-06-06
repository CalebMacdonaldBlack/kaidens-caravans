FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/kaidens-caravans.jar /kaidens-caravans/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/kaidens-caravans/app.jar"]
