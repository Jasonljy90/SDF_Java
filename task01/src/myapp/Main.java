package myapp;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException{

        String csvFile = args[0];
        String templateFile = args[1];

        String pathCsv = Paths.get("src/myapp/sdf_assessment_task_1_files", csvFile).toString();
        String pathTemplateFile = Paths.get("src/myapp/sdf_assessment_task_1_files", templateFile).toString();

        MailMerge mail = new MailMerge();
        mail.mailMerge(pathCsv, pathTemplateFile);
    }

}