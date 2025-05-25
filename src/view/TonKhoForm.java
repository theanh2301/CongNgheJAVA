package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TonKhoForm extends JPanel {

    public TonKhoForm() {

        setLayout(null);
        // Panel chức năng
        JPanel functionPanel = new JPanel(null);
        functionPanel.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        functionPanel.setBounds(10, 10, 440, 90);

        JButton btnAdd = createIconButton("icons/icons8_add_40px.png", "Thêm", 15, 25);
        JButton btnDelete = createIconButton("icons/icons8_delete_40px.png", "Xoá", 80, 25);
        JButton btnEdit = createIconButton("icons/icons8_edit_40px.png", "Sửa", 145, 25);
        JButton btnReset = createIconButton("icons/icons8-update-left-rotation-40.png", "Đặt lại", 210, 25);
        JButton btnExport = createIconButton("icons/icons8_spreadsheet_file_40px.png", "Xuất Excel", 305, 25);
        JButton btnImport = createIconButton("icons/icons8_xls_40px.png", "Nhập Excel", 370, 25);

        // Nút reset không đổ nền
        btnReset.setFocusPainted(false);
        btnReset.setContentAreaFilled(false);

        // Sự kiện cho nuts
        btnAdd.addActionListener(e -> showAddAccountForm());

        // Thêm vào function panel
        functionPanel.add(btnAdd);
        functionPanel.add(btnDelete);
        functionPanel.add(btnEdit);
        functionPanel.add(btnReset);
        functionPanel.add(btnExport);
        functionPanel.add(btnImport);



        add(functionPanel);

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        searchPanel.setBounds(460, 10, 550, 90);

        JComboBox<String> cbFilter = new JComboBox<>(new String[]{"Tất cả", "Hoạt động", "Khóa"});
        cbFilter.setBounds(20, 35, 100, 30);
        searchPanel.add(cbFilter);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(130, 35, 295, 30);
        searchPanel.add(txtSearch);

        JButton btnRefresh = new JButton("Làm mới");
        btnRefresh.setBounds(435, 35, 100, 30);
        searchPanel.add(btnRefresh);

        add(searchPanel);

        // Bảng dữ liệu
        String[] columnNames = {"Tên tài khoản", "Tên đăng nhập", "Vai trò", "Trạng thái"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 110, 1000, 540);
        add(scrollPane);

    }

    // Hàm tạo nút có icon và tooltip
    private JButton createIconButton(String iconPath, String tooltip, int x, int y) {
        JButton button = new JButton(new ImageIcon(iconPath));
        button.setBounds(x, y, 55, 55);
        button.setToolTipText(tooltip);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Mở form thêm tài khoản
    private void showAddAccountForm() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm tài khoản", true);
        dialog.setContentPane(new AddAccountForm());
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    /*// Mở form thêm tài khoản
    private void showAddAccountForm() {
        JDialog dialog = new JDialog(this, "Thêm tài khoản", true);
        dialog.setContentPane(new AddAccountForm()); // AddAccountForm cần là JPanel
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }*/

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccountForm().setVisible(true));
    }*/
}
