# Script to be run by docker's entry point - i.e. /bin/sh -c
#
# SERVER_NAME is fully qualified domain name of the website

TMPCONF=/var/tmp/config
mkdir -p $TMPCONF/WEB-INF/classes/META-INF
mkdir -p $TMPCONF/META-INF

sed -e "s#__DB_URL__#${DB_URL}#" \
    -e "s/__DB_PASSWORD__/${DB_PASSWORD}/" \
    -e "s/__DB_USERNAME__/${DB_USERNAME}/" \
    -e "s/__DB_DRIVER__/${DB_DRIVER}/" \
 /maven/persistence.xml > $TMPCONF/WEB-INF/classes/META-INF/persistence.xml

sed -e "s/__SERVER_PORT__/${SERVER_PORT:-80}/" \
    -e "s/__SERVER_NAME__/${SERVER_NAME}/" \
 /maven/ecas-config.properties > $TMPCONF/WEB-INF/classes/ecas-config.properties

sed -e "s#__DB_URL__#${DB_URL}#" \
    -e "s/__DB_PASSWORD__/${DB_PASSWORD}/" \
    -e "s/__DB_USERNAME__/${DB_USERNAME}/" \
    -e "s/__DB_DRIVER__/${DB_DRIVER}/" \
 /maven/context.xml > $TMPCONF/META-INF/context.xml

# Update the war file with the modifications and start Tomcat
( cd $TMPCONF ; zip -r $CATALINA_HOME/webapps/ROOT.war WEB-INF ; zip -r $CATALINA_HOME/webapps/ROOT.war META-INF ) && catalina.sh run
