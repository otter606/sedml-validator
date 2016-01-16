## SEDML validator project

This is a Spring Boot webapp to validate [SED-ML](http://sed-ml.org "SED-ML homepage") files.

It requires  Java 8  to run.

### Building and running

You can checkout this project from

    https://github.com/otter606/sedml-validator.git
    
and build using Gradle wrapper:

    ./gradlew build
    
Once built, you can run the application (default port 8080) as follows:

    java -jar build/libs/sedml-validator-{VERSION}.jar
    

You can combine both commands together, e.g.

    ./gradlew build && java -jar build/libs/sedml-validator-{VERSION}.jar