package ru.clevertec.beans;

import ru.clevertec.Constants;
import ru.clevertec.Utility;
import ru.clevertec.enums.TableMenu;
import ru.clevertec.enums.TableTail;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class MainOrder {
    private final List<Purchase> purchases;
    private final DiscountCard discountCard;

    public MainOrder(DiscountCard discountCard) {
        this.purchases = new ArrayList<>();
        this.discountCard = discountCard;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public Purchase getPurchaseFromList(int i) {
        return purchases.get(i);
    }

    public void removePurchaseFromList(int i) {
        purchases.remove(i);
    }

    public void addPurchaseToList(Purchase purchase) {
        purchases.add(purchase);
    }

    public BigDecimal getTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Purchase purchase : purchases) {
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public BigDecimal getDiscountCost() {
        BigDecimal discount = BigDecimal.ZERO;
        if (discountCard != null) {
            discount = getTotalCost().multiply(BigDecimal.valueOf(discountCard.getDiscount() / 100));
        }
        return discount;
    }

    public BigDecimal getFinalCost() {
        return getTotalCost().subtract(getDiscountCost());
    }

    public String getCheck() {

        String head = TableMenu.getHead()
                + System.lineSeparator()
                + Constants.MENU_DELIMITER;

        StringBuilder body = new StringBuilder();
        for (Purchase purchase : purchases) {
            body.append(TableMenu.getFormattedString(purchase.toString()))
                    .append(System.lineSeparator());
        }

        String tailString = TableTail.getTailFormatString();
        String tail = Constants.MENU_DELIMITER
                + String.format(tailString, TableTail.TOTAL, Utility.priceToString(getTotalCost()))
                + String.format(tailString, TableTail.DISCOUNT, Utility.percentToString(discountCard.getDiscount()))
                + String.format(tailString, TableTail.TO_PAY, Utility.priceToString(getFinalCost()));

        return head + body + tail;
    }

    public void createPDFCheck(String filePDFOutput) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePDFOutput));
            document.open();

            PdfPTable table = new PdfPTable(5);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            // set width for cells
            float[] cellsWidth = new float[TableMenu.values().length];
            for (int i = 0; i < cellsWidth.length; i++) {
                cellsWidth[i] = TableMenu.values()[i].getWidthCell();
            }
            table.setTotalWidth(cellsWidth);

            // title
            for (TableMenu value : TableMenu.values()) {
                table.addCell(value.toString());
            }

            pdfTableSeparator(table);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            // body
            for (Purchase purchase : purchases) {
                String[] csvStrings = purchase.toString().split(Constants.CSV_DELIMITER);
                for (String csvString : csvStrings) {
                    table.addCell(csvString);
                }

                if (csvStrings.length == 4) {
                    table.addCell(Constants.EMPTY_STRING);
                }
            }

            pdfTableSeparator(table);
            document.add(table);

            // tail
            table = new PdfPTable(2);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            // set width for cells
            int secondCellWidth = TableMenu.DISCOUNT.getWidthCell() + TableMenu.TOTAL.getWidthCell();
            cellsWidth = new float[]{
                    TableMenu.getTotalWidth() - secondCellWidth,
                    secondCellWidth
            };

            table.setTotalWidth(cellsWidth);

            table.addCell(TableTail.TOTAL.toString());
            table.addCell(Utility.priceToString(getTotalCost()));
            table.addCell(TableTail.DISCOUNT.toString());
            table.addCell(Utility.percentToString(discountCard.getDiscount()));
            table.addCell(TableTail.TO_PAY.toString());
            table.addCell(Utility.priceToString(getFinalCost()));

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pdfTableSeparator(PdfPTable table) {
        table.getDefaultCell().setBorder(Rectangle.ALIGN_JUSTIFIED);
        for (int i = 0; i < TableMenu.values().length; i++) {
            table.addCell(Constants.EMPTY_STRING);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Purchase purchase : purchases) {
            sb.append(purchase);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
