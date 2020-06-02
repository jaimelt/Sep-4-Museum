import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoRaClient implements WebSocket.Listener {
    private final IDatabase database = new MongoDbDatabase();
    private final String TOKEN = "wss://iotnet.teracom.dk/app?token=vnoSvwAAABFpb3RuZXQudGVyYWNvbS5ka14S7zZXBMiAAcsYgh0N79M=";
    private final Logger LOGGER = Logger.getLogger(LoRaClient.class.getName());
    private FileHandler fileHandler;

    public LoRaClient() {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(TOKEN), this);
        try {
            fileHandler = new FileHandler("bridge-app.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.addHandler(fileHandler);
        fileHandler.setFormatter(new SimpleFormatter());
    }

    //onOpen()
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        LOGGER.info("WebSocket Listener has been opened for requests.");
    }

    //onError()
    public void onError​(WebSocket webSocket, Throwable error) {
        LOGGER.severe("A " + error.getCause() + " exception was thrown.");
        LOGGER.severe("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }

    //onClose()
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        LOGGER.warning("WebSocket closed!");
        LOGGER.warning("Status:" + statusCode + " Reason: " + reason);
        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    //onPing()
    public CompletionStage<?> onPing​(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        LOGGER.info("Ping: Client ---> Server");
        LOGGER.info(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    //onPong()
    public CompletionStage<?> onPong​(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        LOGGER.info("Pong: Client ---> Server");
        LOGGER.info(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    //onText()
    public CompletionStage<?> onText​(WebSocket webSocket, CharSequence data, boolean last) {
        LOGGER.info("Received data: " + data);
        webSocket.request(1);
        var dataAsString = data.toString();
        parseAndInsertData(dataAsString);
        return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
    }

    private void parseAndInsertData(String jsonTelegram) {
        LOGGER.info("Received json telegram: " + jsonTelegram);

        var parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(jsonTelegram);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (json == null) {
            LOGGER.severe("Failed to parse json telegram");
            return;
        }

        if (!json.get("cmd").equals("rx")) return;

        var dataAsHex = (String) json.get("data");

        if (dataAsHex == null) return;

//        spit data string every 4 characters
        String[] measurementsAsHex = dataAsHex.split("(?<=\\G....)");

        int humidityAsInt = Integer.parseInt(measurementsAsHex[0], 16);
        int temperatureAsInt = Integer.parseInt(measurementsAsHex[1], 16);
        int co2 = Integer.parseInt(measurementsAsHex[2], 16);
        int lightAsInt = Integer.parseInt(measurementsAsHex[3], 16);

        var ratio = 100;

        double humidityAsDouble = ((double) humidityAsInt) / ratio;
        double temperatureAsDouble = ((double) temperatureAsInt) / ratio;
        double lightAsDouble = ((double) lightAsInt) / ratio;

        database.insert(co2, humidityAsDouble, temperatureAsDouble, lightAsDouble, ThreadLocalRandom.current().nextInt(0, 8));
    }
}