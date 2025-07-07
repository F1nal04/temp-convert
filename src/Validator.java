
/**
 * Klassenname: Validator
 * Autor:        Leon Bojanowski (Kommentare mittels KI)
 * Datum:        16.06.2025
 * Beschreibung: Diese Klasse validiert die Eingaben auf Verarbeitbarkeit
 * und geforderte Daten.
 */
import java.util.Scanner;

/**
 * Eine Hilfsklasse zur Validierung von Benutzereingaben. Sie stellt sicher,
 * dass der Benutzer gültige Daten eingibt oder die Eingabe explizit abbricht.
 */
public class Validator {

    // Konstanten für den absoluten Nullpunkt zur Validierung
    private static final double ABSOLUTER_NULLPUNKT_C = -273.15;
    private static final double ABSOLUTER_NULLPUNKT_F = -459.67;
    private static final double ABSOLUTER_NULLPUNKT_K = 0.0;

    private final Scanner scanner;

    /**
     * Konstruktor, der ein Scanner-Objekt zur Wiederverwendung entgegennimmt.
     *
     * @param scanner Das Scanner-Objekt für die Konsoleneingabe.
     */
    public Validator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Liest eine Gleitkommazahl (double) vom Benutzer ein. Die Methode
     * wiederholt die Abfrage so lange, bis eine gültige Zahl eingegeben oder
     * die Eingabe mit "esc" abgebrochen wird.
     *
     * @param aufforderung Die Nachricht, die dem Benutzer angezeigt wird.
     * @return Der eingegebene Double-Wert oder null, wenn der Benutzer
     * abbricht.
     */
    public Double leseDouble(String aufforderung) {
        while (true) {
            System.out.print(aufforderung);
            String eingabe = scanner.nextLine().trim();

            if (eingabe.equalsIgnoreCase("esc")) {
                return null; // Signal für Abbruch
            }

            try {
                return Double.valueOf(eingabe.contains(",") ? eingabe.replace(',', '.') : eingabe);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Ungültige Eingabe. Bitte geben Sie eine Zahl ein (z.B. 25 oder -10,5)."
                );
            }
        }
    }

    /**
     * Liest eine Temperatureinheit (C, F, K) vom Benutzer ein. Die Methode
     * wiederholt die Abfrage so lange, bis eine gültige Einheit eingegeben oder
     * die Eingabe mit "esc" abgebrochen wird.
     *
     * @param aufforderung Die Nachricht, die dem Benutzer angezeigt wird.
     * @return Die eingegebene Einheit als String ("C", "F", "K") oder null bei
     * Abbruch.
     */
    public String leseEinheit(String aufforderung) {
        while (true) {
            System.out.print(aufforderung);
            String eingabe = scanner.nextLine().trim().toUpperCase();

            if (eingabe.equalsIgnoreCase("esc")) {
                return null; // Signal für Abbruch
            }

            if (eingabe.equals("C")
                    || eingabe.equals("F")
                    || eingabe.equals("K")) {
                return eingabe;
            } else {
                System.out.println(
                        "Ungültige Einheit. Bitte geben Sie 'C', 'F' oder 'K' ein."
                );
            }
        }
    }

    /**
     * Prüft, ob ein gegebener Temperaturwert für eine gegebene Einheit
     * physikalisch gültig ist (d.h. über dem absoluten Nullpunkt liegt).
     *
     * @param temperatur Der zu prüfende Temperaturwert.
     * @param einheit Die Einheit des Temperaturwerts ("C", "F", oder "K").
     * @return true, wenn der Wert gültig ist, sonst false.
     */
    public boolean istWertGueltig(double temperatur, String einheit) {
        return switch (einheit) {
            case "C" ->
                temperatur > ABSOLUTER_NULLPUNKT_C;
            case "F" ->
                temperatur >= ABSOLUTER_NULLPUNKT_F;
            case "K" ->
                temperatur >= ABSOLUTER_NULLPUNKT_K;
            default ->
                false; // Sollte nie erreicht werden dank Vorab-Validierung
        };
    }
}
