FROM tomcat:8.5-alpine
ADD target/usermanager.war /usr/local/tomcat/webapps
EXPOSE 8092
CMD ["catalina.sh", "run"]
