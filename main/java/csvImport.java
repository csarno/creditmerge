import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class csvImport {
    public csvImport() {

    }
    public ArrayList<String> parseCSV(String inputFilePath) {

        Reader in = null;
        ArrayList<String> fileRecord = new ArrayList<>();

        String[] FILE_HEADER_MAPPING = {"id",
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
        for (CSVRecord record : records) {
            System.out.printf("\nFirst record name: %s\n", record.get("Name_of_Lessee"));
            out = record.get("Position");
        }

        fileRecord.add(out);
        return fileRecord;
    }
}
