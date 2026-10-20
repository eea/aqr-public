FROM tomcat:6

RUN mkdir -p ${CATALINA_HOME}/lib/org/apache/catalina/authenticator \
    && mkdir -p ${CATALINA_HOME}/lib/org/apache/catalina/startup

COPY ServerConfiguration/ecas/tomcat/org/apache/catalina/authenticator/mbeans-descriptors.xml ${CATALINA_HOME}/lib/org/apache/catalina/authenticator/mbeans-descriptors.xml
COPY ServerConfiguration/ecas/tomcat/org/apache/catalina/startup/Authenticators.properties ${CATALINA_HOME}/lib/org/apache/catalina/startup/Authenticators.properties
COPY ServerConfiguration/ecas/tomcat/ecas-tomcat-5.5-3.6.3.jar ${CATALINA_HOME}/lib/ecas-tomcat-5.5-3.6.3.jar
COPY ServerConfiguration/ecas/tomcat/log4j-1.2.17.jar ${CATALINA_HOME}/lib/log4j-1.2.17.jar
COPY ServerConfiguration/log4j/tomcat-juli-adapters.jar ${CATALINA_HOME}/lib/tomcat-juli-adapters.jar
COPY ServerConfiguration/log4j/tomcat-juli.jar ${CATALINA_HOME}/lib/tomcat-juli.jar
COPY ServerConfiguration/log4j/log4j.properties ${CATALINA_HOME}/lib/log4j.properties

RUN rm -rf ${CATALINA_HOME}/conf/logging.properties ${CATALINA_HOME}/webapps/*
COPY docker/server.xml ${CATALINA_HOME}/conf/server.xml
COPY SourceCode/aqrsystem/target/aqrsystem.war ${CATALINA_HOME}/webapps/ROOT.war