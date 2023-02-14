import core.JSONParser;
import core.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
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

        JSONObject mainObject = new JSONObject();
        JSONArray ticketsArray = new JSONArray();
        mainObject.put("tickets", ticketsArray);
        try {
            FileWriter fileOutput = new FileWriter("file1.json");
            BufferedWriter writer = new BufferedWriter(fileOutput);
            for (Ticket ticket : tickets) {
                JSONObject jo = new JSONObject(ticket.getMappedValue());
                ticketsArray.put(jo);
            }
            writer.write(mainObject.toString());
            writer.close();
            fileOutput.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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
