package mk.ukim.finki.washingmachineapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WashingMachineAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WashingMachineAppApplication.class, args);
    }

}
