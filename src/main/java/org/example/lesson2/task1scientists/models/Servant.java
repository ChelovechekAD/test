package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.lesson2.task1scientists.Constants.*;

public class Servant implements Runnable {
    private Competition competition;
    private List<RobotParts> robotParts = new ArrayList<>();

    public Servant(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void run() {
        for (int i = 1; i <= NUMBER_OF_NIGHTS; i++) {
            int maxNumberOfParts = new Random().nextInt(NUMBER_OF_RANDOM_PARTS) + 1;
            for (int j = 0; j < maxNumberOfParts; j++) {
                RobotParts randomPart = competition.getPart();
                if (randomPart != null) {
                    robotParts.add(randomPart);
                }
            }
            try {
                Thread.sleep(DAY_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Servant: " + Thread.currentThread().getName() + " night №" + i);
        }
    }

    public List<RobotParts> getRobotParts() {
        return robotParts;
    }
}