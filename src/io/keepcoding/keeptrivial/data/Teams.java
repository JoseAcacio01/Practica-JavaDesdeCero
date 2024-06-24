package io.keepcoding.keeptrivial.data;

import java.util.ArrayList;
import java.util.List;

public class Teams {
	private String Name_teams;
	private int teams_points;
	private int untrue;
	private List<String> correct_answer; // lista de respuestas correctas 
	private List<String> question_turn; // lista de preguntas por turnos
	private List<String> chosen_answer; // lista de respuesta elegida
	private List<String> correct_or_false; // lista de mensaje FALLASTE o ACERTASTE por turno
	private List<Topics> topic_list; // lista de topics
	private List<String> topics_name_in_turn; // lista del tipo de Topic por turno
	

	public List<String> getTopics_name_in_turn() {
		return topics_name_in_turn;
	}

	public void setTopics_name_in_turn(String topics_name_in_turn) {
		this.topics_name_in_turn.add(topics_name_in_turn);
	}

	public List<String> getCorrect_or_false() {
		return correct_or_false;
	}

	public void setCorrect_or_false(String valoration) {
		this.correct_or_false.add(valoration);
	}

	public void Teams() { // inicializa listas vacias para poder utilizarlas
		this.correct_answer = new ArrayList<>();
		this.question_turn = new ArrayList<>();
		this.chosen_answer = new ArrayList<>();
		this.correct_or_false = new ArrayList<>();
		this.topics_name_in_turn = new ArrayList<>();
	}
	
	public List<Topics> getTopic_list() {
		return topic_list;
	}

	public void setTopic_list(List<Topics> topic_list) {
		this.topic_list = topic_list;
	}


	public void Teams(String name) {
		Teams();
		this.Name_teams = name;
	}

	public List<String> getChosen_answer() {
		return chosen_answer;
	}

	public void setChosen_answer(String chosen_answer) {
		this.chosen_answer.add(chosen_answer); // añade la respuesta elegida a la lista chosen_answer cada turno
	}

	public List<String> getQuestion_turn() {
		return question_turn;
	}

	public void setQuestion_turn(String question_turn) {
		this.question_turn.add(question_turn); // añade la pregunta a la lista question_turn cada turno
	}

	public List<String> getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String feiled_answer) {
		this.correct_answer.add(feiled_answer); // añade la respuesta correcta a la lista correct_answer cada turno
	}

	public String getName_teams() {
		return Name_teams;
	}

	public void setName_teams(String name_teams) {
		Name_teams = name_teams;
	}

	public int getTeams_points() {
		return teams_points;
	}

	public void setTeams_points(int teams_points) {
		this.teams_points += teams_points;
	}

	public int getUntrue() {
		return untrue;
	}

	public void setUntrue(int untrue) {
		this.untrue += untrue;
	}

	public void initialazer() { // Borra todos los datos en con excepción  el nombre en caso de presionar R en el menu final
		this.teams_points = 0;
		this.untrue = 0;
		this.correct_answer = new ArrayList<>();
		this.question_turn = new ArrayList<>();
		this.chosen_answer = new ArrayList<>();
		this.correct_or_false = new ArrayList<>();
	}

}
