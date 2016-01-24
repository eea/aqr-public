# Script to be run by docker's entry point - i.e. /bin/sh -c
# /config is the mounted location of configuration files.
# SERVER_NAME is fully qualified domain name of the website

mkdir -p /config/WEB-INF/classes/META-INF

sed -e "s#__DB_URL__#${DB_URL}#" \
    -e "s/__DB_PASSWORD__/${DB_PASSWORD}/" \
    -e "s/__DB_USERNAME__/${DB_USERNAME}/" \
    -e "s/__DB_DRIVER__/${DB_DRIVER}/" \
/maven/persistence.xml > /config/WEB-INF/classes/META-INF/persistence.xml

sed -e "s/__SERVER_PORT__/${SERVER_PORT:-80}/" \
    -e "s/__SERVER_NAME__/${SERVER_NAME}/" \
 /maven/ecas-config.properties > /config/WEB-INF/classes/ecas-config.properties

# Update the war file with the modifications
( cd /config ; zip -r /usr/local/tomcat/webapps/ROOT.war WEB-INF ) && catalina.sh run
