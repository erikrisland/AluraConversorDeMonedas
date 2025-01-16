package conversorDeMonedas;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Conversor {
    private static final String CLAVE_API = "4662819e5949b6380b058c99";
    private static final String URL = "https://v6.exchangerate-api.com/v6/" + CLAVE_API + "/latest/";
    private static double tasaCambio = 0.0;

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al programa Conversor de Monedas");
        System.out.println("||||||||||||||||||||||||||||||||||||");

        while (true) {
            Menu();
            int o = scanner.nextInt();

            switch (o) {
                case 1:
                    TasaDeCambio("USD", "ARS");
                    Conversion(scanner, "USD", "ARS");
                    break;
                case 2:
                    TasaDeCambio("ARS", "USD");
                    Conversion(scanner, "ARS", "USD");
                    break;
                case 3:
                    TasaDeCambio("USD", "BRL");
                    Conversion(scanner, "USD", "BRL");
                    break;
                case 4:
                    TasaDeCambio("BRL", "USD");
                    Conversion(scanner, "BRL", "USD");
                    break;
                case 5:
                    TasaDeCambio("USD", "COP");
                    Conversion(scanner, "USD", "COP");
                    break;
                case 6:
                    TasaDeCambio("COP", "USD");
                    Conversion(scanner, "COP", "USD");
                    break;
                case 7:
                    System.out.println("Gracias por usar el conversor. ¡Nos vemos!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
            System.out.println("||||||||||||||||||||||||||||||||||||");
        }

    }

    private static void Menu() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Dólar (USD) a Peso argentino (ARS)");
        System.out.println("2. Peso argentino (ARS) a Dólar (USD)");
        System.out.println("3. Dólar (USD) a Real brasileño (BRL)");
        System.out.println("4. Real brasileño (BRL) a Dólar (USD)");
        System.out.println("5. Dólar (USD) a Peso colombiano (COP)");
        System.out.println("6. Peso colombiano (COP) a Dólar (USD)");
        System.out.println("7. Salir");
        System.out.print("Elija una opción: ");
    }

    private static void TasaDeCambio(String monedaBase, String monedaObjetivo) throws IOException, InterruptedException {
        String url = URL + monedaBase;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> respuesta = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        TasaCambio respuestaT = gson.fromJson(respuesta.body(), TasaCambio.class);

        tasaCambio = respuestaT.getTasas().get(monedaObjetivo);
        System.out.println("Tasa de cambio obtenida: 1 " + monedaBase + " = " + tasaCambio + " " + monedaObjetivo);
    }

    private static void Conversion(Scanner scanner, String monedaBase, String monedaObjetivo) {
        System.out.print("Ingrese el monto en " + monedaBase + ": ");
        double monto = scanner.nextDouble();
        double resultado = monto * tasaCambio;
        System.out.printf("El monto en %s es: %.2f%n", monedaObjetivo, resultado);
    }
}
