import java.io.*;

/**
 * Created by Вячеслав on 14.02.2016.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("json.txt");
        Work work = new Work();
        Messages messages = new Messages();
        messages.readJSON(file);
        work.inputMessage(file, messages);
        messages.writeJSON(file);
    }
}
