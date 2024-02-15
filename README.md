# bankgiro-api
Library to read/write transaction files associated with the Swedish banking association Bankgirot

## Use

Releases are found in Maven-Central repository and can easliy be used by adding a dependency to you pom.xml.

    <dependency>
        <groupId>org.notima.bg</groupId>
        <artifactId>bankgiro-api</artifactId>
        <version>1.4.0</version>
    </dependency>

    
Or in karaf

	repo-add mvn:org.notima.bg/bankgiro-api/RELEASE/xml/features
	feature:install bankgiro-api

## See also

[Click here](https://www.bankgirot.se/tjanster/fakturatjanster/e-faktura-foretag/teknisk-informaton_E-faktura-foretag/) for additional information about BGC Invoice (such as technical manual, implmentation guide, example files, etc).