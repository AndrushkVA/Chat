import javax.xml.crypto.Data;
import java.io.Serializable;

/**
 * Created by ֲÿקוסכאג on 14.02.2016.
 */
public class Message implements Serializable{
    private int id;
    private String message;
    private String author;
    private Data timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Data getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Data timestamp) {
        this.timestamp = timestamp;
    }

}
