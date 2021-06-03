# bankgiro-api
Library to read/write transaction files associated with the Swedish banking association Bankgirot

## Use

Releases are found in Maven-Central repository and can easliy be used by adding a dependency to you pom.xml.

    <dependency>
        <groupId>org.notima.bg</groupId>
        <artifactId>bankgiro-api</artifactId>
        <version>1.3.7</version>
    </dependency>

    
Or in karaf

    install -s mvn:org.apache.httpcomponents/httpcore-osgi/4.4.12
    install -s mvn:org.apache.httpcomponents/httpclient-osgi/4.5.10
	install -s mvn:org.notima/util
	install -s mvn:org.notima.bg/bankgiro-api