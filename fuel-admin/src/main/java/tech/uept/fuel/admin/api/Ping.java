package tech.uept.fuel.admin.api;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ping implements CommandLineRunner {

    private String pong;

    @GetMapping("/ping")
    public String pong() {
        return pong;
    }

    @Override
    public void run(String... args) throws Exception {
        pong = "pong! start time is " + DateFormat.getDateTimeInstance().format(new Date());
    }
}
