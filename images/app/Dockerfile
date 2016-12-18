# Begin with the 4.1.1-web version of the official Glassfish image.
FROM glassfish/server:4.1.1-web

MAINTAINER Rob Benton <rob.benton@arcourts.gov>


# We replace the jboss-logging.jar included with glassfish to fix a bug
# working with hibernate 5.
RUN rm /glassfish4/glassfish/modules/jboss-logging.jar
COPY jboss-logging-3.3.0.Final.jar /glassfish4/glassfish/modules


# Install dock-app
ENV APP=dock-app-1.0-SNAPSHOT.ear
ENV ASADMIN=/glassfish4/glassfish/bin/asadmin
COPY ../../dock-app/target/$APP /
RUN $ASADMIN deploy --name dock-app-1.0 /$APP