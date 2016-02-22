import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by ֲÿקוסכאג on 14.02.2016.
 */

public class Message implements Serializable {
    private long id;
    private String message;
    private String author;
    private String timestamp;

    public Message(long id, String author, String message, String timestamp) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.timestamp = timestamp;
    }

    public long getId() {
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

    public String date(String timestamp) {
        String data;
        DateFormat TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        data = TIMESTAMP.format(Long.parseLong(timestamp));
        return data;
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
        return "\nID: " + id + "\nAuthor: " + author + "\nMessage: " + message + "\nTimestamp: " + date(timestamp);
    }
}