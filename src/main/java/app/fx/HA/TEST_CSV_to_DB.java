package app.fx.HA;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class TEST_CSV_to_DB {


    public static void main(String[] args) {
        String csvFilePath = "D:\\airports_test.csv";

        try {
            FileReader reader = new FileReader(csvFilePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());


        } catch (FileNotFoundException e) {

        } catch (Exception e) {

        }
    }
}
