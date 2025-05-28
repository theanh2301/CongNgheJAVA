package view;

import dao.AccountDAO;
import model.Account;

import javax.swing.*;
import java.awt.*;

public class UpdateAccountForm extends JPanel {

    private AccountForm homeAcc;
    private JTextField txtfullname;
    private JTextField txtusername;
    private JTextField txtEmail;
    private JComboBox<String> vaitro;
    private JComboBox<String> trangthai;

    public UpdateAccountForm(AccountForm homeAcc) {
        this();
        this.homeAcc = homeAcc;
        Account acc = homeAcc.getAccountSelect();
        txtfullname.setText(acc.getFullName());
        txtusername.setText(acc.getUser());
        txtusername.setEditable(false);
        txtEmail.setText(acc.getEmail());
        vaitro.setSelectedItem(acc.getRole());
        trangthai.setSelectedIndex(acc.getStatus());
    }

    public UpdateAccountForm() {
        setLayout(null);
        setPreferredSize(new Dimension(350, 420));

        JLabel title = new JLabel("CẬP NHẬT THÔNG TIN", SwingConstants.CENTER);
        title.setBounds(0, 10, 350, 30);
        title.setOpaque(true);
        title.setBackground(new Color(91, 166, 79)); // Xanh lá
        title.setForeground(Color.WHITE);
        add(title);

        JLabel nameLabel = new JLabel("Tên tài khoản");
        nameLabel.setBounds(30, 60, 150, 25);
        add(nameLabel);
        txtfullname = new JTextField();
        txtfullname.setBounds(30, 85, 280, 25);
        add(txtfullname);

        JLabel usernameLabel = new JLabel("Tên đăng nhập");
        usernameLabel.setBounds(30, 110, 150, 25);
        add(usernameLabel);
        txtusername = new JTextField();
        txtusername.setBounds(30, 135, 280, 25);
        add(txtusername);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(30, 160, 150, 25);
        add(emailLabel);
        txtEmail = new JTextField();
        txtEmail.setBounds(30, 185, 280, 25);
        add(txtEmail);

        JLabel roleLabel = new JLabel("Vai trò");
        roleLabel.setBounds(30, 210, 100, 25);
        add(roleLabel);
        vaitro = new JComboBox<>(new String[]{"Quản lý kho", "Nhân viên"});
        vaitro.setBounds(30, 235, 280, 25);
        add(vaitro);

        JLabel statusLabel = new JLabel("Trạng thái");
        statusLabel.setBounds(30, 265, 100, 25);
        add(statusLabel);
        trangthai = new JComboBox<>(new String[]{"Bị Khóa", "Hoạt động"});
        trangthai.setBounds(30, 290, 280, 25);
        add(trangthai);

        JButton saveBtn = new JButton("Cập nhật");
        saveBtn.setBounds(30, 330, 120, 30);
        add(saveBtn);
        saveBtn.addActionListener(e -> updateAccount());

        JButton cancelBtn = new JButton("Hủy");
        cancelBtn.setBounds(190, 330, 120, 30);
        add(cancelBtn);
        cancelBtn.addActionListener(e -> clearFields()); // hoặc ẩn panel nếu bạn thêm logic vào bên ngoài
    }

    private void updateAccount() {
        String fullName = txtfullname.getText();
        String user = txtusername.getText();
        String password = homeAcc.getAccountSelect().getPassword();
        String role = vaitro.getSelectedItem().toString();
        String email = txtEmail.getText();
        int status = trangthai.getSelectedIndex();
        Account acc = new Account(fullName, user, password, role, status, email);
        AccountDAO.getInstance().update(acc);
        homeAcc.loadDataToTable(AccountDAO.getInstance().selectAll());
        JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
        clearFields();
    }

    private void clearFields() {
        txtfullname.setText("");
        txtusername.setText("");
        txtEmail.setText("");
        vaitro.setSelectedIndex(0);
        trangthai.setSelectedIndex(0);
    }

    public void loadAccount(Account account) {
        txtfullname.setText(account.getFullName());
        txtusername.setText(account.getUser());
        txtEmail.setText(account.getEmail());
        vaitro.setSelectedItem(account.getRole());
        trangthai.setSelectedIndex(account.getStatus());
    }
}
