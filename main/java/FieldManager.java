

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import java.io.IOException;

public class FieldManager {

    public FieldManager() {
    }

    public void setField(PDDocument pdfDocument, String recordName, String recordValue) throws IOException {
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();

        /*
        if (docCatalog == null)
            System.out.println("docCatalog is null");
        if (pdfDocument.getNumberOfPages() > 0)
            System.out.printf("Doc has %d pages\n", pdfDocument.getNumberOfPages());
        for (PDPage docPage : docCatalog.getPages()) {
            System.out.printf("Page: %s\n", docPage.toString());
        }
        */
        System.out.printf("Record value: %s", recordValue);
        if (acroForm != null) {// Retrieve an individual field and set its value.
            PDTextField fieldTest = (PDTextField) acroForm.getField(recordName);
            if (fieldTest == null) {
                System.out.println("Fieldtest is null or empty.");
            } else {
                fieldTest.setValue("12");
            }


            // If a field is nested within the form tree a fully qualified name
            // might be provided to access the field.
            // field = (PDTextField) acroForm.getField( "fieldsContainer.nestedSampleField" );
            // field.setValue("Text Entry"); }
            if (acroForm == null)
                System.out.println("Acroform NULL");
            PDField field = acroForm.getField(recordName);
            if (field != null) {
                if (field instanceof PDCheckBox) {
                    field.setValue("Yes");
                } else if (field instanceof PDComboBox) {
                    field.setValue(recordValue);
                } else if (field instanceof PDListBox) {
                    field.setValue(recordValue);
                } else if (field instanceof PDRadioButton) {
                    field.setValue(recordValue);
                } else if (field instanceof PDTextField) {
                    field.setValue(recordValue);
                    System.out.printf("Set the text to: %s", recordName);
                }
            } else {
                System.err.println("No field found with name:" + recordName);
            }
        }
        // pdfDocument.save("E:\\Development\\creditmerge\\src\\main\\resources\\Consolidated Master Credit Application OUT.pdf");
    }
}
