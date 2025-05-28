package view;

import dao.NhaCungCapDAO;
import model.NhaCungCap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class NhaCungCapForm extends JPanel {

    private JTable tblNCC;
    private DefaultTableModel tblModel;
    private ArrayList<NhaCungCap> armt;

    private JButton btnAdd, btnDelete, btnEdit, btnReset, btnExport, btnImport;
    private JComboBox<String> cbFilter;
    private JTextField txtSearch;
    private JButton btnRefresh;

    public NhaCungCapForm() {
        setLayout(null);

        initFunctionPanel();
        initSearchPanel();
        initTablePanel();

        initTable();
        armt = NhaCungCapDAO.getInstance().selectAll();
        loadDataToTable(armt);
    }

    private void initFunctionPanel() {
        JPanel functionPanel = new JPanel(null);
        functionPanel.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        functionPanel.setBounds(10, 10, 440, 90);

        btnAdd = createIconButton("icons/icons8_add_40px.png", "Thêm", 15, 25);
        btnDelete = createIconButton("icons/icons8_delete_40px.png", "Xoá", 80, 25);
        btnEdit = createIconButton("icons/icons8_edit_40px.png", "Sửa", 145, 25);
        btnReset = createIconButton("icons/icons8-update-left-rotation-40.png", "Đặt lại", 210, 25);
        btnExport = createIconButton("icons/icons8_spreadsheet_file_40px.png", "Xuất Excel", 305, 25);
        btnImport = createIconButton("icons/icons8_xls_40px.png", "Nhập Excel", 370, 25);

        btnReset.setFocusPainted(false);
        btnReset.setContentAreaFilled(false);

        functionPanel.add(btnAdd);
        functionPanel.add(btnDelete);
        functionPanel.add(btnEdit);
        functionPanel.add(btnReset);
        functionPanel.add(btnExport);
        functionPanel.add(btnImport);

        btnAdd.addActionListener(e -> showAddNhaCungCapForm());

        add(functionPanel);
    }

    private void initSearchPanel() {
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        searchPanel.setBounds(460, 10, 550, 90);

        cbFilter = new JComboBox<>(new String[]{"Tất cả", "Hoạt động", "Khóa"});
        cbFilter.setBounds(20, 35, 100, 30);
        searchPanel.add(cbFilter);

        txtSearch = new JTextField();
        txtSearch.setBounds(130, 35, 295, 30);
        searchPanel.add(txtSearch);

        btnRefresh = new JButton("Làm mới");
        btnRefresh.setBounds(435, 35, 100, 30);
        searchPanel.add(btnRefresh);

        add(searchPanel);
    }

    private void initTablePanel() {
        tblNCC = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblNCC);
        scrollPane.setBounds(10, 110, 1000, 400);
        add(scrollPane);

        tblNCC.setDefaultEditor(Object.class, null); // Không cho chỉnh sửa trực tiếp
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"Mã NCC", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblNCC.setModel(tblModel);

        tblNCC.getColumnModel().getColumn(0).setPreferredWidth(1);
        tblNCC.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblNCC.getColumnModel().getColumn(2).setPreferredWidth(2);
        tblNCC.getColumnModel().getColumn(3).setPreferredWidth(350);
    }

    public void loadDataToTable(ArrayList<NhaCungCap> nccList) {
        tblModel.setRowCount(0);
        for (NhaCungCap ncc : nccList) {
            tblModel.addRow(new Object[]{
                    ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSdt(), ncc.getDiaChi()
            });
        }
    }

    private JButton createIconButton(String iconPath, String tooltip, int x, int y) {
        JButton button = new JButton(new ImageIcon(iconPath));
        button.setBounds(x, y, 55, 55);
        button.setToolTipText(tooltip);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void showAddNhaCungCapForm() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm nhà cung cấp", true);
        dialog.setContentPane(new AddNhaCungCap()); // JPanel chứa form thêm
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
}
