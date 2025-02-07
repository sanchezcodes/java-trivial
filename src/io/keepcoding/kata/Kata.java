package io.keepcoding.kata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Kata {
    
    public static Equipo teamA;
    public static Equipo teamB;

    public static void main(String[] args) {
        // Load topics (each containing several questions)
        ArrayList<Topic> topics = getQuestions();
        if (topics.isEmpty()) {
            title("No hay preguntas disponibles");
            return;
        }
        
        // Initialize teams
        title("Selecciona el nombre de los equipos");
        initializeTeams();
        
        Scanner scanner = new Scanner(System.in);
        Equipo currentTeam = teamA;
        boolean gameOver = false;
        
        while (!gameOver) {
            // Select a topic that still has questions
            Topic selectedTopic = null;
            for (Topic topic : topics) {
                if (!topic.getQuestionsList().isEmpty()) {
                    selectedTopic = topic;
                    break;
                }
            }
            if (selectedTopic == null) {
                title("No quedan preguntas, fin del juego");
                break;
            }
            
            // Get a random question from the selected topic
            Pregunta question = selectedTopic.getRandomQuestion();
            if (question == null) {
                continue;
            }
            
            // Display question and answers
            System.out.println("Turno del equipo: " + currentTeam.getTeamName());
            System.out.println("Pregunta: " + question.title);
            for (int i = 0; i < question.answers.size(); i++) {
                System.out.println((i + 1) + ". " + question.answers.get(i));
            }
            System.out.print("Tu respuesta: ");
            String input = scanner.nextLine();
            if (!esTransformableAEntero(input)) {
                System.out.println("Entrada inválida, intenta de nuevo.");
                continue;
            }
            int answer = Integer.parseInt(input);
            if (answer == question.rightAnswer) {
                System.out.println("¡Correcto!");
                // Award a quesito if possible
                int awarded = currentTeam.awardQuesito();
                if (awarded != -1) {
                    System.out.println("Has obtenido el quesito: " + awarded);
                }
                System.out.println(currentTeam.displayQuesitos());
            } else {
                System.out.println("Incorrecto. La respuesta correcta es: " + question.rightAnswer);
            }
            
            // Remove the question to avoid repetition
            selectedTopic.removeQuestion(question);
            
            // Check winning condition: team has all 5 quesitos
            if (currentTeam.hasAllQuesitos()) {
                title("Ha ganado el equipo: " + currentTeam.getTeamName());
                gameOver = true;
                break;
            }
            
            // Switch turn to the other team
            currentTeam = (currentTeam == teamA) ? teamB : teamA;
        }
    }
    
    public static void initializeTeams() {
        Scanner s = new Scanner(System.in);
        title("Ahora el nombre del equipo A");
        teamA = new Equipo();
        teamA.setTeamName(s.nextLine());
        title("Ahora el nombre del equipo B");
        teamB = new Equipo();
        teamB.setTeamName(s.nextLine());
    }
    
    public static void title(String text) {
        int length = text.length();
        printHashtagLine(length + 4);
        System.out.println("# " + text + " #");
        printHashtagLine(length + 4);
    }
    
    public static void printHashtagLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
    
    public static boolean esTransformableAEntero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static ArrayList<Topic> getQuestions() {
        ArrayList<Topic> list = new ArrayList<>();
        File folder = new File("questions");
        if (!folder.exists()) {
            title("Error al cargar el fichero");
        } else {
            File[] filesList = folder.listFiles();
            for (File file : filesList) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    String topicName = file.getName().substring(0, file.getName().length() - 4);
                    Topic topic = new Topic();
                    topic.setTitle(topicName);
                    System.out.println("Cargando tema: " + topicName);
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        List<String> block = new ArrayList<>();
                        while ((line = br.readLine()) != null) {
                            block.add(line);
                            if (block.size() == 6) { // Each question has 6 lines
                                Pregunta question = new Pregunta();
                                question.title = block.get(0);
                                question.answers = new ArrayList<>();
                                // Lines 2 to 5 are the answer options
                                question.answers.add(block.get(1));
                                question.answers.add(block.get(2));
                                question.answers.add(block.get(3));
                                question.answers.add(block.get(4));
                                question.rightAnswer = Integer.parseInt(block.get(5));
                                topic.addQuestion(question);
                                block.clear();
                            }
                        }
                        list.add(topic);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }
}