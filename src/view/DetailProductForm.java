package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DetailProductForm extends JPanel {

    public DetailProductForm() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(new LineBorder(new Color(218, 165, 32), 2)); // Viền vàng nhạt

        // Tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87)); // Màu xanh lá
        titlePanel.setBounds(0, 0, 700, 50);
        titlePanel.setLayout(null);

        JLabel titleLabel = new JLabel("THÔNG TIN SẢN PHẨM");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 10, 300, 30);
        titlePanel.add(titleLabel);

        add(titlePanel);

        // Mảng tên các trường và vị trí
        String[] labels = {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng",
                "CPU", "RAM", "Dung lượng lưu trữ", "Card đồ họa",
                "Loại sản phẩm", "Kích thước màn", "Dung lượng PIN", "Xuất xứ"
        };

        int[][] positions = {
                {40, 70}, {40, 120}, {40, 170}, {40, 220},
                {260, 70}, {260, 120}, {260, 170}, {260, 220},
                {480, 70}, {480, 120}, {480, 170}, {480, 220}
        };

        // Các input field
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setBounds(positions[i][0], positions[i][1], 130, 20);
            add(lbl);

            if (labels[i].equals("Loại sản phẩm")) {
                JComboBox<String> cbType = new JComboBox<>(new String[]{"Laptop", "Điện thoại", "Tablet"});
                cbType.setBounds(positions[i][0], positions[i][1] + 20, 150, 25);
                add(cbType);
            } else {
                JTextField tf = new JTextField();
                tf.setBounds(positions[i][0], positions[i][1] + 20, 150, 25);
                add(tf);
            }
        }
    }

}
