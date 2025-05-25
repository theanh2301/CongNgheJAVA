/*
package view;

import controller.BCrypt;
import dao.AccountDAO;
import model.Account;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("Login");
        setSize(800, 600);
        setLayout(null); // dùng tọa độ tuyệt đối
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Image icon = Toolkit.getDefaultToolkit().getImage("icon/icon.png");
        setIconImage(icon);

        // Panel trái (màu xanh lá)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(91, 180, 112));
        leftPanel.setBounds(0, 0, 400, 600);
        leftPanel.setLayout(null);

        JLabel logo = new JLabel("\uD83D\uDC64", SwingConstants.CENTER);
        logo.setFont(new Font("SansSerif", Font.PLAIN, 100));
        logo.setBounds(150, 100, 100, 100);
        leftPanel.add(logo);

        JLabel loginText = new JLabel("LOGIN", SwingConstants.CENTER);
        loginText.setFont(new Font("SansSerif", Font.BOLD, 40));
        loginText.setForeground(Color.WHITE);
        loginText.setBounds(100, 250, 200, 50);
        leftPanel.add(loginText);

        add(leftPanel);

        // Panel phải (màu xanh đậm)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(15, 40, 55));
        rightPanel.setBounds(400, 0, 400, 600);
        rightPanel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 100, 300, 25);
        rightPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(50, 130, 300, 30);
        rightPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 180, 300, 25);
        rightPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 210, 300, 30);
        rightPanel.add(passwordField);

        loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(50, 270, 300, 40);
        loginButton.setBackground(new Color(91, 180, 112));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rightPanel.add(loginButton);

        JLabel forgotLabel = new JLabel("Quên mật khẩu ?");
        forgotLabel.setForeground(Color.WHITE);
        forgotLabel.setBounds(50, 330, 300, 25);
        rightPanel.add(forgotLabel);

        add(rightPanel);

        setVisible(true);
    }
    public void checkLogin() {
        String usercheck = usernameField.getText();
        String passwordcheck = passwordField.getText();
        if (usercheck.equals("") || passwordcheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Account acc = AccountDAO.getInstance().selectById(usercheck);
                if (acc == null) {
                    JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại trên hệ thống !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (BCrypt.checkpw(passwordcheck, acc.getPassword())) {
                        if (acc.getStatus() == 1) {
                            try {
                                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                                this.dispose();
                                String role = acc.getRole();
                                if (role.equals("Admin")) {
                                    QuanLiKhoForm ad = new QuanLiKhoForm(acc);
                                    ad.setVisible(true);
//                                    ad.setCurrentAcc(acc);
                                    ad.setName(acc.getFullName());
                                } else if (role.equals("Quản lý kho")) {
                                    QuanLiKhoForm ql = new QuanLiKhoForm();
                                    ql.setVisible(true);
                                    ql.setCurrentAcc(acc);
                                    ql.setName(acc.getFullName());
                                } else if (role.equals("Nhân viên nhập")) {
                                    NhapKhoForm ql = new NhapKhoForm(acc);
                                    ql.setVisible(true);
                                    ql.setName(acc.getFullName());
                                } else if (role.equals("Nhân viên xuất")) {
                                    XuatKhoForm ql = new XuatKhoForm(acc);
                                    ql.setVisible(true);
                                    ql.setName(acc.getFullName());
                                }
                            } catch (UnsupportedLookAndFeelException ex) {
                                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Tài khoản của bạn đã bị khóa !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Sai mật khẩu !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

}
*/
