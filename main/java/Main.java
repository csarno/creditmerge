
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static FieldManager fieldManager = new FieldManager();

    public static void main(String[] args) throws IOException {

		PDDocument creditApp = null;
		try {
			creditApp = PDDocument.load(new File("E:\\Development\\creditmerge\\src\\main\\resources\\Consolidated Master Credit Application NEW.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			printMetadata(creditApp);
		} catch (IOException e) {
			e.printStackTrace();
		}

		fieldManager.setField(creditApp,"Street", "Test Corp");
        creditApp.save("E:\\Development\\creditmerge\\src\\main\\resources\\Consolidated Master Credit Application OUT.pdf");

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