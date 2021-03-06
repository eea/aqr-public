Plans & Programmes Web DEM for AQ e-Reporting
=============================================

Original repository: https://webgate.ec.europa.eu/CITnet/stash/scm/aqrtool/aqr-public.git

Modules
-------

* aqrmodel - java library; contains the model; is responsible for the communication with the database; compiles to a JAR that is included in the aqrsystem module
* aqrsystem - webapp; containing the views and controllers

Building a Docker image
-----------------------

It is possible to build, test and push a Docker image of the Web DEM to Docker Hub. The image includes Jolokia for integration into the monitoring system. To do so you activate the `docker` profile. The `install` goal will do a test start up of the container. The `docker:push` will push the Docker image to Docker Hub.
```
cd SourceCode/aqrsystem
mvn -Pdocker clean install
mvn -Pdocker -Ddocker.image=service docker:push
```
To use `docker:push` you must have an account and add these lines to your `~/.m2/settings.xml`:
```
<server>
  <id>docker.io</id>
  <username>{account}</username>
  <password>{password}</password>
</server>
```
Note that occassionally the application is ready before the database is online. In that case use the docker:start goal.

Deployment
----------
The application is configured with environment variables. If you run it in a Docker container, then there is an example configuration at Documentation/docker-compose-example.yml.
