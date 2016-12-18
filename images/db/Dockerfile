# Begin with the mysql 5.7.17 image.
FROM mysql:5.7.17

MAINTAINER Rob Benton <rob.benton@arcourts.gov>


# Add custom config
#
ENV CFG=custom-config.cnf
COPY $CFG /etc/mysql/conf.d


# Copy initialization files.
# 
ENV SQL=create_table.sql
COPY $SQL /docker-entrypoint-initdb.d
