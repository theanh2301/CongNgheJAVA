package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class XuatHangForm extends JPanel {

    public XuatHangForm() {
        setLayout(null);
        setPreferredSize(new Dimension(950, 550));

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        searchPanel.setBounds(20, 10, 460, 80);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(40, 40, 300, 30);
        add(txtSearch);

        JButton btnRefresh = new JButton("Làm mới");
        btnRefresh.setBounds(350, 40, 100, 30);
        add(btnRefresh);

        add(searchPanel);

        // Bảng trái (danh sách sản phẩm)
        String[] leftCols = {"Mã máy", "Tên máy", "Số lượng", "Đơn giá"};
        JTable tblLeft = new JTable(new DefaultTableModel(leftCols, 0));
        JScrollPane scrollLeft = new JScrollPane(tblLeft);
        scrollLeft.setBounds(20, 100, 460, 480);
        add(scrollLeft);

        // Số lượng + nút thêm
        JLabel lblQuantity = new JLabel("Số lượng");
        lblQuantity.setBounds(100, 600, 70, 30);
        add(lblQuantity);

        JTextField txtQuantity = new JTextField("1");
        txtQuantity.setBounds(160, 600, 50, 30);
        add(txtQuantity);

        JButton btnAdd = new JButton("➕ Thêm");
        btnAdd.setBounds(220, 600, 100, 30);
        add(btnAdd);

        // Form phải (thông tin phiếu)
        JLabel lblMaPhieu = new JLabel("Mã phiếu nhập");
        lblMaPhieu.setBounds(510, 20, 100, 25);
        add(lblMaPhieu);

        JTextField txtMaPhieu = new JTextField();
        txtMaPhieu.setBounds(620, 20, 350, 25);
        add(txtMaPhieu);

        JLabel lblNCC = new JLabel("Nhà cung cấp");
        lblNCC.setBounds(510, 60, 100, 25);
        add(lblNCC);

        JComboBox<String> cbNCC = new JComboBox<>(new String[]{"-- Chọn --"});
        cbNCC.setBounds(620, 60, 350, 25);
        add(cbNCC);

        JLabel lblNguoiTao = new JLabel("Người tạo phiếu");
        lblNguoiTao.setBounds(510, 100, 100, 25);
        add(lblNguoiTao);

        JTextField txtNguoiTao = new JTextField();
        txtNguoiTao.setBounds(620, 100, 350, 25);
        add(txtNguoiTao);

        // Bảng phải (sản phẩm được thêm)
        String[] rightCols = {"STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá"};
        JTable tblRight = new JTable(new DefaultTableModel(rightCols, 0));
        JScrollPane scrollRight = new JScrollPane(tblRight);
        scrollRight.setBounds(510, 140, 490, 400);
        add(scrollRight);

        // Các nút dưới bảng phải
        JButton btnImportExcel = new JButton("Nhập excel");
        btnImportExcel.setBounds(510, 550, 150, 30);
        add(btnImportExcel);

        JButton btnEdit = new JButton("Sửa số lượng");
        btnEdit.setBounds(680, 550, 150, 30);
        add(btnEdit);

        JButton btnDelete = new JButton("Xoá sản phẩm");
        btnDelete.setBounds(850, 550, 150, 30);
        add(btnDelete);

        // Tổng tiền
        JLabel lblTotal = new JLabel("Tổng tiền:");
        lblTotal.setBounds(550, 600, 100, 30);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTotal);

        JLabel lblTotalValue = new JLabel("0đ");
        lblTotalValue.setBounds(650, 600, 200, 30);
        lblTotalValue.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotalValue.setForeground(Color.RED);
        add(lblTotalValue);

        // Nút tạo phiếu
        JButton btnCreate = new JButton("Tạo phiếu");
        btnCreate.setBounds(850, 600, 150, 35);
        add(btnCreate);
    }

}
