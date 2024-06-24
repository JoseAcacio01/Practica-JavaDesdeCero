package io.keepcoding.keeptrivial.data;

import java.util.ArrayList;
import java.util.List;

public class Topics {	
	private String topic; // Nombre de Topic
    private List<Questions> questions; // lista de preguntas
    private int topic_points; // puntos Obtenidos por Topic
    
 
	public int getTopic_points() {
		return topic_points;
	}

	public void setTopic_points(int topic_points) {
		this.topic_points += topic_points;
	}

	public Topics() {
		this.topic_points = 0;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
	
	public void initialazer() {  // Borra todos los puntos del Topic en caso de precinar R en el menu final
		this.topic_points = 0;
	}
	

}