

import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Main {
    public static FieldManager fieldManager = new FieldManager();

    public static void main(String[] args) throws IOException {

		PDDocument creditApp = null;
		final String dir = System.getProperty("user.dir");
		String inputFilename = "";
		String outputName = "";

		try {
			//creditApp = PDDocument.load(new File("E:\\Development\\creditmerge\\src\\main\\resources\\Consolidated Master Credit Application NEW.pdf"));
			//creditApp = PDDocument.load(new File("E:\\Dropbox\\PageSt\\Mail Merge\\PSL Individual-Homeowner Rental Application.pdf"));
			creditApp = PDDocument.load(new File(dir + "\\PSL Individual-Homeowner Rental Application.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			printMetadata(creditApp);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			inputFilename = args[0];
			outputName = args[1];
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

        CSVImport csvImport = new CSVImport();
        CSVImport csvImport1 = new CSVImport();
		CSVRecord inputRecordMap = csvImport.parseCSV(dir + "\\" + inputFilename);

		String[] recordMapping = csvImport1.getFILE_HEADER_MAPPING();


		System.out.printf("Size: %d\n", recordMapping.length);
		int i;
		for (i=0; i < recordMapping.length; i++) {
			System.out.println("Loop-de-loop.");
			System.out.printf("\nColumn %s: %s\n", recordMapping[i], inputRecordMap.get(recordMapping[i]));
			fieldManager.setField(creditApp, recordMapping[i], inputRecordMap.get(recordMapping[i]));
		}

		creditApp.save(dir + "\\Individual Credit Application " + outputName + ".pdf");


		System.out.printf("Record mapping 5: %s\n", recordMapping[5]);
		System.out.printf("Record value: %s\n", inputRecordMap.get("Mailing_Address"));

		for (i=0; i < recordMapping.length; i++) {
			System.out.printf("Record value %d: %s\n", i, inputRecordMap.get(recordMapping[i]));
		}


	}

	public static void printMetadata(PDDocument document) throws IOException {
		PDDocumentInformation info = document.getDocumentInformation();
		PDDocumentCatalog cat = document.getDocumentCatalog();
		PDMetadata metadata = cat.getMetadata();
		System.out.println("Page Count=" + document.getNumberOfPages());
		System.out.println("Title=" + info.getTitle());
		System.out.println("Author=" + info.getAuthor());
		System.out.println("Subject=" + info.getSubject());
		System.out.println("Keywords=" + info.getKeywords());
		System.out.println("Creator=" + info.getCreator());
		System.out.println("Producer=" + info.getProducer());
		System.out.println("Creation Date=" + formatDate(info.getCreationDate()));
		System.out.println("Modification Date=" + formatDate(info.getModificationDate()));
		System.out.println("Trapped=" + info.getTrapped());
		if (metadata != null) {
			String string = new String(metadata.toByteArray(), "ISO-8859-1");
			System.out.println("Metadata=" + string);
		}
	}

	private static String formatDate(Calendar date) {
		String retval = null;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat();
			retval = formatter.format(date.getTime());
		}

		return retval;
	}
}