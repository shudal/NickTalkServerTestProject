package moe.perci.server.nicktalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NicktalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(NicktalkApplication.class, args);
    }

}
