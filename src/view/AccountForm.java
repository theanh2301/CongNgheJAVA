package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AccountForm extends JPanel {

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
        functionPanel.add(createIconButton("icons/icons8_delete_40px.png", "Xoá", null));
        functionPanel.add(Box.createHorizontalStrut(10));
        functionPanel.add(createIconButton("icons/icons8_edit_40px.png", "Sửa", null));
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
        String[] columnNames = {"Tên tài khoản", "Tên đăng nhập", "Vai trò", "Trạng thái"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
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
        dialog.setContentPane(new AddAccountForm());
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản lý tài khoản");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1050, 700);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new AccountForm());
            frame.setVisible(true);
        });
    }

}

