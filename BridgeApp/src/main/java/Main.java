import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        LoRaClient loRaClient = new LoRaClient();
        System.out.println(Calendar.getInstance().getTimeInMillis());
    }
}
