import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DVDLibrary {

    static int uniqueId = 17;
    static String filePath ="C:/Users/bogda/Desktop/courses/microservices/BasicJava/Assessment2/src/dvds.csv";//"C:/Users/bogda/Desktop/courses/microservices/BasicJava/src/dvds.csv";

    public static void addDvd(String row) {
        uniqueId++;

        try {
            //Create object myDb of type CSVWriter
            FileWriter myFileWriter = new FileWriter(filePath, true);
            CSVWriter myDb = new CSVWriter(myFileWriter);

            String[] newDvd = row.split(",");

            myDb.writeNext(newDvd);
            myDb.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removeDvdById(int id) {
        try {

            //Create object readFile of type CSVReader
            CSVReader readFile = new CSVReader(new FileReader(filePath));

            List<String[]> nextRecord = readFile.readAll();
            nextRecord.remove(id);
            FileWriter sw = new FileWriter(filePath);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(nextRecord);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void editDvdById(int id, String replace){
        try {

            CSVReader readFile = new CSVReader(new FileReader(filePath));

            List<String[]> nextRecord = readFile.readAll();
            nextRecord.get(id)[0] = replace;
            readFile.close();

            FileWriter sw = new FileWriter(filePath);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(nextRecord);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void listAllDvds(){
        //List allData = csvReader.readAll();
        try {
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(filePath);

            // create csvReader object and skip first Line (which are the headers)
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            // print Data
            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + ",");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void displayDvdById(int id){
        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(filePath);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            int i = -1;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                i++;
                for (String cell : nextRecord) {
                    if(i == id){
                        System.out.print(cell + ",");
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void searchDvdByTitle(String title){
        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(filePath);

            // create csvReader object of type CSVReader
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                if(nextRecord[1].equals(title)){
                    for (String cell : nextRecord) {
                        System.out.print(cell + ",");

                    }
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){

        System.out.println("Select one option:  ");
        System.out.println("1.Add a DVD");
        System.out.println("2.Remove DVD by ID");
        System.out.println("3.Edit DVD by ID" );
        System.out.println("4.List all DVDs");
        System.out.println("5.Display a DVD");
        System.out.println("6.Search DVD by title");
        System.out.println("7.Add,Edit,Delete in one session");

        Scanner scan = new Scanner(System.in);
        int choice =  scan.nextInt();

        if(1 == choice){
            Scanner sc = new Scanner(System.in);
            System.out.println("Type: Title, Release Date, MPAA rating, Director's name, Studio, User rating");
            String newDvd = sc.next();

            addDvd(newDvd);
        }
        if(2 == choice){
            Scanner sc = new Scanner(System.in);
            System.out.println("Remove DVD by ID:");
            int removeDvd = sc.nextInt();

            removeDvdById(removeDvd);
        }
        if(3 == choice){
            Scanner sc = new Scanner(System.in);
            System.out.println("Type the ID:");
            int editDvd = sc.nextInt();

            Scanner update = new Scanner(System.in);
            System.out.println("Edit the DVD:");
            String replace = update.nextLine();

            editDvdById(editDvd, replace);
        }
        if(4 == choice){
            listAllDvds();
        }
        if(5 == choice) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a corresponding id: ");
            int idChoice =sc.nextInt();

            displayDvdById(idChoice);
        }
        if(6 == choice){
            Scanner sc =new Scanner(System.in);
            System.out.println("Type a DVD title: ");
            String title = sc.nextLine();
            searchDvdByTitle(title);
        }
        if(7 == choice){
            System.out.println("Add, Edit, Delete video in the library:");
            Scanner sc = new Scanner(System.in);
            System.out.println("Type: Title, Release Date, MPAA rating, Director's name, Studio, User rating");
            String newDvd = sc.next();
            addDvd(newDvd);

            System.out.println("Edit a DVD:");
            Scanner edit = new Scanner(System.in);
            System.out.println("Type the ID:");
            int editDvd = edit.nextInt();
            Scanner update = new Scanner(System.in);
            System.out.println("Edit the DVD:");
            String replace = update.nextLine();
            editDvdById(editDvd, replace);

            System.out.println("Delete a DVD");
            Scanner remove = new Scanner(System.in);
            System.out.println("Remove DVD by ID:");
            int removeDvd = remove.nextInt();
            removeDvdById(removeDvd);
        }
    }


}

