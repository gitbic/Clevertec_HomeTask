package ru.clevertec.beans;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import ru.clevertec.Constants;
import ru.clevertec.enums.TableMenu;
import ru.clevertec.enums.TableTail;
import ru.clevertec.interfaces.CashReceipt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CashReceiptPdf implements CashReceipt {

    private PdfPTable getCheckHead() throws DocumentException {
        PdfPTable table = getTable5Columns();

        for (TableMenu value : TableMenu.values()) {
            table.addCell(value.toString());
        }
        return table;
    }

    private PdfPTable getCheckBody(List<Purchase> purchases) throws DocumentException {
        PdfPTable table = getTable5Columns();

        for (Purchase purchase : purchases) {
            String[] csvStrings = purchase.toString().split(Constants.CSV_DELIMITER);
            for (String csvString : csvStrings) {
                table.addCell(csvString);
            }

            if (csvStrings.length == 4) {
                table.addCell(Constants.EMPTY_STRING);
            }
        }
        return table;
    }


    private PdfPTable getCheckTail(String[] tailArgs) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        // set width for cells
        int secondCellWidth = TableMenu.DISCOUNT.getWidthCell() + TableMenu.TOTAL.getWidthCell();
        float[] cellsWidth = new float[]{
                TableMenu.getTotalWidth() - secondCellWidth,
                secondCellWidth
        };
        table.setTotalWidth(cellsWidth);

        table.addCell(TableTail.TOTAL.toString());
        table.addCell(tailArgs[TableTail.TOTAL.ordinal()]);
        table.addCell(TableTail.DISCOUNT.toString());
        table.addCell(tailArgs[TableTail.DISCOUNT.ordinal()]);
        table.addCell(TableTail.PAYMENT.toString());
        table.addCell(tailArgs[TableTail.PAYMENT.ordinal()]);

        return table;
    }

    @Override
    public String getCheck(List<Purchase> purchases, String[] tailArgs) {
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(Constants.DEFAULT_PAH_FILE_CHECK_PDF_OUTPUT));
            document.open();

            document.setMargins(0,0,200,0);
            document.newPage();

            useTemplate(writer, Constants.PDF_TEMPLATE);

            document.add(getCheckHead());
            document.add(pdfTableSeparator());
            document.add(getCheckBody(purchases));
            document.add(pdfTableSeparator());
            document.add(getCheckTail(tailArgs));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "PDF document successfully created: " + Constants.DEFAULT_PAH_FILE_CHECK_PDF_OUTPUT;
    }

    private void useTemplate(PdfWriter writer, String templateFileName) throws IOException {
        FileInputStream template = new FileInputStream(templateFileName);
        PdfReader reader = new PdfReader(template);
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        PdfContentByte cb = writer.getDirectContent();
        cb.addTemplate(page, 0, 0);
    }


    private PdfPTable getTable5Columns() throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        // set width for cells
        float[] cellsWidth = new float[TableMenu.values().length];
        for (int i = 0; i < cellsWidth.length; i++) {
            cellsWidth[i] = TableMenu.values()[i].getWidthCell();
        }
        table.setTotalWidth(cellsWidth);
        return table;
    }

    private PdfPTable pdfTableSeparator() {
        PdfPTable table = new PdfPTable(5);
        table.getDefaultCell().setBorder(Rectangle.ALIGN_JUSTIFIED);
        for (int i = 0; i < TableMenu.values().length; i++) {
            table.addCell(Constants.EMPTY_STRING);
        }
        return table;
    }
}