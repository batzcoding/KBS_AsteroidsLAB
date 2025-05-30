package dk.sdu.cbse.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoreServiceApp {

    private long score = 0;

    public static void main(String[] args) {
        SpringApplication.run(ScoreServiceApp.class, args);
    }

    @GetMapping("/score")
    public long updateScore(@RequestParam(name = "point") long points) {
        score += points;
        return score;
    }
}

