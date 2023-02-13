import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInput = new FileInputStream("file.json");
        BufferedInputStream buffer = new BufferedInputStream(fileInput);

        StringBuilder sb = new StringBuilder();
        for (int a : buffer.readAllBytes()) {
            sb.append((char) a);
        }
        fileInput.close();
        buffer.close();

        JSONObject jo = new JSONObject(sb.toString());
        HashMap<String, String> map = new HashMap<>();
        map.put("lala", "koka");

        jo.put("biba", new JSONArray(new JSONObject[]{new JSONObject(map)}));
        jo.put("lola", "popa");

        FileWriter fileOutput = new FileWriter("file1.json");
        BufferedWriter writer = new BufferedWriter(fileOutput);
        writer.write(jo.toString());
        writer.close();
        fileOutput.close();

//
//        for (int a : buffer.readAllBytes()) {
//            writer.write(a);
//        }
//        writer.close();
    }
}