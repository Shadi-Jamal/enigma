package de.uniwue.jpp.enigma;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.in;

public class EnigmaConsole {
	enum State {
		S0,
		S1,
		S2,
		S3,
		S4,
		S5,
		S6;
	}


	public static void main(String[] args) {
		EnigmaConsole console = new EnigmaConsole();
		InputStream inputStream = in;
		OutputStream outputStream = System.out;
		console.run(inputStream, outputStream);
	}

	public void run(InputStream is, OutputStream os) {
		PrintWriter writer = new PrintWriter(os, true);
		Scanner sc = new Scanner(is);
		//String filePath = sc.nextLine().trim();
		//File file = new File(filePath);
		State currentState = State.S0;
		String text = null;
		Enigma enigma = null;
		String input = null;

		while (true){
			switch (currentState){
				case S0:
					writer.println("Enigma wird gestartet.");
					currentState = State.S1;
					break;

				case S1 :
					writer.println("Bitte geben Sie den Pfad zu einer Konfiguration an:");
					currentState = State.S2;
					break;

				case S2:
					String filePath = sc.nextLine().trim();
					File file = new File(filePath);

					if (!file.exists()) {
						writer.println("Datei nicht gefunden!");
						currentState=State.S1;
						break;
					}
					try {
						FileInputStream fileInput = new FileInputStream(file);
						enigma = new Enigma(fileInput);
						fileInput.close();
						currentState = State.S3;
					} catch (Exception e) {
						writer.println("Fehlerhafte Enigma-Datei!");
						currentState = State.S1;
					}
					break;

				case S3:

					writer.println("Geben Sie ihren Ver- oder Entschlüsselten Text ein:");
					currentState = State.S4;
					break;

				case S4:
					input = sc.nextLine();
					try {
						ByteArrayInputStream textInput = new ByteArrayInputStream(input.getBytes());
						text = enigma.encrypt(textInput);
						currentState = State.S5;
					} catch (Exception e) {
						writer.println("Nicht erlaubte Symbole!");
						currentState = State.S3;
					}
					break;

				case S5:
					writer.println("En- oder Dekodierter Text:" + text);
					writer.println("Vielen Dank für die Nutzung der Enigma.");
					currentState = State.S6;
					break;


				case S6:
					return;

			}

		}
	}

}



