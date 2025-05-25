package view;

import javax.swing.*;

public class UpdateAccountForm extends JFrame {
    public UpdateAccountForm() {
        setTitle("Cập Nhật Thông Tin");
        setSize(350, 420);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("CẬP NHẬT THÔNG TIN", SwingConstants.CENTER);
        title.setBounds(0, 10, 350, 30);
        title.setOpaque(true);
        title.setBackground(new java.awt.Color(91, 166, 79)); // Xanh lá
        title.setForeground(java.awt.Color.WHITE);
        add(title);

        addLabelAndField("Tên tài khoản", 60);
        addLabelAndField("Tên đăng nhập", 110);
        addLabelAndField("Email", 160);

        JLabel roleLabel = new JLabel("Vai trò");
        roleLabel.setBounds(30, 210, 100, 25);
        add(roleLabel);
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"Quản lý kho", "Nhân viên"});
        roleCombo.setBounds(30, 235, 280, 25);
        add(roleCombo);

        JLabel statusLabel = new JLabel("Trạng thái");
        statusLabel.setBounds(30, 265, 100, 25);
        add(statusLabel);
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Bị Khóa", "Hoạt động"});
        statusCombo.setBounds(30, 290, 280, 25);
        add(statusCombo);

        JButton saveBtn = new JButton("Cập nhật");
        saveBtn.setBounds(30, 330, 120, 30);
        add(saveBtn);

        JButton cancelBtn = new JButton("Hủy");
        cancelBtn.setBounds(190, 330, 120, 30);
        add(cancelBtn);

        setVisible(true);
    }

    private void addLabelAndField(String label, int y) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(30, y, 150, 25);
        add(jLabel);

        JTextField jField = new JTextField();
        jField.setBounds(30, y + 25, 280, 25);
        add(jField);
    }
}
