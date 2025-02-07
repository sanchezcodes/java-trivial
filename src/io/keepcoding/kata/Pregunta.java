package io.keepcoding.kata;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
	String title = "";
	List<String> answers = new ArrayList<String>();
	Integer rightAnswer = 0;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answer) {
		this.answers = answer;
	}

	public Integer getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(Integer rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
}
