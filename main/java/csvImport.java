

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVImport {

    public String[] getFILE_HEADER_MAPPING() {
        return FILE_HEADER_MAPPING;
    }

    public String[] FILE_HEADER_MAPPING = {"id",
            "Date_Submitted",
            "Name_of_Lessee",
            "Date_of_Birth",
            "Drivers_Lic",
            "Mailing_Address",
            "Mail_City",
            "Mail_State",
            "Mail_Zip",
            "Home_Phone",
            "Mobile_Phone",
            "Email",
            "Years_Residing",
            "Invoice_Delivery",
            "Delivery_Address",
            "Delivery_City",
            "Delivery_State",
            "Delivery_Zip",
            "Relationship_to_property",
            "Property_Owner",
            "Employer_Name",
            "Office_Phone",
            "Office_Address",
            "Office_City",
            "Office_State",
            "Office_Zip",
            "Position",
            "Supervisor_Name",
            "Years_Employed",
            "Employment_Status",
            "Alternate_Name",
            "Alternate_Relationship",
            "Alternate_Phone",
            "Alternate_Address",
            "Alternate_City",
            "Alternate_State",
            "Alternate_Zip",
            "Bankrupcy",
            "Bankruptcy_Date",
            "Authorized_Signature",
            "Signature_Date"
    };

    public CSVImport() {

    }
    public CSVRecord parseCSV(String inputFilePath) {

        Reader in = null;
        ArrayList<CSVRecord> csvRecords = new ArrayList<>();
        ArrayList<String> customerRecord = new ArrayList<>();


        try {
            in = new FileReader(inputFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CSVFormat csvFileFormat = CSVFormat.EXCEL.withHeader(FILE_HEADER_MAPPING);
        CSVParser records = null;
        try {
            records = new CSVParser(in, csvFileFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String out = "";
        try {
            csvRecords = (ArrayList<CSVRecord>) records.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CSVRecord custRecord = csvRecords.get(1);
        System.out.printf("\nCustomer email: %s\n", custRecord.get("Email"));

        return csvRecords.get(1);
    }
}
