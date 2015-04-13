FROM java:8

MAINTAINER ProAthlete, Inc

EXPOSE 80

ADD target/ProAthlete.MicroServiceTemplate.jar /application/ProAthlete.MicroServiceTemplate.jar
ADD template.yml /application/template.yml
CMD java -jar /application/ProAthlete.MicroServiceTemplate.jar server /application/template.yml
