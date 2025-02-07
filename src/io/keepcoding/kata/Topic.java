package io.keepcoding.kata;

import java.util.ArrayList;
import java.util.Random;

public class Topic {
    private ArrayList<Pregunta> questions = new ArrayList<>();
    String title = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Returns the list of questions.
    public ArrayList<Pregunta> getQuestionsList() {
        return questions;
    }

    // Alias method to add a question (same as addQuestion).
    public void addTopic(Pregunta question) {
        questions.add(question);
    }
    
    // Adds a question to the topic.
    public void addQuestion(Pregunta question) {
        questions.add(question);
    }

    // Retrieves a random question from the topic without removing it.
    public Pregunta getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        int index = new Random().nextInt(questions.size());
        return questions.get(index);
    }

    // Removes a question from the topic.
    public void removeQuestion(Pregunta question) {
        questions.remove(question);
    }

    // Returns a string listing all questions in the topic.
    public String getQuestions() {
        if (questions.isEmpty()) {
            return "No questions available in topic: " + title;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            Pregunta q = questions.get(i);
            sb.append((i + 1) + ". " + q.title + "\n");
        }
        return sb.toString();
    }
}