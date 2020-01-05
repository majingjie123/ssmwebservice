第一
org.apache.catalina.startup.ContextConfig.processAnnotationsJar Unable to process Jar entry
 [module-info.class] from Jar 
[file:/D:/test/ssmwebservice/target/servlet-1.0-SNAPSHOT/WEB-INF/lib/opentest4j-1.2.0.jar] for annotations
 org.apache.tomcat.util.bcel.classfile.ClassFormatException: Invalid byte tag in constant pool: 19
 添加opentest4j-1.2.0.jar
 
 kafka
 一
 打开
 C:\Windows\System32\drivers\etc
 添加查看host文件，添加映射
 192.168.0.104 majj02
 Exception in thread "main" org.apache.kafka.common.KafkaException: Failed to construct kafka producer
 	at org.apache.kafka.clients.producer.KafkaProducer.<init>(KafkaProducer.java:415)
 	at org.apache.kafka.clients.producer.KafkaProducer.<init>(KafkaProducer.java:287)
 	at TestMain.main(TestMain.java:21)
 Caused by: org.apache.kafka.common.config.ConfigException: No resolvable bootstrap urls given in bootstrap.servers