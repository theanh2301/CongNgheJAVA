package view;

import javax.swing.*;

public class RecoverPasswordForm extends JFrame {
    public RecoverPasswordForm() {
        setTitle("Khôi Phục Mật Khẩu");
        setSize(450, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("KHÔI PHỤC MẬT KHẨU", SwingConstants.CENTER);
        title.setBounds(0, 10, 450, 30);
        title.setOpaque(true);
        title.setBackground(new java.awt.Color(91, 166, 79));
        title.setForeground(java.awt.Color.WHITE);
        add(title);

        JLabel emailLabel = new JLabel("Nhập địa chỉ email tài khoản");
        emailLabel.setBounds(30, 60, 300, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(30, 90, 250, 25);
        add(emailField);

        JButton sendBtn = new JButton("Gửi mã xác nhận");
        sendBtn.setBounds(290, 90, 130, 25);
        add(sendBtn);

        setVisible(true);
    }

}
