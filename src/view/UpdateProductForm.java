package view;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UpdateProductForm extends JPanel {
    private JTextField tfMaSP, tfTenSP, tfDonGia, tfXuatXu;
    private JTextField tfCPU, tfRAM, tfStorage, tfGPU, tfScreenSize, tfBattery;
    private JComboBox<String> cbLoaiSP;
    private JButton btnSave, btnCancel;

    public UpdateProductForm() {
        setLayout(null); // dùng tọa độ tuyệt đối
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(710, 400));

        // Tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87)); // xanh lá đậm
        titlePanel.setBounds(0, 0, 720, 50);
        titlePanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("THÊM SẢN PHẨM MỚI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        add(titlePanel);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 13);
        int labelW = 120, fieldW = 180, h = 25;

        // Cột 1
        addLabelAndField("Mã sản phẩm:", 30, 70, labelW, h, labelFont);
        tfMaSP = createTextField(160, 70, fieldW, h);

        addLabelAndField("Tên sản phẩm:", 30, 110, labelW, h, labelFont);
        tfTenSP = createTextField(160, 110, fieldW, h);

        addLabelAndField("Đơn giá:", 30, 150, labelW, h, labelFont);
        tfDonGia = createTextField(160, 150, fieldW, h);

        addLabelAndField("Xuất xứ:", 30, 190, labelW, h, labelFont);
        tfXuatXu = createTextField(160, 190, fieldW, h);

        // Cột 2
        addLabelAndField("CPU:", 380, 70, labelW, h, labelFont);
        tfCPU = createTextField(510, 70, fieldW, h);

        addLabelAndField("RAM:", 380, 110, labelW, h, labelFont);
        tfRAM = createTextField(510, 110, fieldW, h);

        addLabelAndField("Dung lượng lưu trữ:", 380, 150, labelW, h, labelFont);
        tfStorage = createTextField(510, 150, fieldW, h);

        addLabelAndField("Card đồ họa:", 380, 190, labelW, h, labelFont);
        tfGPU = createTextField(510, 190, fieldW, h);

        // Cột 3 (phần loại + các thành phần tiếp)
        addLabelAndField("Loại sản phẩm:", 30, 230, labelW, h, labelFont);
        cbLoaiSP = new JComboBox<>(new String[]{"Laptop", "PC", "Tablet"});
        cbLoaiSP.setBounds(160, 230, fieldW, h);
        add(cbLoaiSP);

        addLabelAndField("Kích thước màn:", 380, 230, labelW, h, labelFont);
        tfScreenSize = createTextField(510, 230, fieldW, h);

        addLabelAndField("Dung lượng PIN:", 380, 270, labelW, h, labelFont);
        tfBattery = createTextField(510, 270, fieldW, h);

        // Buttons
        btnSave = new JButton("Lưu");
        btnSave.setBounds(220, 320, 120, 35);
        btnSave.setBackground(Color.WHITE);
        btnSave.setBorder(new LineBorder(new Color(46, 139, 87), 1));
        btnSave.setForeground(new Color(46, 139, 87));

        btnCancel = new JButton("Hủy");
        btnCancel.setBounds(370, 320, 120, 35);
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setBorder(new LineBorder(Color.RED, 1));
        btnCancel.setForeground(Color.RED);

        add(btnSave);
        add(btnCancel);
    }

    private void addLabelAndField(String text, int x, int y, int w, int h, Font font) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font);
        add(label);
    }

    private JTextField createTextField(int x, int y, int w, int h) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, h);
        add(tf);
        return tf;
    }

}
