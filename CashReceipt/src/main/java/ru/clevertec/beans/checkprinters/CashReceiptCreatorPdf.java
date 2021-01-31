package ru.clevertec.beans.checkprinters;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import ru.clevertec.beans.Purchase;
import ru.clevertec.constants.Constants;
import ru.clevertec.enums.Arguments;
import ru.clevertec.enums.TableMenu;
import ru.clevertec.enums.TableTail;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CashReceiptCreatorPdf implements CashReceiptCreator {

    @Override
    public void printCheck(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Arguments.CHECK_PDF_OUTPUT_PATH_FILE.getValue());
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ByteArrayOutputStream createCheck(List<Purchase> purchases, String[] tailArgs)  {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();


            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

//            new FileOutputStream(Arguments.CHECK_PDF_OUTPUT_PATH_FILE.getValue()));
            document.open();

            document.setMargins(Constants.PDF_DOC_MARGIN_LEFT,
                    Constants.PDF_DOC_MARGIN_RIGHT,
                    Constants.PDF_DOC_MARGIN_TOP,
                    Constants.PDF_DOC_MARGIN_BOTTOM);

            document.newPage();
            useTemplate(writer, Constants.PDF_TEMPLATE_FILE_PATH);

            document.add(getCheckHead());
            document.add(getCheckBody(purchases));
            document.add(pdfTableSeparator());
            document.add(getCheckTail(tailArgs));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream;
    }

    private PdfPTable getCheckHead() {
        PdfPTable table = getPdfTable(TableMenu.getCellWidth());

        for (TableMenu value : TableMenu.values()) {
            table.addCell(value.toString());
        }
        return table;
    }

    private PdfPTable getCheckBody(List<Purchase> purchases) {
        PdfPTable table = getPdfTable(TableMenu.getCellWidth());

        for (int i = 0; i < purchases.size(); i++) {
            String[] csvStrings = purchases.get(i).toString().split(Constants.CSV_DELIMITER);
            for (String csvString : csvStrings) {
                table.addCell(csvString);
            }

            if (csvStrings.length == Constants.PDF_NUMBER_OF_COLUMN_PURCHASE_WITHOUT_DISCOUNT) {
                table.addCell(Constants.STRING_ONE_SPACE);
            }
        }
        return table;
    }


    private PdfPTable getCheckTail(String[] tailArgs) {
        PdfPTable table = getPdfTable(TableTail.getCellWidth());

        table.addCell(TableTail.TOTAL.toString());
        table.addCell(tailArgs[TableTail.TOTAL.ordinal()]);
        table.addCell(TableTail.DISCOUNT.toString());
        table.addCell(tailArgs[TableTail.DISCOUNT.ordinal()]);
        table.addCell(TableTail.PAYMENT.toString());
        table.addCell(tailArgs[TableTail.PAYMENT.ordinal()]);

        return table;
    }

    private void useTemplate(PdfWriter writer, String templateFileName) throws IOException {
        FileInputStream template = new FileInputStream(templateFileName);
        PdfReader reader = new PdfReader(template);
        PdfImportedPage page = writer.getImportedPage(reader, Constants.PDF_TEMPLATE_PAGE_NUMBER);
        PdfContentByte cb = writer.getDirectContent();
        cb.addTemplate(page, Constants.PDF_TEMPLATE_PAGE_COORD_X, Constants.PDF_TEMPLATE_PAGE_COORD_Y);
    }


    private PdfPTable getPdfTable(float[] cellsWidth) {
        PdfPTable table = new PdfPTable(cellsWidth.length);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        try {
            table.setTotalWidth(cellsWidth);
        } catch (DocumentException e) {
            System.out.println(e);
        }
        return table;
    }


    private PdfPTable pdfTableSeparator() {
        PdfPTable table = new PdfPTable(TableMenu.values().length);
        table.getDefaultCell().setBorder(Rectangle.ALIGN_JUSTIFIED);
        for (int i = 0; i < TableMenu.values().length; i++) {
            table.addCell(Constants.STRING_ONE_SPACE);
        }
        return table;
    }
}