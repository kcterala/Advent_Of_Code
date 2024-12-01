package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class AocUtils {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String BASE_URL = "https://adventofcode.com/{year}/day/{day}/input";


    public static List<String> getPuzzleInput(final int year, final int day) throws IOException, InterruptedException {
        final String session = System.getenv("AOC_SESSION");
        final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL.replace("{year}", String.valueOf(year)).replace("{day}", String.valueOf(day))))
            .header("Cookie", "session=" + session)
            .GET()
            .build();

        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        final String input = response.body();
        return Arrays.stream(input.split("\n")).toList();
    }
}
