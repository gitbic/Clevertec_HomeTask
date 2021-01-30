package ru.clevertec.beans;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import ru.clevertec.constants.Constants;
import ru.clevertec.enums.TableMenu;
import ru.clevertec.enums.TableTail;
import ru.clevertec.interfaces.CashReceipt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CashReceiptPdf implements CashReceipt {

    @Override
    public <T> T getCheckHead(Class<T> targetType) {
        PdfPTable table = getPdfTable(TableMenu.getCellWidth());

        for (TableMenu value : TableMenu.values()) {
            table.addCell(value.toString());
        }
        return targetType.cast(table);
    }

    @Override
    public <T> T getCheckBody(List<Purchase> purchases, Class<T> targetType) {
        PdfPTable table = getPdfTable(TableMenu.getCellWidth());

        for (int i = 0; i < purchases.size(); i++) {
            String[] csvStrings = purchases.get(i).toString().split(Constants.CSV_DELIMITER);
            for (String csvString : csvStrings) {
                table.addCell(csvString);
            }

            if (csvStrings.length == 4) {
                table.addCell(Constants.STRING_ONE_SPACE);
            }
        }
        return targetType.cast(table);
    }


    @Override
    public <T> T getCheckTail(String[] tailArgs, Class<T> targetType) {
        PdfPTable table = getPdfTable(TableTail.getCellWidth());

        table.addCell(TableTail.TOTAL.toString());
        table.addCell(tailArgs[TableTail.TOTAL.ordinal()]);
        table.addCell(TableTail.DISCOUNT.toString());
        table.addCell(tailArgs[TableTail.DISCOUNT.ordinal()]);
        table.addCell(TableTail.PAYMENT.toString());
        table.addCell(tailArgs[TableTail.PAYMENT.ordinal()]);

        return targetType.cast(table);
    }

    @Override
    public String getCheck(List<Purchase> purchases, String[] tailArgs) {



        try {
            Document document = new Document();

            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(Constants.DEFAULT_CHECK_PDF_OUTPUT_FILE_PATH));
            document.open();

            document.setMargins(Constants.PDF_DOC_MARGIN_LEFT,
                    Constants.PDF_DOC_MARGIN_RIGHT,
                    Constants.PDF_DOC_MARGIN_TOP,
                    Constants.PDF_DOC_MARGIN_BOTTOM);

            document.newPage();
            useTemplate(writer, Constants.PDF_TEMPLATE_PATH_FILE);

            document = fillDocument(purchases, tailArgs, document);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "PDF document successfully created: " + Constants.DEFAULT_CHECK_PDF_OUTPUT_FILE_PATH;
    }

    public Document fillDocument(List<Purchase> purchases, String[] tailArgs, Document document) {
        try {

            document.add(getCheckHead(PdfPTable.class));
            document.add(pdfTableSeparator());
            document.add(getCheckBody(purchases, PdfPTable.class));
            document.add(pdfTableSeparator());
            document.add(getCheckTail(tailArgs, PdfPTable.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
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