package view;

import dao.AccountDAO;
import dao.NhaCungCapDAO;
import dao.PhieuNhapDAO;
import format.DateLabelFormatter;
import model.PhieuNhap;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

public class PhieuNhapForm extends JPanel {

    private JPanel functionPanel, searchPanel, datePanel, pricePanel;
    private JButton btnDelete, btnEdit, btnWatch, btnExport, btnImport, btnRefresh;
    private JComboBox<String> cbFilter;
    private JTextField txtSearch, txtPriceFrom, txtPriceTo;
    private JDatePickerImpl datePickerFrom, datePickerTo;
    private JLabel jDateChooserFrom, jDateChooserTo, jPriceChooserFrom, jPriceChooserTo;
    private JTable tblPhieuNhap;

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");

    public PhieuNhapForm() {
        setLayout(null);
        initFunctionPanel();
        initSearchPanel();
        initDateFilterPanel();
        initPriceFilterPanel();

        initTable();
        loadDataToTable();
    }

    private void initFunctionPanel() {
        functionPanel = new JPanel(null);
        functionPanel.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        functionPanel.setBounds(10, 10, 400, 90);

        btnDelete = createIconButton("icons/icons8_delete_40px.png", "Xoá", 20, 25);
        btnEdit = createIconButton("icons/icons8_edit_40px.png", "Sửa", 85, 25);
        btnWatch = createIconButton("icons/icons8_eye_40px.png", "Xem chi tiết", 150, 25);
        btnExport = createIconButton("icons/icons8_spreadsheet_file_40px.png", "Xuất Excel", 260, 25);
        btnImport = createIconButton("icons/icons8_xls_40px.png", "Nhập Excel", 325, 25);

        btnWatch.setFocusPainted(false);
        btnWatch.setContentAreaFilled(false);
        btnWatch.addActionListener(e -> showCTPhieuNhapForm());

        functionPanel.add(btnDelete);
        functionPanel.add(btnEdit);
        functionPanel.add(btnWatch);
        functionPanel.add(btnExport);
        functionPanel.add(btnImport);

        add(functionPanel);
    }

    private void initSearchPanel() {
        searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        searchPanel.setBounds(420, 10, 550, 90);

        cbFilter = new JComboBox<>(new String[]{"Tất cả", "Hoạt động", "Khóa"});
        cbFilter.setBounds(20, 35, 100, 30);

        txtSearch = new JTextField();
        txtSearch.setBounds(130, 35, 295, 30);

        btnRefresh = new JButton("Làm mới");
        btnRefresh.setBounds(435, 35, 100, 30);

        searchPanel.add(cbFilter);
        searchPanel.add(txtSearch);
        searchPanel.add(btnRefresh);

        add(searchPanel);
    }

    private void initDateFilterPanel() {
        datePanel = new JPanel(null);
        datePanel.setBorder(BorderFactory.createTitledBorder("Lọc theo ngày"));
        datePanel.setBounds(10, 110, 440, 80);

        jDateChooserFrom = new JLabel("Từ");
        jDateChooserFrom.setBounds(15, 30, 30, 30);
        datePanel.add(jDateChooserFrom);

        UtilDateModel modelFrom = new UtilDateModel();
        Properties pFrom = new Properties();
        pFrom.put("text.today", "Hôm nay");
        pFrom.put("text.month", "Tháng");
        pFrom.put("text.year", "Năm");
        JDatePanelImpl dateFrom = new JDatePanelImpl(modelFrom, pFrom);
        datePickerFrom = new JDatePickerImpl(dateFrom, new DateLabelFormatter());
        datePickerFrom.setBounds(40, 30, 160, 30);
        datePanel.add(datePickerFrom);

        jDateChooserTo = new JLabel("Đến");
        jDateChooserTo.setBounds(230, 30, 30, 30);
        datePanel.add(jDateChooserTo);

        UtilDateModel modelTo = new UtilDateModel();
        Properties pTo = new Properties();
        pTo.put("text.today", "Hôm nay");
        pTo.put("text.month", "Tháng");
        pTo.put("text.year", "Năm");
        JDatePanelImpl dateTo = new JDatePanelImpl(modelTo, pTo);
        datePickerTo = new JDatePickerImpl(dateTo, new DateLabelFormatter());
        datePickerTo.setBounds(260, 30, 160, 30);
        datePanel.add(datePickerTo);

        add(datePanel);
    }

    private void initPriceFilterPanel() {
        pricePanel = new JPanel(null);
        pricePanel.setBorder(BorderFactory.createTitledBorder("Lọc theo giá"));
        pricePanel.setBounds(460, 110, 450, 80);

        jPriceChooserFrom = new JLabel("Từ");
        jPriceChooserFrom.setBounds(15, 30, 30, 30);
        pricePanel.add(jPriceChooserFrom);

        txtPriceFrom = new JTextField();
        txtPriceFrom.setBounds(50, 30, 150, 30);
        pricePanel.add(txtPriceFrom);

        jPriceChooserTo = new JLabel("Đến");
        jPriceChooserTo.setBounds(230, 30, 30, 30);
        pricePanel.add(jPriceChooserTo);

        txtPriceTo = new JTextField();
        txtPriceTo.setBounds(270, 30, 150, 30);
        pricePanel.add(txtPriceTo);

        add(pricePanel);

    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"STT", "Mã phiếu nhập", "Nhà cung cấp", "Người tạo", "Thời gian tạo", "Tổng tiền"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblPhieuNhap = new JTable(tblModel);
        tblPhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(1);
        tblPhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(2);
        tblPhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblPhieuNhap.getColumnModel().getColumn(3).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(tblPhieuNhap);
        scrollPane.setBounds(10, 200, 1000, 450);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadDataToTable() {
        try {
            ArrayList<PhieuNhap> allPhieuNhap = PhieuNhapDAO.getInstance().selectAll();
            tblModel.setRowCount(0);
            for (int i = 0; i < allPhieuNhap.size(); i++) {
                tblModel.addRow(new Object[]{
                        i + 1, allPhieuNhap.get(i).getMaPhieu(), NhaCungCapDAO.getInstance().selectById(allPhieuNhap.get(i).getNhaCungCap()).getTenNhaCungCap(), AccountDAO.getInstance().selectById(allPhieuNhap.get(i).getNguoiTao()).getFullName(), formatDate.format(allPhieuNhap.get(i).getThoiGianTao()), formatter.format(allPhieuNhap.get(i).getTongTien()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    private void loadDataToTableSearch(ArrayList<PhieuNhap> result) {
        try {
            tblModel.setRowCount(0);
            for (int i = 0; i < result.size(); i++) {
                tblModel.addRow(new Object[]{
                        i + 1, result.get(i).getMaPhieu(), NhaCungCapDAO.getInstance().selectById(result.get(i).getNhaCungCap()).getTenNhaCungCap(), AccountDAO.getInstance().selectById(result.get(i).getNguoiTao()).getFullName(), formatDate.format(result.get(i).getThoiGianTao()), formatter.format(result.get(i).getTongTien()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<PhieuNhap> searchTatCa(String text) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        ArrayList<PhieuNhap> armt = PhieuNhapDAO.getInstance().selectAll();
        for (PhieuNhap phieu : armt) {
            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())
                    || phieu.getNhaCungCap().toLowerCase().contains(text.toLowerCase())
                    || phieu.getNguoiTao().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }
        }
        return result;
    }

    public ArrayList<PhieuNhap> searchMaPhieuNhap(String text) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        ArrayList<PhieuNhap> armt = PhieuNhapDAO.getInstance().selectAll();
        for (PhieuNhap phieu : armt) {
            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }
        }
        return result;
    }

    public ArrayList<PhieuNhap> searchNhaCungCap(String text) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        ArrayList<PhieuNhap> armt = PhieuNhapDAO.getInstance().selectAll();
        for (PhieuNhap phieu : armt) {
            if (phieu.getNhaCungCap().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }
        }
        return result;
    }

    public ArrayList<PhieuNhap> searchNguoiTao(String text) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        ArrayList<PhieuNhap> armt = PhieuNhapDAO.getInstance().selectAll();
        for (PhieuNhap phieu : armt) {
            if (phieu.getNguoiTao().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }
        }
        return result;
    }

    private void showCTPhieuNhapForm() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Chi Tiết Phiếu Nhập", true);
        dialog.setContentPane(new CTPhieuNhapForm());
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private JButton createIconButton(String iconPath, String tooltip, int x, int y) {
        JButton button = new JButton(new ImageIcon(iconPath));
        button.setBounds(x, y, 55, 55);
        button.setToolTipText(tooltip);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
