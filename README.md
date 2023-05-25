## Micronaut 3.9.2 + jpa + reactive +vert.x +cockroachdb
1. yml vert.x config 
````
   vertx:
  pg:
    client:
      port: 26257
      host: 'coarse-gosling-2563.g95.cockroachlabs.cloud'
      database: 'defaultdb'
      user: 'bbbang'
      password: "tKEYTzKI8krk0_wys_uSpg"
      url: jdbc:postgresql://coarse-gosling-2563.g95.cockroachlabs.cloud:26257/db2?verify_full
      maxSize: 5
      ssl: true 
````
2. connect cockroachdb
 GET error   Trust options must be specified under verify-full or verify-ca sslmode

````

Caused by: java.lang.IllegalArgumentException: Trust options must be specified under verify-full or verify-ca sslmode
	at io.vertx.pgclient.impl.PgConnectionFactory.initializeConfiguration(PgConnectionFactory.java:69)
	at io.vertx.sqlclient.impl.ConnectionFactoryBase.<init>(ConnectionFactoryBase.java:81)
	at io.vertx.pgclient.impl.PgConnectionFactory.<init>(PgConnectionFactory.java:50)
	at io.vertx.pgclient.spi.PgDriver.createConnectionFactory(PgDriver.java:72)
	at io.vertx.pgclient.spi.PgDriver.lambda$newPoolImpl$1(PgDriver.java:51)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.Collections$2.tryAdvance(Collections.java:4853)
	at java.base/java.util.Collections$2.forEachRemaining(Collections.java:4861)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
	at io.vertx.pgclient.spi.PgDriver.newPoolImpl(PgDriver.java:51)
	at io.vertx.pgclient.spi.PgDriver.newPool(PgDriver.java:38)
	at io.vertx.pgclient.spi.PgDriver.newPool(PgDriver.java:25)
	at io.vertx.sqlclient.spi.Driver.createPool(Driver.java:70)
	at io.micronaut.configuration.vertx.pg.client.PgDriverFactory.build(PgDriverFactory.java:67)
	at io.micronaut.configuration.vertx.pg.client.$PgDriverFactory$Build0$Definition.build(Unknown Source)
	at io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2354)
	... 69 common frames omitted
Disconnected from the target VM, address: '127.0.0.1:54035', transport: 'socket'

````

throw exception  code:
````ignorelang

    switch (sslMode) {
      case VERIFY_FULL:
        String hostnameVerificationAlgorithm = options.getHostnameVerificationAlgorithm();
        if (hostnameVerificationAlgorithm == null || hostnameVerificationAlgorithm.isEmpty()) {
          throw new IllegalArgumentException("Host verification algorithm must be specified under verify-full sslmode");
        }
      case VERIFY_CA:
        TrustOptions trustOptions = options.getTrustOptions();
        if (trustOptions == null) {//ERROR this CODE
          throw new IllegalArgumentException("Trust options must be specified under verify-full or verify-ca sslmode");
        }
        break;
    }
    
````

how can id config trustOptions ?look like this ? trust-options/pem-trust-options
i hava the root ca file: certs/ca.crt 

````ignorelang
vertx:
  pg:
    client:
      port: 26257
      host: 'coarse-gosling-2563.g95.cockroachlabs.cloud'
      database: 'defaultdb'
      user: 'bbbang'
      password: "tKEYTzKI8krk0_wys_uSpg"
      url: jdbc:postgresql://coarse-gosling-2563.g95.cockroachlabs.cloud:26257/db2?verify_full
      maxSize: 5
      ssl: true
      pem-trust-options:# ?
        ca-cert:
          path: D:/certs/root.crt
      trust-options:   # ?
        ca-cert:      #  ?
          path: D:/certs/root.crt

````
