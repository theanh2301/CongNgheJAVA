package view;

import dao.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AccountForm extends JPanel {

    private JTable tblAccount;
    private DefaultTableModel tblModel;
    private ArrayList<Account> accounts = AccountDAO.getInstance().selectAll();


    public AccountForm() {
        setLayout(new BorderLayout());

        // Panel chứa 2 phần trên: chức năng và tìm kiếm
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // === Panel chức năng ===
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new BoxLayout(functionPanel, BoxLayout.X_AXIS));
        functionPanel.setBorder(BorderFactory.createTitledBorder("Chức năng"));

        functionPanel.add(Box.createHorizontalStrut(10));
        functionPanel.add(createIconButton("icons/icons8_add_40px.png", "Thêm", this::showAddAccountForm));
        functionPanel.add(Box.createHorizontalStrut(10));
        functionPanel.add(createIconButton("icons/icons8_delete_40px.png", "Xoá", this::btnDeleteAccountActionPerformed));
        functionPanel.add(Box.createHorizontalStrut(10));
        functionPanel.add(createIconButton("icons/icons8_edit_40px.png", "Sửa", this::btnEditAccountActionPerformed));
        functionPanel.add(Box.createHorizontalStrut(10));
        JButton btnReset = createIconButton("icons/icons8-update-left-rotation-40.png", "Đặt lại", null);
        btnReset.setContentAreaFilled(false);
        functionPanel.add(btnReset);
        functionPanel.add(Box.createHorizontalStrut(10));
        functionPanel.add(createIconButton("icons/icons8_spreadsheet_file_40px.png", "Xuất Excel", null));
        functionPanel.add(Box.createHorizontalStrut(10));
        functionPanel.add(createIconButton("icons/icons8_xls_40px.png", "Nhập Excel", null));
        functionPanel.add(Box.createHorizontalStrut(10));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 5);
        topPanel.add(functionPanel, gbc);

        // === Panel tìm kiếm ===
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

        GridBagConstraints searchGbc = new GridBagConstraints();

        JComboBox<String> cbFilter = new JComboBox<>(new String[]{"Tất cả", "Hoạt động", "Khóa"});
        searchGbc.gridx = 0;
        searchGbc.gridy = 0;
        searchGbc.insets = new Insets(5, 5, 5, 5);
        searchPanel.add(cbFilter, searchGbc);

        JTextField txtSearch = new JTextField(20);
        searchGbc.gridx = 1;
        searchPanel.add(txtSearch, searchGbc);

        JButton btnRefresh = new JButton("Làm mới");
        searchGbc.gridx = 2;
        searchPanel.add(btnRefresh, searchGbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(searchPanel, gbc);

        add(topPanel, BorderLayout.NORTH);

        // === Bảng dữ liệu ===
        // === Bảng dữ liệu ===
        String[] columnNames = {"Tên tài khoản", "Tên đăng nhập", "Vai trò", "Trạng thái"};
        tblModel = new DefaultTableModel(columnNames, 0);
        tblAccount = new JTable(tblModel); // GÁN cho biến thành viên tblAccount
        tblAccount.setDefaultEditor(Object.class, null); // Đặt bảng không cho chỉnh sửa

        JScrollPane scrollPane = new JScrollPane(tblAccount);
        add(scrollPane, BorderLayout.CENTER);

        initTable();
        loadDataToTable(accounts);

    }

    // Tạo nút có icon và tooltip + action
    private JButton createIconButton(String iconPath, String tooltip, Runnable action) {
        JButton button = new JButton(new ImageIcon(iconPath));
        button.setToolTipText(tooltip);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(55, 55));
        if (action != null) {
            button.addActionListener(e -> action.run());
        }
        return button;
    }

    // Hiển thị form thêm tài khoản
    private void showAddAccountForm() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm tài khoản", true);
        dialog.setContentPane(new AddAccountForm(this));
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"Tên tài khoản", "Tên đăng nhập", "Email", "Vai trò", "Trạng thái"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblAccount.setModel(tblModel);
    }

    public void loadDataToTable(ArrayList<Account> acc) {
        try {
            tblModel.setRowCount(0);
            for (Account i : acc) {
                tblModel.addRow(new Object[]{
                        i.getFullName(), i.getUser(), i.getEmail(), i.getRole(), i.getStatus() == 0 ? "Đã khóa" : "Hoạt động"
                });
            }
        } catch (Exception e) {
        }
    }

    public Account getAccountSelect() {
        int i_row = tblAccount.getSelectedRow();
        Account acc = AccountDAO.getInstance().selectById(tblAccount.getValueAt(i_row, 1).toString());
        return acc;
    }

    private void btnEditAccountActionPerformed() {
        if (tblAccount.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần chỉnh sửa !");
        } else {
            if (getAccountSelect().getRole().equals("Admin")) {
                JOptionPane.showMessageDialog(this, "Không thể sửa tài khoản admin tại đây !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            } else {
                JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Sửa tài khoản", true);
                dialog.setContentPane(new UpdateAccountForm(this));
                dialog.pack();
                dialog.setLocationRelativeTo(this);
                dialog.setResizable(false);
                dialog.setVisible(true);

            }
        }
    }

    private void btnDeleteAccountActionPerformed() {
        if (tblAccount.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xoá !");
        } else {
            Account select = getAccountSelect();
            if (select.getRole().equals("Admin")) {
                JOptionPane.showMessageDialog(this, "Không thể xóa tài khoản admin !");
            } else {
                int checkVl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tài khoản này ?", "Xác nhận xóa tài khoản", JOptionPane.YES_NO_OPTION);
                if (checkVl == JOptionPane.YES_OPTION) {
                    try {
                        AccountDAO.getInstance().delete(select);
                        JOptionPane.showMessageDialog(this, "Xoá thành công tài khoản !");
                        loadDataToTable(AccountDAO.getInstance().selectAll());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Xoá thất bại !");
                    }
                }
            }
        }
    }

}

