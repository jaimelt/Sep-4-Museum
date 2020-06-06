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

/**
 * A Lorawan web client that listens to data from the hardware,
 * processes the telegram received and extracts the data to inserts
 * it in the mongo db database.
 *
 * @author Marina Ionel
 * @version 1.0.0
 */
public class LoRaClient implements WebSocket.Listener {
    // the reference to the database repository
    private final IDatabase database;
    // the hardware connection string including the token
    private final String HARDWARE_CONNECTION_STRING = "wss://iotnet.teracom.dk/app?token=vnoSvwAAABFpb3RuZXQudGVyYWNvbS5ka14S7zZXBMiAAcsYgh0N79M=";
    // a logger object for logging the
    private final Logger LOGGER = Logger.getLogger(LoRaClient.class.getName());
    //a file handler for writing the logs to a file
    private FileHandler fileHandler;
    //the ratio for dividing the sensor data in order to convert it to a double
    private final int RATIO = 100;

    /**
     * The constructor for the Lorawan client initializing the fields.
     */
    public LoRaClient() {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(HARDWARE_CONNECTION_STRING), this);
        try {
            fileHandler = new FileHandler("bridge-app.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.addHandler(fileHandler);
        fileHandler.setFormatter(new SimpleFormatter());
        database = new MongoDbDatabase();
    }

    /**
     * The implementation of the {@linkplain WebSocket.Listener#onOpen(WebSocket)}
     */
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        LOGGER.info("WebSocket Listener has been opened for requests.");
    }

    /**
     * The implementation of {@linkplain WebSocket.Listener#onError(WebSocket, Throwable)}
     */
    public void onError​(WebSocket webSocket, Throwable error) {
        LOGGER.severe("A " + error.getCause() + " exception was thrown.");
        LOGGER.severe("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }

    /**
     * The implementation of {@linkplain WebSocket.Listener#onClose(WebSocket, int, String)}
     */
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        LOGGER.warning("WebSocket closed!");
        LOGGER.warning("Status:" + statusCode + " Reason: " + reason);
        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    /**
     * The implementation of {@linkplain WebSocket.Listener#onPing(WebSocket, ByteBuffer)}
     */
    public CompletionStage<?> onPing​(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        LOGGER.info("Ping: Client ---> Server");
        LOGGER.info(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    /**
     * The implementation of {@linkplain WebSocket.Listener#onPong(WebSocket, ByteBuffer)}
     */
    public CompletionStage<?> onPong​(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        LOGGER.info("Pong: Client ---> Server");
        LOGGER.info(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    /**
     * The implementation of {@linkplain WebSocket.Listener#onText(WebSocket, CharSequence, boolean)}
     */
    public CompletionStage<?> onText​(WebSocket webSocket, CharSequence data, boolean last) {
        LOGGER.info("Received data: " + data);
        webSocket.request(1);
        var dataAsString = data.toString();
        parseAndInsertData(dataAsString);
        return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
    }

    /**
     * A method for processing the json telegram received and extracting
     * the sensor data from the byte array in the rx telegram.
     * It calls {@linkplain IDatabase#insert(int, double, double, double, int)}
     * for inserting the extracted sensor values.
     *
     * @param jsonTelegram the received json telegram
     */
    private void parseAndInsertData(String jsonTelegram) {
//        parse the telegram from String to JSONObject
        var parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(jsonTelegram);
        } catch (ParseException e) {
            LOGGER.severe(e.toString());
            return;
        }

        if (json == null) {
            LOGGER.severe("Failed to parse json telegram");
            return;
        }

//        check the type of the telegram
        if (!json.get("cmd").equals("rx")) return;

//        extract data raw
        var dataAsHex = (String) json.get("data");

//        check id data field is null
        if (dataAsHex == null) {
            LOGGER.severe("data field in telegram is null");
            return;
        }

//        spit data string every 4 characters and store it in an array
        String[] measurementsAsHex = dataAsHex.split("(?<=\\G....)");

//        extract sensor data from the array
        int humidityAsInt = Integer.parseInt(measurementsAsHex[0], 16);
        int temperatureAsInt = Integer.parseInt(measurementsAsHex[1], 16);
        int co2 = Integer.parseInt(measurementsAsHex[2], 16);
        int lightAsInt = Integer.parseInt(measurementsAsHex[3], 16);

//        convert the sensor data (humidity, temperature and light) back to double
        double humidityAsDouble = ((double) humidityAsInt) / RATIO;
        double temperatureAsDouble = ((double) temperatureAsInt) / RATIO;
        double lightAsDouble = ((double) lightAsInt) / RATIO;

//        insert the processed sensor data values in the data storage
        database.insert(co2, humidityAsDouble, temperatureAsDouble, lightAsDouble, ThreadLocalRandom.current().nextInt(1, 8));
    }
}