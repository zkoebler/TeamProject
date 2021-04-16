package com.codebind;

import java.io.*;
import java.util.*;

public class CSVData {

    private CSVList globalList; // rows of data example information.get(0) returns: ID,Last Name,First Name,Vaccine Type,Vaccination Date,Vaccine Location

    public CSVData(CSVList list) { // class for CSVData functions
        this.globalList = list;
        //list = this.globalList;
    }


       /*
            for(int i = 0; i <= globalList.size(); i++){
                String[] tableData = globalList.get(i).split(","); // tableData[0 to 5] to get individual data for table

                code for table

            }*/


    public void loadData(String fileName){

        //Scanner sc= new Scanner(System.in); // make scanner to get user input for filename
        //String fileName = sc.nextLine();

        CSVList tempList = new CSVList();
        BufferedReader reader = null; // reader for file
        String line = null; // line to hold each row to put into list

        try {
            reader = new BufferedReader(new FileReader(fileName)); // read fileName
        } catch (FileNotFoundException e1) { // exception when file is not found
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            while ((line = reader.readLine()) != null) { // reads each line/row until null and adds to list
                tempList.add(line);
            }
        } catch (IOException e) { // try and catch for reading errors
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //globalList = tempList;
        globalList.copy(tempList); // overwrite globalList with loaded data
    }

    public void saveData(File f){

        if(globalList != null) {
            Writer output; // create writer
            try {
                output = new BufferedWriter(new FileWriter(f, false)); // makes file in write mode and overwrites
                output.write("ID,Last Name,First Name,Vaccine Type,Vaccination Date,Vaccine Location"); // append first line
                for(int i = 1; i<globalList.size();i++ ){
                    String arg = "\n"+ globalList.get(i);
                    output.append(arg); // appends all data in current list to file
                }
                output.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            System.out.println("no data available");
        }
    }
    public void addData(String ID, String lastName, String firstName, String type, String date, String location){

        String newData = ID + "," + lastName + "," + firstName + "," + type + "," + date + "," + location; // format new data
        globalList.add(newData); // adds new data to global array list used for visual
    }

}
