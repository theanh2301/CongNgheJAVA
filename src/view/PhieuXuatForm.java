package view;

import format.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class PhieuXuatForm extends JPanel {

    public PhieuXuatForm() {

        setLayout(null);
        // Panel chức năng
        JPanel functionPanel = new JPanel(null);
        functionPanel.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        functionPanel.setBounds(10, 10, 400, 90);

        JButton btnDelete = createIconButton("icons/icons8_delete_40px.png", "Xoá", 20, 25);
        JButton btnEdit = createIconButton("icons/icons8_edit_40px.png", "Sửa", 85, 25);
        JButton btnWatch = createIconButton("icons/icons8_eye_40px.png", "Xem chi tiết", 150, 25);
        JButton btnExport = createIconButton("icons/icons8_spreadsheet_file_40px.png", "Xuất Excel", 260, 25);
        JButton btnImport = createIconButton("icons/icons8_xls_40px.png", "Nhập Excel", 325, 25);

        // Them su kien cho nuts
        btnWatch.addActionListener(e -> showCTPhieuXuatForm());

        // Nút reset không đổ nền
        btnWatch.setFocusPainted(false);
        btnWatch.setContentAreaFilled(false);

        // Thêm vào function panel
        functionPanel.add(btnDelete);
        functionPanel.add(btnEdit);
        functionPanel.add(btnWatch);
        functionPanel.add(btnExport);
        functionPanel.add(btnImport);

        add(functionPanel);

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        searchPanel.setBounds(420, 10, 550, 90);

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

        // Panel loc theo ngay
        JPanel datePanel = new JPanel();
        datePanel.setLayout(null);
        datePanel.setBounds(10, 110, 440, 80);
        datePanel.setBorder(BorderFactory.createTitledBorder("Lọc theo ngày"));

        JLabel fromLabel = new JLabel("Từ");
        fromLabel.setBounds(15, 30, 30, 30);

        UtilDateModel modelFrom = new UtilDateModel();
        Properties pFrom = new Properties();
        pFrom.put("text.today", "Hôm nay");
        pFrom.put("text.month", "Tháng");
        pFrom.put("text.year", "Năm");
        JDatePanelImpl dateFrom = new JDatePanelImpl(modelFrom, pFrom);
        JDatePickerImpl datePickerFrom = new JDatePickerImpl(dateFrom, new DateLabelFormatter());
        datePickerFrom.setBounds(40, 30, 160, 30);

        datePanel.add(fromLabel);
        datePanel.add(datePickerFrom);

        JLabel toLabel = new JLabel("Đến");
        toLabel.setBounds(230, 30, 30, 30);

        UtilDateModel modelTo = new UtilDateModel();
        Properties pTo = new Properties();
        pTo.put("text.today", "Hôm nay");
        pTo.put("text.month", "Tháng");
        pTo.put("text.year", "Năm");
        JDatePanelImpl dateTo = new JDatePanelImpl(modelTo, pTo);
        JDatePickerImpl datePickerTo = new JDatePickerImpl(dateTo, new DateLabelFormatter());
        datePickerTo.setBounds(260, 30, 160, 30);

        datePanel.add(toLabel);
        datePanel.add(datePickerTo);

        setLayout(null);
        add(datePanel);

        // Panel loc the gia
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(null);
        pricePanel.setBounds(460, 110, 450, 80);
        pricePanel.setBorder(BorderFactory.createTitledBorder("Lọc theo giá"));

        JLabel fromLabel1 = new JLabel("Từ");
        fromLabel1.setBounds(15, 30, 30, 30);
        pricePanel.add(fromLabel1);

        JTextField txtPriceFrom = new JTextField();
        txtPriceFrom.setBounds(50, 30, 150, 30);
        pricePanel.add(txtPriceFrom);


        JLabel toLabel1 = new JLabel("Đến");
        toLabel1.setBounds(230, 30, 30, 30);
        pricePanel.add(toLabel1);

        JTextField txtPriceTo= new JTextField();
        txtPriceTo.setBounds(270, 30, 150, 30);
        pricePanel.add(txtPriceTo);


        setLayout(null);
        add(pricePanel);


    }

    private void showCTPhieuXuatForm() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Chi Tiết Phiếu Nhập", true);
        dialog.setContentPane(new CTPhieuXuatForm());
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
