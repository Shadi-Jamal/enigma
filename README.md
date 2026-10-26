# Enigma – Simulation in Java

Eine vereinfachte Simulation einer Enigma-Verschlüsselungsmaschine als Java-Konsolenanwendung. Texte werden mithilfe einer konfigurierbaren Kette aus festen Encodern, rotierenden Walzen und einem Reflektor ver- und entschlüsselt.

Das Projekt veranschaulicht objektorientierte Programmierung, die Verkettung von Objekten über ein gemeinsames Interface, Dateiverarbeitung und Fehlerbehandlung mit eigenen Exceptions.

## Funktionen

- Ver- und Entschlüsselung über die Konsole
- Laden der Encoder-Konfiguration aus einer `.enigma`-Datei
- Feste Buchstabenzuordnungen mit `FixedEncoder`
- Veränderliche Buchstabenzuordnungen mit `RotatingEncoder`
- Rückführung des Signals durch einen symmetrischen `Reflector`
- Prüfung von Konfigurationen und Eingaben

Nach jedem verarbeiteten Buchstaben dreht sich die erste rotierende Walze. Nach einer vollständigen Umdrehung wird die nächste rotierende Walze weitergeschaltet. Leerzeichen bleiben erhalten und lösen keine Rotation aus.

## Voraussetzungen

- Java Development Kit (JDK) ab Version 8
- Ein Terminal oder eine Java-IDE wie IntelliJ IDEA

Es werden keine externen Bibliotheken benötigt.

## Kompilieren und starten

Die folgenden Befehle im Hauptordner des Projekts ausführen:

```bash
javac -encoding UTF-8 -d out src/de/uniwue/jpp/encoder/*.java src/de/uniwue/jpp/enigma/*.java
java -cp out de.uniwue.jpp.enigma.EnigmaConsole
```

Alternativ das Projekt in IntelliJ IDEA öffnen, `src` als Quellordner markieren und die `main`-Methode in `EnigmaConsole` starten. Als Arbeitsverzeichnis den Hauptordner des Projekts verwenden.

## Verwendung

1. Die Anwendung starten.
2. Bei der Pfadabfrage `resources/config.enigma` eingeben.
3. Eine Textzeile eingeben, zum Beispiel `hallo welt`.
4. Das Ergebnis in der Konsole ablesen. Die Anwendung beendet sich anschließend.

Mit der mitgelieferten Konfiguration ergibt sich:

```text
Klartext:       hallo welt
Verschlüsselt:  eslsm ehiz
```

Zum Entschlüsseln die Anwendung erneut starten, dieselbe unveränderte Konfigurationsdatei laden und `eslsm ehiz` eingeben. Das Ergebnis lautet wieder `hallo welt`. Beide Vorgänge müssen mit denselben Buchstabenzuordnungen und Rotationszählern beginnen.

### Erlaubte Eingaben

Die Konsole akzeptiert eine Textzeile mit Kleinbuchstaben von `a` bis `z` und Leerzeichen. Großbuchstaben, Umlaute, `ß`, Zahlen und Satzzeichen werden abgewiesen.

## Konfiguration

Die Datei [`resources/config.enigma`](resources/config.enigma) enthält die Buchstabenzuordnungen der Encoder. Die mitgelieferte Konfiguration besteht aus drei festen Encodern, drei rotierenden Encodern und einem Reflektor.

Jeder Abschnitt beginnt mit einer Typangabe:

| Typangabe | Bedeutung |
| --- | --- |
| `# fixed` | Encoder mit unveränderlicher Buchstabenzuordnung |
| `# rotating 0` | Rotierender Encoder mit Rotationszähler `0`; zulässig sind Werte von `0` bis `25` |
| `# reflecting` | Reflektor am Ende der Encoder-Kette |

Unter jeder Typangabe stehen 26 eindeutige Zuordnungen im Format `a->p`, jeweils eine pro Zeile. Jeder Buchstabe von `a` bis `z` muss genau einmal als Eingabe und als Ausgabe vorkommen. Beim Reflektor muss die Zuordnung symmetrisch sein: Aus `a->t` folgt beispielsweise `t->a`.

Die Konfiguration beginnt mit einem festen Encoder. Feste und rotierende Encoder wechseln sich ab; am Ende steht genau ein Reflektor.

## Projektstruktur

```text
enigma/
├── src/de/uniwue/jpp/
│   ├── encoder/       # Encoder-Interface, Implementierungen und Exceptions
│   └── enigma/        # Konfiguration, Textverarbeitung und Konsolenoberfläche
├── resources/
│   └── config.enigma  # Beispielkonfiguration
├── .gitignore
└── README.md
```
