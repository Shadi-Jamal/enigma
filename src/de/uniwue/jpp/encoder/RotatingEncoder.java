package de.uniwue.jpp.encoder;

import java.util.*;
import java.util.Map.Entry;

public class RotatingEncoder extends AbstractChainableEncoder {

	Encoder delegate;  // Der nächste Encoder
	HashMap<Character, Character> map; //Speichert das aktuelle Mapping
	int rotations; // Zählt, wie oft die Walze bereits gedreht wurde
	HashMap<Character, List<Character>>  valueToKeys = new HashMap<>();

	public RotatingEncoder(Encoder delegate) throws EncoderCreationException {
		super(delegate);
		if (delegate==null) throw new EncoderCreationException("delegate darf nivht null sein");
		this.map = new HashMap<>();
		this.delegate=delegate;
		for (char c = 'a'; c <= 'z'; c++) {
			map.put(c, c);
		}
		this.rotations = 0;

	}

	public RotatingEncoder(Encoder delegate, HashMap<Character, Character> map, int rotations) throws EncoderCreationException {
		super(delegate);
        if (map==null) throw new EncoderCreationException("map darf nicht den Wert null sein");
		if (map.size()!=26) throw new EncoderCreationException("beinhaltet nicht alle 26 Buchstaben des Alphabets");
		if (rotations<0||rotations>25) throw new EncoderCreationException("beinhaltet nicht alle 26 Buchstaben des Alphabets");
		for (char c = 'a'; c <= 'z'; c++) {
			if (!map.containsKey(c) || !map.containsValue(c)) {
				throw new EncoderCreationException("Fehlendes Zeichen im Mapping: " + c);
			}
		}
		// Überprüfen, ob die map eindeutige Werte hat
		for (Map.Entry<Character, Character> entry : map.entrySet()) {
			Character value = entry.getValue();
			Character key = entry.getKey();

			if (valueToKeys.containsKey(value)) {
				throw new EncoderCreationException("Doppelter Wert '" + value + "' gefunden bei Keys '"
						+ valueToKeys.get(value) + "' und '" + key + "'");
			}

			List<Character> keyList = new ArrayList<>();
			keyList.add(key);
			valueToKeys.put(value, keyList);
		}
		this.delegate=delegate;
		this.map=new HashMap<>(map);
		this.rotations=rotations;

	}

	//Verschlüsselt den Buchstaben mit dem aktuellen Mapping
	@Override
	public char encode(char c) throws EncoderInputException {
		if (!map.containsKey(c)) throw new EncoderInputException("Ungültiges Zeichen");

		Character c1 = map.get(c);
		if (delegate != null) {
			char delegateResult = delegate.encode(c1);
			for (char key = 'a'; key <= 'z'; key++) {
				if (map.get(key) == delegateResult) {
					return key;
				}
			}
		}
		return c1;
	}

	//Verschiebt das Mapping um eine Position nach rechts.
	//Falls carry == true, wird das Signal an den nächsten Encoder weitergeleitet.
	// Ursprünglich	  Nach 1 Rotation   	Nach 2 Rotationen
	//  a -> a             a -> b       	          a -> c
	@Override
	public void rotate(boolean carry) {
			if (carry){
				rotations = (rotations + 1) % 26;
				HashMap<Character,Character> rotateMap=new HashMap<>();
				char firstValue = map.get('a');

				for (char key = 'a'; key <= 'z'; key++) {
					char nextKey = (char) (key + 1);
					rotateMap.put(key, map.get(nextKey));
				}
				rotateMap.put('z', firstValue);
				this.map = rotateMap;
				if ( rotations == 0 && delegate != null && carry) {
					delegate.rotate(true);
				}
			}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		RotatingEncoder that = (RotatingEncoder) o;
		 return Objects.equals(map, that.map) ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(map);
	}

	@Override
	public String toString() {

		List<Character> list = new ArrayList<>(map.keySet());
		Collections.sort(list);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append("->");
			sb.append(map.get(list.get(i)));
			if (i<list.size()-1){
				sb.append("\n");
			}
		}
		return sb.toString();

	}
	public char rotateLowercaseChar(char c, int positions) {
		if (c >= 'a' && c <= 'z') {
			return (char) ((((c - 'a') + positions) % 26) + 'a');
		}
		return c;
	}
}