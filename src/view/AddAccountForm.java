package view;

import controller.BCrypt;
import dao.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class AddAccountForm extends JPanel {
    private JTextField tfFullName, tfUsername, tfEmail;
    private JPasswordField pfPassword;
    private JComboBox<String> cbRole;
    private JButton btnSave, btnCancel;

    private AccountForm homeAcc;

    public AddAccountForm(AccountForm homeAcc) {
        this(); // gọi constructor mặc định
        this.homeAcc = homeAcc;
    }

    public AddAccountForm() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(new LineBorder(new Color(218, 165, 32), 2)); // viền vàng nhạt

        // Tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setBackground(new Color(46, 139, 87));
        titlePanel.setBounds(0, 0, 450, 50);

        JLabel titleLabel = new JLabel("THÊM TÀI KHOẢN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 10, 450, 30);
        titlePanel.add(titleLabel);

        add(titlePanel);

        // Nhãn và input
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        int labelWidth = 120;
        int fieldWidth = 280;
        int height = 25;
        int xLabel = 20;
        int xField = 150;
        int y = 70;

        // Tên tài khoản
        JLabel lbFullName = new JLabel("Tên tài khoản:");
        lbFullName.setFont(labelFont);
        lbFullName.setBounds(xLabel, y, labelWidth, height);
        tfFullName = new JTextField();
        tfFullName.setBounds(xField, y, fieldWidth, height);
        y += 40;

        // Tên đăng nhập
        JLabel lbUsername = new JLabel("Tên đăng nhập:");
        lbUsername.setFont(labelFont);
        lbUsername.setBounds(xLabel, y, labelWidth, height);
        tfUsername = new JTextField();
        tfUsername.setBounds(xField, y, fieldWidth, height);
        y += 40;

        // Email
        JLabel lbEmail = new JLabel("Email:");
        lbEmail.setFont(labelFont);
        lbEmail.setBounds(xLabel, y, labelWidth, height);
        tfEmail = new JTextField();
        tfEmail.setBounds(xField, y, fieldWidth, height);
        y += 40;

        // Mật khẩu
        JLabel lbPassword = new JLabel("Mật khẩu:");
        lbPassword.setFont(labelFont);
        lbPassword.setBounds(xLabel, y, labelWidth, height);
        pfPassword = new JPasswordField();
        pfPassword.setBounds(xField, y, fieldWidth, height);
        y += 40;

        // Vai trò
        JLabel lbRole = new JLabel("Vai trò:");
        lbRole.setFont(labelFont);
        lbRole.setBounds(xLabel, y, labelWidth, height);
        cbRole = new JComboBox<>(new String[]{"Quản lý kho", "Nhân viên", "Quản trị"});
        cbRole.setBounds(xField, y, fieldWidth, height);
        y += 50;

        // Buttons
        btnSave = new JButton("Lưu");
        btnSave.setBounds(xField, y, 100, 30);
        btnSave.setBackground(Color.WHITE);
        btnSave.setBorder(new LineBorder(new Color(46, 139, 87), 1));
        btnSave.setForeground(new Color(46, 139, 87));

        btnCancel = new JButton("Hủy");
        btnCancel.setBounds(xField + 120, y, 100, 30);
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setBorder(new LineBorder(Color.GRAY, 1));
        btnCancel.setForeground(Color.DARK_GRAY);
        btnCancel.addActionListener(e -> clearFields());

        // Thêm vào panel
        add(lbFullName);
        add(tfFullName);
        add(lbUsername);
        add(tfUsername);
        add(lbEmail);
        add(tfEmail);
        add(lbPassword);
        add(pfPassword);
        add(lbRole);
        add(cbRole);
        add(btnSave);
        add(btnCancel);

        // Đặt kích thước tổng thể
        setPreferredSize(new Dimension(450, y + 80));

        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveClick(evt);
            }
        });
    }

    private void clearFields() {
        tfFullName.setText("");
        tfUsername.setText("");
        tfEmail.setText("");
        pfPassword.setText("");
        cbRole.setSelectedIndex(0);
    }

    private void btnSaveClick(java.awt.event.MouseEvent evt) {
        String fullName = tfFullName.getText();
        String user = tfUsername.getText();
        String password = BCrypt.hashpw(pfPassword.getText(), BCrypt.gensalt(12));
        String role = cbRole.getSelectedItem().toString();
        String email = tfEmail.getText();
        if (fullName.equals("") || user.equals("") || password.equals("") || role.equals("") || email.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !", "Cảnh báo ", JOptionPane.WARNING_MESSAGE);
        } else {
            if (AccountDAO.getInstance().selectById(user) == null) {
                Account acc = new Account(fullName, user, password, role, 1, email);
                AccountDAO.getInstance().insert(acc);
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
                homeAcc.loadDataToTable(AccountDAO.getInstance().selectAll());
            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }

        }

    }
}
