FROM tthogho1/wildflydemo:latest

COPY /staging/demo.war /opt/wildfly/standalone/deployment/demo.war

CMD /opt/wildfly/bin/standalone.sh -b 0.0.0.0 -c standalone-ha.xml
