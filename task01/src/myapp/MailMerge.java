package myapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class MailMerge {
    public void mailMerge(String csvFile, String templateFile) throws IOException {

        // Read the csv file
        ArrayList<Map<String, String>> rows = readCsvFile(csvFile);


        for (Map<String, String> row : rows) {
            FileReader fr = new FileReader(templateFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                
                for (Entry<String, String> entry : row.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
            
                    if (line.contains(key)) {
                        String tag = String.format("<<%s>>", key);
                        String newLine = line.replace(tag, value);
                        newLine = newLine.replaceAll("\\\\n", "\n");
                        System.out.println(newLine);
                        System.out.println();
                    }
                }
            }
            br.close();
        }
    }

    public static ArrayList<Map<String, String>> readCsvFile(String csvFile) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        // Skip the first line of csv files which stores the Header names
        String firstLine = br.readLine();
        ArrayList<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        String[] headers = firstLine.split(",");

        String line;

        while ((line = br.readLine()) != null) {
            // Spilt the line in order to get publisher name
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