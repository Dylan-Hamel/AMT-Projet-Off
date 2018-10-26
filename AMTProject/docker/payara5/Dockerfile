FROM payara/server-full
MAINTAINER Dylan Hamel <dylan.hamel@heig-vd.ch>

ADD ./drivers/mysql-connector-java-5.1.34.jar /opt/payara5/glassfish/lib/

RUN echo "AS_ADMIN_PASSWORD=admin" > /tmp/payaraPass.txt && \
./bin/asadmin start-domain domain1 && \
./bin/asadmin --interactive=false --user admin --passwordfile=/tmp/payaraPass.txt create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --property user=root:password=root:DatabaseName=AMT-DB:ServerName=db:port=3306 AMT-Pool && \
./bin/asadmin --interactive=false --user admin --passwordfile=/tmp/payaraPass.txt create-jdbc-resource --connectionpoolid AMT-Pool jdbc/amtProject









#RUN ./bin/asadmin stop-domain domain1


# ./bin/asadmin create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.jdb.jdbc2.optional.MysqlDataSource --property user=root:password=root:DatabaseName=AMT-DB:ServerName=db:port=3306 AMT-Pool

#  ./bin/asadmin create-jdbc-resource --connectionpoolid AMT-Pool jdbc/amtProject
