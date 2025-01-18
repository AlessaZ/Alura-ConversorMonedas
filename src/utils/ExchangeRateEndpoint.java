package utils;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import model.ExchangeRate;
import model.ExchangeRateDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateEndpoint {

    static Dotenv dotenv = Dotenv.load();
    public static final String API_KEY = dotenv.get("API_KEY");

    public ExchangeRate exchangeMoneda(double monto, String monedaOrigen, String monedaDestino){

        String endpoint = "https://v6.exchangerate-api.com/v6/"+API_KEY+"/pair/"+monedaOrigen+"/"+monedaDestino+"/"+monto;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            ExchangeRateDTO exchangeRateDTO = new Gson().fromJson(response.body(), ExchangeRateDTO.class);
            return new ExchangeRate(exchangeRateDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
