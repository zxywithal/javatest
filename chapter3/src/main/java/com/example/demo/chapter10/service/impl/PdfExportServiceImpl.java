package com.example.demo.chapter10.service.impl;

import com.example.demo.chapter10.pojo.User;
import com.example.demo.chapter10.service.PdfExportService;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiaoyun3 on 2018/11/15.
 */
@Service
public class PdfExportServiceImpl implements PdfExportService {
    @Override
    public void make(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) {
        try {
            // A4纸张
            document.setPageSize(PageSize.A4);
            // 标题
            document.addTitle("用户信息");
            // 换行
            document.add(new Chunk("\n"));
            // 表格，3列
            PdfPTable table = new PdfPTable(4);
            // 单元格
            PdfPCell cell = null;
            // 字体，定义为蓝色加粗
            Font f8 = new Font();
            f8.setColor(Color.BLUE);
            f8.setStyle(Font.BOLD);
            // 标题
            cell = new PdfPCell(new Paragraph("id", f8));
            // 居中对齐
            cell.setHorizontalAlignment(1);
            // 将单元格加入表格
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("user_name", f8));
            // 居中对齐
            cell.setHorizontalAlignment(1);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("sex",f8));
            cell.setHorizontalAlignment(1);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("note", f8));
            cell.setHorizontalAlignment(1);
            table.addCell(cell);
            // 获取数据模型中的用户列表
            List<User> userList = (List<User>) model.get("userList");
            for (User user : userList) {
                document.add(new Chunk("\n"));
                cell = new PdfPCell(new Paragraph(user.getId() + ""));
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(user.getUserName()));
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(user.getSex()));
                table.addCell(cell);
                String note = user.getNote() == null ? "" : user.getNote();
                cell = new PdfPCell(new Paragraph("hh"));
                table.addCell(cell);
            }
            // 在文档中加入表格
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
