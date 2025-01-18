package model;

import utils.ExchangeRateEndpoint;

import java.util.Scanner;

public class Menu {

    private final int anchoLinea = 76;// Longitud de la línea delimitadora
    private static final String DOLAR = "USD";
    private static final String PESO_ARG = "ARS";
    private static final String PESO_COLOMB = "COP";
    private static final String REAL_BRASIL = "BRL";

    // Metodo para centrar texto con base en la longitud de la línea delimitadora
    private String centrarTexto(String texto) {
        int espacios = (anchoLinea - texto.length()) / 2; // Calcular espacios a la izquierda
        return " ".repeat(Math.max(0, espacios)) + texto;  // Agregar los espacios calculados
    }


    public void mostrarMenu(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        double monto = 0;
        ExchangeRateEndpoint endpoint = new ExchangeRateEndpoint();
        ExchangeRate exchangeRate = null;

        do {
            System.out.println("\n****************************************************************************");
            System.out.println(centrarTexto("MENÚ PRINCIPAL"));
            System.out.println(centrarTexto("Sea bienvenido/a al Conversor de Monedas"));
            System.out.println("1) Dolar >>> Peso argentino");
            System.out.println("2) Peso argentino >>> Dolar");
            System.out.println("3) Dolar >>> Real brasileño");
            System.out.println("4) Real brasileño >>> Dolar");
            System.out.println("5) Dolar >>> Peso colombiano");
            System.out.println("6) Peso colombiano >>> Dolar");
            System.out.println("7) Salir");
            System.out.print("Selecciona una opción: ");

            opcion = scanner.nextInt();

            if (opcion != 7) {
                System.out.print("Ingrese el monto que desea convertir: ");
                monto = scanner.nextDouble();
            }

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la Opción 1");
                    exchangeRate = endpoint.exchangeMoneda(monto,DOLAR,PESO_ARG);
                    break;
                case 2:
                    System.out.println("Has seleccionado la Opción 2");
                    exchangeRate = endpoint.exchangeMoneda(monto,PESO_ARG,DOLAR);
                    break;
                case 3:
                    System.out.println("Has seleccionado la Opción 3");
                    exchangeRate = endpoint.exchangeMoneda(monto,DOLAR,REAL_BRASIL);
                    break;
                case 4:
                    System.out.println("Has seleccionado la Opción 4");
                    exchangeRate = endpoint.exchangeMoneda(monto,REAL_BRASIL,DOLAR);
                    break;
                case 5:
                    System.out.println("Has seleccionado la Opción 5");
                    exchangeRate = endpoint.exchangeMoneda(monto,DOLAR,PESO_COLOMB);
                    break;
                case 6:
                    System.out.println("Has seleccionado la Opción 6");
                    exchangeRate = endpoint.exchangeMoneda(monto,PESO_COLOMB,DOLAR);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            if (opcion != 7 && exchangeRate != null) {
                exchangeRate.setRate(monto);
                System.out.printf("El valor %.2f [%s] corresponde al valor final %.2f [%s]",exchangeRate.getRate(),exchangeRate.getOriginRate(),exchangeRate.getExchangeRate(),exchangeRate.getDestinationRate());
            }
            System.out.println("\n****************************************************************************");

        } while (opcion != 7);

        scanner.close();
    }

}
