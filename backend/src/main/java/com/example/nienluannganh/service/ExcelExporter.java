package com.example.nienluannganh.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    public static byte[] exportInfoChamCongToExcel(List<InfoChamCong> dataList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh sách chấm công");

        // Tạo header style
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        // Tạo header
        Row header = sheet.createRow(0);
        String[] columns = {"STT", "Tên nhân viên", "Số điện thoại", "Tổng thu nhập", "Tổng thời gian trễ ( Phút )", "Tổng Thời gian tăng ca ( Phút ) "};

        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        // Ghi dữ liệu
        int rowNum = 1;
        for (int i = 0; i < dataList.size(); i++) {
            InfoChamCong info = dataList.get(i);
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(i + 1); // STT
            row.createCell(1).setCellValue(info.getNhanVien().getTen()); // Tên nhân viên
            row.createCell(2).setCellValue(info.getNhanVien().getSDT()); // Số điện thoại
            row.createCell(3).setCellValue(info.getTongSoTien() != null ? info.getTongSoTien() : 0); // Tổng thu nhập
            row.createCell(4).setCellValue(info.getSoGioDiTre() != null ? info.getSoGioDiTre() : 0); // Tổng giờ trễ
            row.createCell(5).setCellValue(info.getSoGioTangCa() != null ? info.getSoGioTangCa() : 0); // Tổng giờ tăng ca
        }

        // Auto size cột
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Ghi workbook vào byte[]
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            workbook.close();
            return bos.toByteArray();
        }
    }
}
