
/**
 * Programmname: temperature-converter
 * Autor:        Leon Bojanowski (Kommentare mittels KI)
 * Datum:        16.06.2025
 * Beschreibung: Dieses Programm rechnet Temperaturen zwischen Celsius,
 * Fahrenheit und Kelvin um. Es validiert die Eingaben
 * auf physikalisch sinnvolle Werte (über dem absoluten
 * Nullpunkt).
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Validator validator = new Validator(scanner);

            anleitungAnzeigen();

            while (true) {
                // 1. Einheit einlesen und validieren
                String einheit = validator.leseEinheit(
                        "Geben Sie die Ausgangseinheit ein (C, F, K): "
                );
                if (einheit == null) { // Benutzer hat Abbruch gewählt
                    break;
                }

                // 2. Temperaturwert einlesen
                Double temperatur = validator.leseDouble(
                        "Geben Sie den Temperaturwert ein: "
                );
                if (temperatur == null) { // Benutzer hat Abbruch gewählt
                    break;
                }

                // 3. Physikalische Gültigkeit prüfen
                if (!validator.istWertGueltig(temperatur, einheit)) {
                    System.out.println(
                            "Fehler: Der Wert liegt unter dem absoluten Nullpunkt. Bitte erneut versuchen.\n"
                    );
                    continue; // Nächste Iteration der Schleife starten
                }

                // 4. Umrechnen und Ergebnisse ausgeben
                ergebnisseAnzeigen(temperatur, einheit);

                // 5. Nach weiterer Umrechnung fragen
                System.out.print(
                        "\nMöchten Sie eine weitere Umrechnung durchführen? (j/n): "
                );
                String weiter = scanner.nextLine().trim().toLowerCase();
                if (!weiter.equals("j")) {
                    break;
                }
                System.out.println(); // Leerzeile für bessere Lesbarkeit
            }

            System.out.println("Programm wird beendet. Auf Wiedersehen!");
        }
    }

    /**
     * Zeigt die Anleitung für den Benutzer an.
     */
    public static void anleitungAnzeigen() {
        System.out.println("--- Temperatur-Umrechner ---");
        System.out.println(
                "Dieses Programm rechnet einen Temperaturwert in die anderen beiden Einheiten um."
        );
        System.out.println("Gültige Einheiten sind:");
        System.out.println("  C für Celsius");
        System.out.println("  F für Fahrenheit");
        System.out.println("  K für Kelvin");
        System.out.println(
                "Hinweis: Sie können jede Eingabe durch die Eingabe von 'esc' (und Bestätigung mit Enter) abbrechen.\n"
        );
    }

    /**
     * Führt die Umrechnungen durch und gibt die Ergebnisse formatiert aus.
     *
     * @param temperatur Der ursprüngliche Temperaturwert.
     * @param einheit Die ursprüngliche Einheit.
     */
    public static void ergebnisseAnzeigen(double temperatur, String einheit) {
        System.out.println("\n--- Ergebnisse ---");
        switch (einheit) {
            case "C" -> {
                double fahrenheit = celsiusToFahrenheit(temperatur);
                double kelvin = celsiusToKelvin(temperatur);
                System.out.printf(
                        "%.2f °C sind %.2f °F\n",
                        temperatur,
                        fahrenheit
                );
                System.out.printf("%.2f °C sind %.2f K\n", temperatur, kelvin);
            }
            case "F" -> {
                double celsiusFromF = fahrenheitToCelsius(temperatur);
                double kelvinFromF = celsiusToKelvin(celsiusFromF);
                System.out.printf(
                        "%.2f °F sind %.2f °C\n",
                        temperatur,
                        celsiusFromF
                );
                System.out.printf(
                        "%.2f °F sind %.2f K\n",
                        temperatur,
                        kelvinFromF
                );
            }
            case "K" -> {
                double celsiusFromK = kelvinToCelsius(temperatur);
                double fahrenheitFromK = celsiusToFahrenheit(celsiusFromK);
                System.out.printf(
                        "%.2f K sind %.2f °C\n",
                        temperatur,
                        celsiusFromK
                );
                System.out.printf(
                        "%.2f K sind %.2f °F\n",
                        temperatur,
                        fahrenheitFromK
                );
            }
        }
    }

    // --- Umrechnungsformeln ---
    /**
     * Umrechnung von Celsius nach Fahrenheit: T(°F) = T(°C) × 9/5 + 32
     */
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }

    /**
     * Umrechnung von Celsius nach Kelvin: T(K) = T(°C) + 273,15
     */
    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    /**
     * Umrechnung von Fahrenheit nach Celsius: T(°C) = (T(°F) - 32) × 5/9
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }

    /**
     * Umrechnung von Kelvin nach Celsius: T(°C) = T(K) - 273,15
     */
    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}
