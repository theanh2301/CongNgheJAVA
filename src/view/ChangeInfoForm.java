package view;

import javax.swing.*;

public class ChangeInfoForm extends JFrame {
    public ChangeInfoForm() {
        setTitle("Thay Đổi Thông Tin");
        setSize(350, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("THAY ĐỔI THÔNG TIN", SwingConstants.CENTER);
        title.setBounds(0, 10, 350, 30);
        title.setOpaque(true);
        title.setBackground(new java.awt.Color(91, 166, 79));
        title.setForeground(java.awt.Color.WHITE);
        add(title);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 50, 350, 230);
        JPanel infoTab = new JPanel(null);

        addLabelAndField(infoTab, "Họ và tên", 10);
        addLabelAndField(infoTab, "Email", 60);
        addLabelAndField(infoTab, "Mật khẩu", 110);

        JButton saveBtn = new JButton("Lưu thay đổi");
        saveBtn.setBounds(100, 160, 130, 30);
        infoTab.add(saveBtn);

        tabbedPane.add("Thông tin", infoTab);
        tabbedPane.add("Mật khẩu", new JPanel());  // Dummy tab

        add(tabbedPane);
        setVisible(true);
    }

    private void addLabelAndField(JPanel panel, String label, int y) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(20, y, 150, 25);
        panel.add(jLabel);

        JTextField jField = new JTextField();
        jField.setBounds(20, y + 25, 300, 25);
        panel.add(jField);
    }

}
