package it.almaviva.cassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableCassandraRepositories
@EnableJms
public class CassandraApplication {
    public static void main(String[] args) {
        SpringApplication.run(CassandraApplication.class, args);
    }
}
