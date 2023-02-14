import core.JSONParser;
import core.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class CollectionManager {
    TreeSet<Ticket> collection = new TreeSet<>();
    String filename;

    public CollectionManager(String filename) {
        this.filename = filename;
        JSONParser jParser = new JSONParser(this.getJSONObjectFromFile());
        ArrayList<Ticket> tickets = jParser.parse();
        for (Ticket ticket : tickets) {
            if (this.checkId(ticket.getId())) {
                this.collection.add(ticket);
            }
        }
        System.out.println(this.collection);
    }

    public JSONObject getJSONObjectFromFile() {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fileInput = new FileInputStream("file.json");
            BufferedInputStream buffer = new BufferedInputStream(fileInput);

            for (int a : buffer.readAllBytes()) {
                sb.append((char) a);
            }
            fileInput.close();
            buffer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        return new JSONObject(sb.toString());
    }

    public boolean checkId(long id) {
        for (Ticket ticket : this.collection) {
            if (ticket.getId() == id) {
                return false;
            }
        }
        return true;
    }
}
