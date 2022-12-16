package myapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MailMerge {
    private String first_name;
    private String last_name;
    private String address;
    private int years;

    public MailMerge() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

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
                        System.out.println(newLine);
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