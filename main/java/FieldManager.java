
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import java.io.IOException;

public class FieldManager {

    public FieldManager() {
    }

    public void setField(PDDocument pdfDocument, String name, String value) throws IOException {
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();

        if (docCatalog == null)
            System.out.println("docCatalog is null");
        if (pdfDocument.getNumberOfPages() > 0)
            System.out.printf("Doc has %d pages\n", pdfDocument.getNumberOfPages());
        for (PDPage docPage : docCatalog.getPages()) {
            System.out.printf("Page: %s\n", docPage.toString());
        }
        if (acroForm != null) {// Retrieve an individual field and set its value.
            PDTextField fieldTest = (PDTextField) acroForm.getField("Street");
            fieldTest.setValue("Text Entry");

            // If a field is nested within the form tree a fully qualified name
            // might be provided to access the field.
            // field = (PDTextField) acroForm.getField( "fieldsContainer.nestedSampleField" );
            // field.setValue("Text Entry"); }
            if (acroForm == null)
                System.out.println("Acroform NULL");
            PDField field = acroForm.getField(name);
            if (field != null) {
                if (field instanceof PDCheckBox) {
                    field.setValue("Yes");
                } else if (field instanceof PDComboBox) {
                    field.setValue(value);
                } else if (field instanceof PDListBox) {
                    field.setValue(value);
                } else if (field instanceof PDRadioButton) {
                    field.setValue(value);
                } else if (field instanceof PDTextField) {
                    field.setValue(value);
                    System.out.printf("Set the text to: %s", value);
                }
            } else {
                System.err.println("No field found with name:" + name);
            }
        }
        // pdfDocument.save("E:\\Development\\creditmerge\\src\\main\\resources\\Consolidated Master Credit Application OUT.pdf");
    }
}
