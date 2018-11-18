FROM jboss/wildfly:latest

# DB
ENV DB_NAME AMT-DB
ENV JNDI_NAME amtProject
ENV DB_USER amt
ENV DB_PASS amt1234
ENV DB_URI db:3306

ENV MYSQL_VERSION 5.1.47
ENV JBOSS_CLI /opt/jboss/wildfly/bin/jboss-cli.sh
ENV DEPLOYMENT_DIR /opt/jboss/wildfly/standalone/deployments/

RUN echo "=> Adding WildFly administrator"
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent


# Configure Wildfly server
RUN echo "=> Starting WildFly server" && \
      bash -c '$JBOSS_HOME/bin/standalone.sh &' && \
    echo "=> Waiting for the server to boot" && \
      bash -c 'until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do echo `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null`; sleep 1; done' && \
    echo "=> Downloading MySQL driver" && \
      curl --location --output /tmp/mysql-connector-java-${MYSQL_VERSION}.jar --url http://search.maven.org/remotecontent?filepath=mysql/mysql-connector-java/${MYSQL_VERSION}/mysql-connector-java-${MYSQL_VERSION}.jar && \
    echo "=> Adding MySQL module" && \
      $JBOSS_CLI --connect --command="module add --name=com.mysql --resources=/tmp/mysql-connector-java-${MYSQL_VERSION}.jar --dependencies=javax.api,javax.transaction.api" && \
    echo "=> Adding MySQL driver" && \
                                     #/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql.driver,driver-class-name=com.mysql.jdbc.Driver)
      $JBOSS_CLI --connect --command="/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource)" && \
    echo "=> Creating a new datasource" && \
      $JBOSS_CLI --connect --command="data-source add \
        --name=${DB_NAME}DS \
        --jndi-name=java:/jdbc/${JNDI_NAME} \
        --user-name=${DB_USER} \
        --password=${DB_PASS} \
        --driver-name=mysql \
        --connection-url=jdbc:mysql://${DB_URI}/${DB_NAME} \
        --use-ccm=false \
        --max-pool-size=25 \
        --blocking-timeout-wait-millis=5000 \
        --enabled=true" && \
	echo "=> Creating a new smtp server" && \
	  $JBOSS_CLI --connect --command="/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=mail-smtp2:add(host=smtp.gmail.com, port=25)" && \
      $JBOSS_CLI --connect --command="/subsystem=mail/mail-session=smtp-server:add(jndi-name=java:/smtp-server)" && \
	  $JBOSS_CLI --connect --command="/subsystem=mail/mail-session=smtp-server/server=smtp:add(outbound-socket-binding-ref=mail-smtp2,password=somepass,ssl=false,username=user@example.com)" && \
    echo "=> Shutting down WildFly and Cleaning up" && \
      $JBOSS_CLI --connect --command=":shutdown" && \
      rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \
      rm -f /tmp/*.jar



#EXPOSE 8080 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

#ADD apps-dist/AMTProjet.war $DEPLOYMENT_DIR