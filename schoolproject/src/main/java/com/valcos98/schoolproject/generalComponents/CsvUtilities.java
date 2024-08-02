package com.valcos98.schoolproject.generalComponents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.valcos98.schoolproject.studentsComponents.StudentModel;

public class CsvUtilities {

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

    public static List<StudentModel> csvToStudentsList(MultipartFile file) throws CsvValidationException, IOException{
        List<StudentModel> studentList = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF-8"));
        CSVReader csvReader = new CSVReader(fileReader);
        while (true) {
            String[] names = csvReader.peek();
            if (names != null) {
                StudentModel newStudent = new StudentModel(names[0], names[1], names[2]);
                studentList.add(newStudent);
                csvReader.readNext();
            }else{
                break;
            }
        }
        csvReader.close();
        fileReader.close();
        return studentList;
    }
}
