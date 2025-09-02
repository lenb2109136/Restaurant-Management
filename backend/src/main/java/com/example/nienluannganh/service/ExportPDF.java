package com.example.nienluannganh.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Service
public class ExportPDF {


    private static Font customFont;
    private static Font customBoldFont;

    static {
        try {
            String fontPath = new ClassPathResource("Fonts/Roboto-ExtraLight.ttf").getFile().getAbsolutePath();
            FontFactory.register(fontPath, "customFont");
            customFont = FontFactory.getFont("customFont", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
            customBoldFont = FontFactory.getFont("customFont", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12, Font.BOLD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] createPdf(Map<Object, Object> data) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        PdfWriter.getInstance(document, out);
        document.open();

        DecimalFormat currencyFormat = new DecimalFormat("#,##0.000");

        // Tiêu đề
        Paragraph header = new Paragraph("FLAM CẦN THƠ", customBoldFont);
        header.setAlignment(Element.ALIGN_LEFT);
        document.add(header);

        // Ngày lập và nhân viên
        document.add(new Paragraph("Ngày Lập: " + data.get("ngaylap").toString().replace("T", " ").substring(0, 19), customFont));
        Map<String, Object> nhanVien = (Map<String, Object>) data.get("nhanVien");
        document.add(new Paragraph("Nhân viên thu: " + nhanVien.get("ten"), customFont));

        // Tổng hóa đơn
        Number tongtien = (Number) data.get("tongtien");
        Paragraph tongTien = new Paragraph("Tổng hóa đơn: " + currencyFormat.format(tongtien.doubleValue() * 1000) + " đ", customBoldFont);
        tongTien.setSpacingAfter(10f);
        document.add(tongTien);

        Paragraph title = new Paragraph("Thông tin bàn ăn", customBoldFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10f);
        document.add(title);

        List<Map<String, Object>> banan = (List<Map<String, Object>>) data.get("banan");

        int index = 1;
        for (Map<String, Object> banAn : banan) {
            List<Map<String, Object>> ban = (List<Map<String, Object>>) banAn.get("ban");
            int soban = (int) ban.get(0).get("stt");

            Paragraph banTitle = new Paragraph("---------------- Bàn ăn số " + soban + " ----------------", customFont);
            banTitle.setAlignment(Element.ALIGN_LEFT);
            document.add(banTitle);

            // Danh sách món ăn
            List<Map<String, Object>> dsma = (List<Map<String, Object>>) banAn.get("dsma");
            if (dsma != null && !dsma.isEmpty()) {
                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                table.setSpacingBefore(5f);
                table.setWidths(new float[]{3, 2, 2, 2, 3});

                table.addCell(createCell("Tên Món Ăn", customBoldFont));
                table.addCell(createCell("Đơn Giá", customBoldFont));
                table.addCell(createCell("Số Lượng", customBoldFont));
                table.addCell(createCell("Giảm khuyến mãi", customBoldFont));
                table.addCell(createCell("Tổng", customBoldFont));

                for (Map<String, Object> mon : dsma) {
                    table.addCell(createCell(mon.get("ten").toString(), customFont));
                    Number dongia = (Number) mon.get("dongia");
                    table.addCell(createCell(currencyFormat.format(dongia.doubleValue() * 1000), customFont));
                    table.addCell(createCell(mon.get("soluong").toString(), customFont));
                    table.addCell(createCell("0%", customFont));
                    Number tong = (Number) mon.get("tong");
                    table.addCell(createCell(currencyFormat.format(tong.doubleValue() * 1000), customFont));
                }
                document.add(table);
            }

            // Tương tự với dscb nếu có
            List<Map<String, Object>> dscb = (List<Map<String, Object>>) banAn.get("dscb");
            if (dscb != null && !dscb.isEmpty()) {
                Paragraph sub = new Paragraph("Danh sách combo", customBoldFont);
                sub.setSpacingBefore(8f);
                document.add(sub);
                // Thêm bảng tương tự ở đây nếu cần
            }

            // Tương tự với dstu nếu có
            List<Map<String, Object>> dstu = (List<Map<String, Object>>) banAn.get("dstu");
            if (dstu != null && !dstu.isEmpty()) {
                Paragraph sub = new Paragraph("Danh sách tự chọn", customBoldFont);
                sub.setSpacingBefore(8f);
                document.add(sub);
                // Thêm bảng tương tự ở đây nếu cần
            }
        }

        document.close();
        return out.toByteArray();
    }



    private static PdfPCell createCell(String text, Font font) {
        return createCell(text, font, 1, Element.ALIGN_LEFT);
    }

    private static PdfPCell createCell(String text, Font font, int colspan, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setColspan(colspan);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.BOX);
        return cell;
    }
    


   
}
