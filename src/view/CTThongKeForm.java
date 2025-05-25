package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CTThongKeForm extends JPanel {

    public CTThongKeForm() {
        setLayout(null);
        setBackground(Color.WHITE);

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(66, 133, 66));
        header.setBounds(0, 0, 750, 50);
        header.setLayout(null);

        JLabel lblTitle = new JLabel("PHIẾU", JLabel.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(0, 10, 750, 30);
        header.add(lblTitle);
        add(header);

        // Người tạo
        JLabel lblNguoiTao = new JLabel("Người tạo:");
        lblNguoiTao.setBounds(30, 60, 100, 20);
        add(lblNguoiTao);

        JLabel lblValue = new JLabel("jLabel7");
        lblValue.setBounds(100, 60, 200, 20);
        add(lblValue);

        // Bảng phiếu
        String[] columns = {"STT", "Mã phiếu", "Thời gian", "Thành tiền"};
        JTable table = new JTable(new DefaultTableModel(columns, 6));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 90, 690, 300);
        add(scrollPane);
    }
}
