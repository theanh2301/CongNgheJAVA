package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CTPhieuNhapForm extends JPanel {

    public CTPhieuNhapForm() {
        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(750, 450));

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(66, 133, 66));
        header.setBounds(0, 0, 750, 50);
        header.setLayout(null);

        JLabel lblTitle = new JLabel("CHI TIẾT PHIẾU NHẬP", JLabel.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(0, 10, 750, 30);
        header.add(lblTitle);
        add(header);

        // Thông tin chung
        JLabel lblMaPhieu = new JLabel("Mã phiếu:");
        lblMaPhieu.setBounds(30, 60, 100, 20);
        add(lblMaPhieu);
        add(createValueLabel(100, 60));

        JLabel lblNhaCungCap = new JLabel("Nhà cung cấp:");
        lblNhaCungCap.setBounds(30, 85, 100, 20);
        add(lblNhaCungCap);
        add(createValueLabel(130, 85));

        JLabel lblNguoiTao = new JLabel("Người tạo:");
        lblNguoiTao.setBounds(400, 60, 100, 20);
        add(lblNguoiTao);
        add(createValueLabel(480, 60));

        JLabel lblThoiGian = new JLabel("Thời gian tạo:");
        lblThoiGian.setBounds(400, 85, 100, 20);
        add(lblThoiGian);
        add(createValueLabel(500, 85));

        // Bảng chi tiết
        String[] columns = {"STT", "Mã máy", "Tên máy", "Số lượng", "Đơn giá", "Thành tiền"};
        JTable table = new JTable(new DefaultTableModel(columns, 5));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 120, 690, 250);
        add(scrollPane);

        // Tổng tiền
        JLabel lblTongTien = new JLabel("TỔNG TIỀN:");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 14));
        lblTongTien.setBounds(30, 380, 100, 30);
        add(lblTongTien);

        JLabel lblSoTien = new JLabel("...đ");
        lblSoTien.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSoTien.setBounds(130, 380, 200, 30);
        add(lblSoTien);

        // Button Xuất PDF
        JButton btnExport = new JButton("Xuất PDF");
        btnExport.setBounds(620, 380, 100, 30);
        btnExport.setIcon(new ImageIcon("icons/pdf_icon.png")); // chỉnh lại icon nếu cần
        add(btnExport);
    }

    private JLabel createValueLabel(int x, int y) {
        JLabel label = new JLabel("jLabel7");
        label.setBounds(x, y, 200, 20);
        return label;
    }
}
