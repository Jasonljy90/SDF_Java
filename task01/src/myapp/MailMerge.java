package myapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MailMerge {
   
    public void mailMerge(String csvFile, String templateFile) throws IOException {

        // Read the csv file and get the ArrayList
        ArrayList<Map<String, String>> rows = readCsvFile(csvFile);

        for (Map<String, String> row : rows) {
            FileReader fr = new FileReader(templateFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
           
            while ((line = br.readLine()) != null) {
               
                for (Entry<String, String> entry : row.entrySet()) {
                    String key = entry.getKey();
                    //System.out.println("Key " + key);
                    String value = entry.getValue();
                    
                    if (line.contains(key)) {
                        String tag = String.format("<<%s>>", key);
                        line = line.replaceAll(tag, value);
                        line = line.replaceAll("\\\\n", "\n");
                    }
                }
                System.out.println(line);
            }
            br.close();
        }
    }

    public static ArrayList<Map<String, String>> readCsvFile(String csvFile) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        String firstLine = br.readLine();
        ArrayList<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        String[] headers = firstLine.split(",");

        String line;

        while ((line = br.readLine()) != null) {
            String[] csvRow = line.split(",");
            Map<String, String> row = new HashMap<String, String>();
            for (int i = 0; i < csvRow.length; i++) {
                String item = csvRow[i];
                String header = headers[i];
                row.put(header, item);
            }
            rows.add(row);
        }
        br.close();
        return rows;
    }
}
