package view;

import dao.LaptopDAO;
import dao.MayTinhDAO;
import dao.PCDAO;
import model.Laptop;
import model.MayTinh;
import model.PC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ProductForm extends JPanel {

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");


    public ProductForm() {

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

        // Thêm vào function panel
        functionPanel.add(btnAdd);
        functionPanel.add(btnDelete);
        functionPanel.add(btnEdit);
        functionPanel.add(btnReset);
        functionPanel.add(btnExport);
        functionPanel.add(btnImport);

        // Sự kiện cho btnAdd
        btnAdd.addActionListener(e -> showAddProductForm());

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

        initTable();
        loadDataToTable();
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"Mã máy", "Tên máy", "Số lượng", "Đơn giá", "Bộ xử lí", "RAM", "Bộ nhớ", "Loại máy"};
        tblModel.setColumnIdentifiers(headerTbl);
        JTable tblSanPham = new JTable(tblModel);
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(5).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(6).setPreferredWidth(5);
        JScrollPane scrollPane = new JScrollPane(tblSanPham);
        scrollPane.setBounds(10, 110, 1000, 500);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadDataToTable() {
        try {
            MayTinhDAO mtdao = new MayTinhDAO();
            ArrayList<MayTinh> armt = mtdao.selectAll();
            tblModel.setRowCount(0);
            for (MayTinh i : armt) {
                if (i.getTrangThai() == 1) {
                    String loaimay;
                    if (LaptopDAO.getInstance().isLaptop(i.getMaMay()) == true) {
                        loaimay = "Laptop";
                    } else {
                        loaimay = "PC/Case";
                    }
                    tblModel.addRow(new Object[]{
                            i.getMaMay(), i.getTenMay(), i.getSoLuong(), formatter.format(i.getGia()) + "đ", i.getTenCpu(), i.getRam(), i.getRom(), loaimay
                    });
                }
            }
        } catch (Exception e) {
        }
    }

    private JButton createIconButton(String iconPath, String tooltip, int x, int y) {
        JButton button = new JButton(new ImageIcon(iconPath));
        button.setBounds(x, y, 55, 55);
        button.setToolTipText(tooltip);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void showAddProductForm() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm tài khoản", true);
        dialog.setContentPane(new AddProductForm());
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

}
