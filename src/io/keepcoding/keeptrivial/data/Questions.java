package io.keepcoding.keeptrivial.data;

import java.util.List;

public class Questions {
	private String question; // Preguntas
	private List<String> answer; // Respuestas
	private int correctask; // Numero de respuesta correcta
	
	public Questions() {
	}
	
	public Questions(String question, List<String> answer, int correctask) {
		this.question = question;
		this.answer = answer;
		this.correctask = correctask;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}

	public int getCorrectask() {
		return correctask;
	}

	public void setCorrectask(int correctask) {
		this.correctask = correctask;
	}
	
	

	
}
