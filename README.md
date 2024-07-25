## Spring Boot based Kafka consumer using SSL Certificate authentication
This is a sample code in Spring Boot to build Kafka consumer that uses SSL Certificate authentication. It uses `SslBundles` to build Kafka Consumer using SSL Certificates

### How to setup
- Have the Client's SSL Key and Certificate in pem format
- Provide the Key and Certificate file path in properties file
- The CA certificate should be available either as a file to be referred in properties file or should be installed in JREs cacerts

In this sample the pem files are assumed to be available in the classpath