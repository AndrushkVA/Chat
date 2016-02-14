import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ֲÿקוסכאג on 14.02.2016.
 */
public class Messages {
    private List<Message> messages;

    public Messages(){
        messages = new ArrayList<Message>();
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public void readJSON(File file) throws IOException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(file));
        if (file.length() > 0) {
            messages = gson.fromJson(br, new TypeToken<ArrayList<Message>>() {
            }.getType());
            System.out.println(messages.toString());
        } else System.out.println("JSON-file is empty");
    }

    public void writeJSON(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        PrintStream ps = new PrintStream(file);
        ps.append(gson.toJson(messages));
    }
}
