#!/bin/bash
#java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar target/spring-automation-framework-0.0.1-SNAPSHOT.jar --spring.config.location=driver.properties tests.xml
java -jar target/spring-automation-framework-0.0.1-SNAPSHOT.jar --spring.config.location=driver.properties tests.xml