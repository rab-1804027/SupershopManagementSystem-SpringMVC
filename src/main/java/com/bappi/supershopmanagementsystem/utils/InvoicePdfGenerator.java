package com.bappi.supershopmanagementsystem.utils;

// Updated Utility.InvoicePdfGenerator
import com.bappi.supershopmanagementsystem.dto.SaleDetailsDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class InvoicePdfGenerator {
    public static byte[] generateInvoicePdf(List<SaleDetailsDto> saleDetails, double totalPrice, String saleTime)
            throws DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        try {
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("INVOICE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);
            Paragraph dateTime = new Paragraph("Date/Time: " + saleTime, normalFont);
            dateTime.setAlignment(Element.ALIGN_RIGHT);
            document.add(dateTime);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            String[] headers = {"Product Name", "Unit Price", "Quantity", "Price"};

            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            }

            for (SaleDetailsDto detail : saleDetails) {
                table.addCell(new Phrase(detail.getProductName(), normalFont));
                table.addCell(new Phrase(String.format("%.2f", detail.getUnitPrice()), normalFont));     // for price
                table.addCell(new Phrase(String.format("%d", detail.getQuantity()), normalFont));         // for quantity, using %d
                table.addCell(new Phrase(String.format("%.2f", detail.getPrice()), normalFont));         // for price
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            Paragraph total = new Paragraph(
                    String.format("Total Price: %.2f", totalPrice),
                    new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)
            );
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);
        } finally {
            document.close();
        }

        return byteArrayOutputStream.toByteArray();
    }
}
