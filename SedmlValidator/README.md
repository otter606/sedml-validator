## SEDML validator project

This is a Spring Boot webapp to validate SED-ML files.

It requires a Java 8  to run.

### Building and running

You can checkout this project by

    https://github.com/otter606/sedml-validator.git
    
and build using Gradle wrapper:

    ./gradlew build
    
Once built, you can run the application (default port 8080) as follows:

    java -jar build/libs/sedml-validator-0.1.0.jar
    
(The version may be different in later releases)

You can combine both commands together, e.g.

    ./gradlew build && java -jar build/libs/sedml-validator-0.1.0.jar
  
