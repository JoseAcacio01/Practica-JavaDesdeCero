package io.keepcoding.keeptrivial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import io.keepcoding.keeptrivial.data.Questions;
import io.keepcoding.keeptrivial.data.Teams;
import io.keepcoding.keeptrivial.data.Topics;

public class MainTrivial {
	public static void main(String[] args) {

		// initialize teams
		ArrayList<Teams> teams_num = new ArrayList<>();
		Scanner input = new Scanner(System.in);

		// VARIABLES FIJAS
		boolean exit = false; // Esta variable mantiene el funcionamient en el do while principal
		String t_name; // Se inicializa el nombre del equipo vacio para despues rellenarlo cuando se creen 
		int turn = 1; // int que mantiene el bucle de los turnos 
		int point_of_victory = 0; // Quesitos necesario para ganar despues al introducirlo en el menu cambiara al numero deceado, siempre y cuando este bajo los parametros 
		Teams team_win = null; // guarda al quipo ganador 
		String readline;
		String res_ops = ""; // respuesta introduccida por los jugadores, esta inizializada aqui para ir cambiando su valor desde dentro del bucle 
		

		title("INICIO DEL JUEGO");

		do {
			System.out.print("Introduzca un nombre o S para salir: ");
			t_name = input.nextLine(); // se pide el nombre del equipo asignandole el valor a t_name

			if (t_name.isEmpty()) { // gestiona que el nombre no este en blanco 
				System.out.println("Error, No se pueden colocar nombres en blanco");
			}

			if (!t_name.equalsIgnoreCase("S") && !t_name.isEmpty()) { // gestiona que el nombre no este en blanco y que mientra no sea (S) se pueda crear el equipo 
				Teams teams = new Teams();
				teams.Teams(t_name);
				teams.setTopic_list(getQuestions());
				teams_num.add(teams); // añade el quipo a una lista tipo Teams para ir creando varios equipos y se facilite la iteracion con estos
			}
			if (teams_num.isEmpty()) { // gestiona que almenos haya un equipo, viendo que la lista no este vacia 
				System.out.println();
				System.out.println("Se tiene que colocar al menos un equipo para jugar");
				System.out.println();
				t_name = "a";
			}

		} while (!t_name.equalsIgnoreCase("S")); // solo se sale t_name es S

		do {
			
			System.out.print("Seleccione el numero nesesario de quesitos para ganar, el minimo de quesitos son dos: ");
			readline = input.nextLine();
			if (esTransformableAEntero(readline)) {
				int points = Integer.parseInt(readline);
				if (points > 1) {
					point_of_victory = points;
				} else {
					System.out.println("Seleccione un numero mayor a 1");
				}
			} else {
				System.out.println("No se permiten letras solo numeros");
			}
		} while (point_of_victory == 0);

		do {
			if (!res_ops.equalsIgnoreCase("T")) { // Esta condicion If es para que no se repita el juego y solo se muestre la tabla de puntajes
				do {

					System.out.println();
					System.out.println("TABLA DE PUNTAJES: ");

					for (Teams name : teams_num) {
						System.out.println("Equipo: " + name.getName_teams() + " puntos: " + name.getTeams_points());
					}

					System.out.println();

					int points = 0; // Se define una variable points para ir comparando los puntos de los equipos para ganar 

					Teams teams = teams_num.get(turn - 1);
					title("TURNO DEL EQUIPO: " + teams.getName_teams());
					System.out.println();
					
					List<Topics> topic_list = teams.getTopic_list(); // Bucle que imprime los Topics
					int ntopic = getRandomInt(topic_list.size());
					Topics topic = topic_list.get(ntopic);
					System.out.println("Topic Sobre " + topic.getTopic());
					List<Questions> questions = topic.getQuestions();
					int nasn = getRandomInt(questions.size());
					Questions question = questions.get(nasn);
					System.out.println(question.getQuestion());
					int n = 1;
					for (String answer : question.getAnswer()) {
						System.out.println(n++ + ". " + answer);
					}
					
					teams.setQuestion_turn(question.getQuestion());

					System.out.println();
					int res_answer = 0;
					int Correct = question.getCorrectask();
					
					System.out.print("Introduzca su respuesta: ");
					readline = input.nextLine();
					if (esTransformableAEntero(readline)) { // validacion que confirma si la respuesta dada se puede convertir en un int
						res_answer = Integer.parseInt(readline);
						if (res_answer < 1 || res_answer > 4) { // valida si la respuesta esta entre en el rango numerico de las opciones 
							System.out.println("Opcion no valida, la respuesta debe ser un valor numerico del 1 al 4");
							res_answer = 5;
						}
					} else {
						res_answer = 5;
						System.out.println("Opcion no valida, la respuesta debe ser un valor numerico del 1 al 4");
					}
					// En dado caso de que la opcion no cumpla con estas condiciones la respuesta sera invalidada, por lo que se le asigna el numero 5 a res_answer como parametro para invalidarla 

					System.out.println();

					// IF ELSE: PREGUNTA CORRECTA O FALLADA
					if (question.getCorrectask() == res_answer) { // cuando es correcta la respuesta se guarda lo siguinte en el objeto Teams: la respuesta correcta, la elegida, se le suma un punto al team, El topic de la lista, para mostrarlo en el tablero de puntajes
						title(congratulations());
						topic.setTopic_points(1);
						teams.setTeams_points(1);
						teams.setTopics_name_in_turn(topic.getTopic());
						teams.setCorrect_answer("RESPUESTA CORRECTA: " + (question.getAnswer()).get(res_answer - 1));
						teams.setChosen_answer("RESPUESTA ELEGIDA: " + (question.getAnswer()).get(res_answer - 1));
						teams.setCorrect_or_false("¡¡ACERTASTE!!: ");
					} else { // cuando es fallida la respuesta se guarda lo siguinte en el objeto Teams: la respuesta correcta, la elegida, se le suma un punto fallido al team, El topic de la lista, para mostrarlo en el tablero de puntajes
						title(failed());
						teams.setUntrue(1);
						teams.setCorrect_answer("RESPUESTA CORRECTA: " + (question.getAnswer()).get(Correct - 1));
						teams.setTopics_name_in_turn(topic.getTopic());
						if (res_answer != 5) { // cuando res_asnwer es 5 invalida la respuesta y guarda un mensaje para mostrarlo en el tablero de puntajes 
							teams.setChosen_answer("RESPUESTA ELEGIDA: " + (question.getAnswer()).get(res_answer - 1));
						} else {
							teams.setChosen_answer("RESPUESTA ELEGIDA: No valida");
						}
						teams.setCorrect_or_false("FALLASTE: ");
					}
					

					points = teams.getTeams_points(); // Se le va asignando los puntos que llevan los equipos para luego compararlos con los puntos necesarios para ganar 

					if (points == point_of_victory) { // Ciclo que mantienen los turnos 
						team_win = teams;
						exit = true;
					} else {
						if (turn == teams_num.size()) {
							turn = 1;
						} else {
							turn++;
						}
					}

				} while (!exit);
			}
			if (exit) {
				System.out.println();
				title("¡¡HAN GANADO, FELICIDADES!!: " + team_win.getName_teams());
				System.out.println();

			}

			boolean validMenu = false;
			do {
				System.out.println();
				System.out.println("## PARA VER TABLA DE PUNTAJES, PULSE T ##");
				System.out.println("## PARA JUGAR DE NUEVO, PULSE R ##");
				System.out.println("## PARA SALIR DEL JUEGO, PULSE X ##");
				System.out.print("Selecciona Opcion: ");
				res_ops = input.next();
				switch (res_ops.toUpperCase()) { // valida que la opcion pulsada sea una de las presentadas (T,R,X)
				case "T":
					validMenu = true;
					break;
				case "R":
					validMenu = true;
					break;
				case "X":
					System.out.println();
					validMenu = true;
					break;
				default:
					System.out.print("Opcion Invalida");
					break;
				}
			} while (!validMenu);

			if (res_ops.equalsIgnoreCase("T")) { // Cuando res_ops es T entra aqui
				exit = false;
				System.out.println();
				title("TABLA DE PUNTUACIONES");
				for (Teams teams_result : teams_num) { // imprime lo que se guardo antriormente en el IF ELSE: PREGUNTA CORRECTA O FALLADA
					System.out.println();
					title("PUNTAJE EQUIPO: " + teams_result.getName_teams());
					System.out.println(" PUNTOS OBTENIDOS: " + teams_result.getTeams_points());
					System.out.println(" PUNTOS PERDIDOS: " + teams_result.getUntrue());
					System.out.println();
					
					System.out.println("PUNTAJES EN TOPICS:" );
					List<Topics> topic_list_print = teams_result.getTopic_list();
					for (Topics topics : topic_list_print) {
						System.out.println(topics.getTopic() + ", Puntos: " + topics.getTopic_points());
					}
					System.out.println();
	
					
					
					int num_turn = 0;
					for (int i = 0; i < teams_result.getQuestion_turn().size(); i++) { // imprime lo que se guardo antriormente en el IF ELSE: PREGUNTA CORRECTA O FALLADA
						num_turn += 1;
						System.out.println();
						System.out.println("TURNO " + num_turn);
						System.out.println();
						
						System.out.println(teams_result.getCorrect_or_false().get(i));
						System.out.println("TOPIC DE: " + teams_result.getTopics_name_in_turn().get(i));
						System.out.println("PREGUNTA: " + teams_result.getQuestion_turn().get(i));
						System.out.println(teams_result.getChosen_answer().get(i));
						System.out.println(teams_result.getCorrect_answer().get(i));

					}
				}

			}

			if (res_ops.equalsIgnoreCase("R")) { // Cuando res_ops es R entra aqui donde se cambia exit a false para repetir el do While del juego, tambien se limpian los puntajes y mensajes guardados en Teams
				exit = false;
				turn = 1;
				team_win = null;
				for (Teams teams : teams_num) {
					teams.initialazer();
					List<Topics> topic_list = teams.getTopic_list();
					for(Topics topic : topic_list) {
						topic.initialazer();
					}
					
				}

			}

		} while (!res_ops.equalsIgnoreCase("X")); // Cuando res_ops es X se sale del bucle y acaba el juego

		title("FINAL DEL JUEGO, GRACIAS POR JUGAR");

	}

	// METODOS ZONE

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

	public static String congratulations() { // Metodo para retornar Strings de Felicitaciones aleatrios
		ArrayList<String> congratulations_ops = new ArrayList<>();
		congratulations_ops.add("¡¡FELICIDADES ACERTASTE!!");
		congratulations_ops.add("¡¡MUY BIEN LA RESPUESTA ES CORRECTA!!");
		congratulations_ops.add("¡¡ACERTASTE, ERES UNA MAQUINA!!");

		int num_ramdom = getRandomInt(congratulations_ops.size());

		String phrase = congratulations_ops.get(num_ramdom);
		return phrase;
	}

	public static String failed() { // Metodo para retornar Strings con mensajes aleatrios para las respuestas fallidas
		ArrayList<String> congratulations_ops = new ArrayList<>();
		congratulations_ops.add("Fallaste, Mas suerte para la proxima");
		congratulations_ops.add("Respuesta incorrecta, no te rindas!!");
		congratulations_ops.add("Respuesta errónea, concentrate");

		int num_ramdom = getRandomInt(congratulations_ops.size());

		String phrase = congratulations_ops.get(num_ramdom);
		return phrase;
	}

	private static int getRandomInt(int max) {
		return new Random().nextInt(max);
	}

	private static ArrayList<Topics> getQuestions() {
		ArrayList<Topics> list = new ArrayList<>(); 

		File folder = new File("questions");
		if (!folder.exists()) {
			title("Error al cargar el fichero");
		} else {
			File[] filesList = folder.listFiles();

			for (File file : filesList) {
				if (file.isFile() && file.getName().endsWith(".txt")) {
					var topicName = file.getName().substring(0, file.getName().length() - 4);

					Topics topic = new Topics(); // Se inicializa el objeto Topics donde se guardaran una lista de objetos Questions, el nombre del topic, y los puntos de las respuestas correctas
					topic.setTopic(topicName);

					List<Questions> questions = new ArrayList<>();

					try (BufferedReader br = new BufferedReader(new FileReader(file))) {
						String line;
						List<String> block = new ArrayList<>();

						while ((line = br.readLine()) != null) {
							block.add(line);

							if (block.size() == 6) {
								var question = block.get(0);
								var answer1 = block.get(1);
								var answer2 = block.get(2);
								var answer3 = block.get(3);
								var answer4 = block.get(4);
								var rightOption = Integer.parseInt(block.get(5));

								// TODO create question
								Questions question_objet = new Questions(question,
										List.of(answer1, answer2, answer3, answer4), rightOption);
								questions.add(question_objet); // se inixializa y se guardan las preguntas, las opciones (se guarden en forma de lista) y el numero de la respuesta correcta

								block.clear();
							}
						}

						topic.setQuestions(questions); // En esta parte se añade el Objeto Questions al Topic, el topic se separa en estas dos partes para manejar mejor el flujo de datos
						list.add(topic); // Aqui se añaden los topics ya hechos a la lista que vamos a devolver

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}

		return list;
	}

}
