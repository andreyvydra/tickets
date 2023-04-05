package core;

import data.Ticket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * FileManager returns values from json files
 *
 * @see JSONParser
 */
public class FileManager {
    private final String filename;
    private final OutputHandler outputHandler = new OutputHandler();

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
            return new JSONObject(sb.toString());
        } catch (IOException e) {
            outputHandler.println("Поток был завершён, или файл не может быть прочитан!");
        } catch (JSONException e) {
            outputHandler.println("Некорректный файл");
        }
        return new JSONObject();
    }

    public void saveJSONObjectToFile(CollectionManager collectionManager) {
        JSONObject mainJsonObject = new JSONObject();
        JSONArray ticketsArray = new JSONArray();
        mainJsonObject.put("tickets", ticketsArray);
        try {
            FileWriter fileOutput = new FileWriter(this.filename);
            BufferedWriter writer = new BufferedWriter(fileOutput);
            for (Ticket ticket : collectionManager.getCollection()) {
                JSONObject jsonObject = new JSONObject(ticket.getMappedValues());
                ticketsArray.put(jsonObject);
            }
            writer.write(mainJsonObject.toString());
            writer.close();
            fileOutput.close();
            outputHandler.println("Данные были сохранены в json");
        } catch (IOException e) {
            outputHandler.println("Поток был завершён, или файл не может быть прочитан!");
        }
    }
}
