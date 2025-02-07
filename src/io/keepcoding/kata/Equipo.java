package io.keepcoding.kata;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Equipo {
    String teamName;
    private int points = 0;
    // A set to store unique quesitos (numbers 1-5)
    private Set<Integer> quesitos = new HashSet<>();

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void addPoint() {
        points++;
    }

    public int getPoints() {
        return points;
    }
    
    /**
     * Awards a random quesito (1 to 5) that the team does not already have.
     * @return the awarded quesito number or -1 if the team already has all quesitos.
     */
    public int awardQuesito() {
        if (quesitos.size() >= 5) {
            return -1; // All quesitos already acquired.
        }
        Random random = new Random();
        int quesito;
        do {
            quesito = random.nextInt(5) + 1; // Generates a number between 1 and 5.
        } while (quesitos.contains(quesito));
        quesitos.add(quesito);
        return quesito;
    }
    
    /**
     * Checks if the team has collected all 5 quesitos.
     * @return true if all quesitos have been collected, false otherwise.
     */
    public boolean hasAllQuesitos() {
        return quesitos.size() == 5;
    }
    
    /** 
     * Provides a visual representation of the team's quesitos.
     * For each quesito from 1 to 5, if owned, it is shown in brackets, else empty brackets.
     * @return a string representing the current state of quesitos.
     */
    public String displayQuesitos() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quesitos: ");
        for (int i = 1; i <= 5; i++) {
            sb.append(quesitos.contains(i) ? "[" + i + "] " : "[ ] ");
        }
        return sb.toString();
    }
    
    /**
     * Optionally, a getter for the raw set of quesitos if needed.
     */
    public Set<Integer> getQuesitos() {
        return quesitos;
    }
}