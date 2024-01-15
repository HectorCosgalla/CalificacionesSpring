package com.valcos98.schoolproject.generalComponents;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class CsvProcessor {

    public static String[][] csvToStringForCourses(String FilePath){
        
        ArrayList<ArrayList<String>> csvFile = readLineByLine(FilePath);

        String[][] result = new String[csvFile.size()][csvFile.get(0).size()];

        for (int i = 0; i < csvFile.size(); i++) {
            for(int j = 0; j < csvFile.get(i).size() ; j++){
                result[i][j] = csvFile.get(i).get(j);
            }
        }
        
        return result;
    }

    private static ArrayList<ArrayList<String>> readLineByLine(String FilePath){
        ArrayList<ArrayList<String>> csvRows = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(FilePath);
            try (CSVReader csvReader = new CSVReader(fileReader)) {
                String [] nextLine;
                int i = 0;
                while ((nextLine = csvReader.readNext()) != null) {
                    ArrayList<String> csvColumns = new ArrayList<>();
                    csvRows.add(csvColumns);
                    for (int j = 0; j < nextLine.length; j++) {
                        csvRows.get(i).add(nextLine[j]);
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return csvRows;
    }
}
