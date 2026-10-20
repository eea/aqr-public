Plans & Programmes Web DEM for AQ e-Reporting
=============================================

Original repository: https://webgate.ec.europa.eu/CITnet/stash/scm/aqrtool/aqr-public.git

Modules
-------

* aqrmodel - java library; contains the model; is responsible for the communication with the database; compiles to a JAR that is included in the aqrsystem module
* aqrsystem - webapp; containing the views and controllers

Build the application
---------------------
```
cd SourceCode/aqrsystem
mvn -Dmaven.test.skip=true clean install
```

Run the unit tests
------------------
```
cd SourceCode/aqrsystem
mvn test
```

Building a Docker image
-----------------------

It is possible to build and push a Docker image of the Web DEM to Docker Hub.
Build the application as already described and then create a docker image by executing the docker build command in the root directory containing the Dockerfile. 
If no tag is provided then the image will be tagged as "latest".
```
docker build -t eeacms/aqrsystem:image-tag .
```
In order to push the image you have created to DockerHub run:
```
docker push eeacms/aqrsystem:image-tag
```

Run locally
----------------

You can use docker-compose to run the application locally:
```
docker compose -f docker-compose-local.yml up
```