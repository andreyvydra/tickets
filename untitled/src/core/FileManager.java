package core;

import data.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private String filename;

    public FileManager(String filename) {
        this.filename = filename;
    }

    public JSONObject getJSONObjectFromFile() {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fileInput = new FileInputStream(this.filename);
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

    public void loadDataToCollection(CollectionManager collectionManager) {
        JSONParser jParser = new JSONParser(this.getJSONObjectFromFile());
        ArrayList<Ticket> tickets = jParser.parse();
        collectionManager.loadTickets(tickets);
    }

    public void saveJSONObjectToFile(CollectionManager collectionManager) {
        JSONObject mainObject = new JSONObject();
        JSONArray ticketsArray = new JSONArray();
        mainObject.put("tickets", ticketsArray);
        try {
            FileWriter fileOutput = new FileWriter(this.filename);
            BufferedWriter writer = new BufferedWriter(fileOutput);
            for (Ticket ticket : collectionManager.getCollection()) {
                JSONObject jo = new JSONObject(ticket.getMappedValues());
                ticketsArray.put(jo);
            }
            writer.write(mainObject.toString());
            writer.close();
            fileOutput.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
