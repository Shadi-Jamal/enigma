package de.uniwue.jpp.encoder;

import java.util.*;

//Der Reflector ist der letzte Encoder, der das Signal zurückführt.
public class Reflector implements Encoder {

	HashMap<Character, Character> map;//Speichert die symmetrische Buchstaben-Zuordnung
	HashMap<Character, List<Character>>  valueToKeys = new HashMap<>();

	public Reflector(HashMap<Character, Character> map) throws EncoderCreationException {
		if(map==null) throw new EncoderCreationException();
		if(map.keySet().size()<26) throw new EncoderCreationException();
		for (Character c:map.keySet()){
			if (map.get(map.get(c)) != c )
				throw new EncoderCreationException("nicht symmetrisch");
		}
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
		this.map=new HashMap<>(map);
	}

	@Override
	public char encode(char c) throws EncoderInputException {
		if (map == null) {
			throw new EncoderInputException("Mapping wurde nicht initialisiert.");
		}
		if (!map.containsKey(c)) throw new EncoderInputException("Ungültiges Zeichen: " + c);
		return map.get(c);
	}

	@Override
	public void rotate(boolean carry) {
		System.out.println("Reflector kann nicht rotieren"); //Der Reflector dreht sich nicht, also passiert hier nichts.
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Reflector reflector = (Reflector) o;
		return Objects.equals(map, reflector.map) ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(map);
	}

	@Override
	public String toString() {
		//a->b
		//b->c
		//c->d
		//...
		//z->a
		List<Character> arrayList=new ArrayList<>(map.keySet());
		Collections.sort(arrayList);

		StringBuilder sb=new StringBuilder();

		for (int i = 0; i < arrayList.size(); i++) {
			sb.append(arrayList.get(i));
			sb.append("->");
			sb.append(map.get(arrayList.get(i)));
			if (i< arrayList.size()-1){
				sb.append("\n");
			}

		}
		return sb.toString();
	}

	public static boolean checkAllAlphabetsManually(Map<Character, Character> map) {
		return map.containsKey('a') &&
				map.containsKey('b') &&
				map.containsKey('c') &&
				map.containsKey('d') &&
				map.containsKey('e') &&
				map.containsKey('f') &&
				map.containsKey('g') &&
				map.containsKey('h') &&
				map.containsKey('i') &&
				map.containsKey('j') &&
				map.containsKey('k') &&
				map.containsKey('l') &&
				map.containsKey('m') &&
				map.containsKey('n') &&
				map.containsKey('o') &&
				map.containsKey('p') &&
				map.containsKey('q') &&
				map.containsKey('r') &&
				map.containsKey('s') &&
				map.containsKey('t') &&
				map.containsKey('u') &&
				map.containsKey('v') &&
				map.containsKey('w') &&
				map.containsKey('x') &&
				map.containsKey('y') &&
				map.containsKey('z');
	}
}