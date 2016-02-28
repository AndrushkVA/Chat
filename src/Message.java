import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ֲÿקוסכאג on 14.02.2016.
 */

public class Message implements Serializable {
    private String id;
    private String message;
    private String author;
    private String timestamp;

    public Message(String id, String author, String message, String timestamp) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", author='" + author + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public String toShow() {
        Messages messages = new Messages();
        return "\nID: " + id + "\nAuthor: " + author + "\nMessage: " + message + "\nTimestamp: " + messages.date(timestamp);
    }
}