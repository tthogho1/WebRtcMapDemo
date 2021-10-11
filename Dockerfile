FROM tthogho1/wildflydemo:latest

COPY ./target/demo.war /opt/wildfly/standalone/deployments/demo.war

CMD /opt/wildfly/bin/standalone.sh -b 0.0.0.0 -c standalone-ha.xml
