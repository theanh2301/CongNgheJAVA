package view;

import controller.SearchProduct;
import controller.WritePDF;
import dao.ChiTietPhieuNhapDAO;
import dao.MayTinhDAO;
import dao.NhaCungCapDAO;
import dao.PhieuNhapDAO;
import model.ChiTietPhieu;
import model.MayTinh;
import model.NhaCungCap;
import model.PhieuNhap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class NhapHangForm extends JPanel {

    private JComboBox<String> cbNCC;
    private JTable tblSanPham, tblNhapHang;
    private JLabel lblTotalValue;
    private JTextField txtNguoiTao, txtMaPhieu, txtSoLuong, txtSearch;

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    private ArrayList<MayTinh> allProduct;
    private String MaPhieu;
    private ArrayList<ChiTietPhieu> CTPhieu;
    private static final ArrayList<NhaCungCap> arrNcc = NhaCungCapDAO.getInstance().selectAll();

    public NhapHangForm() {
        setLayout(null);
        setPreferredSize(new Dimension(950, 550));

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        searchPanel.setBounds(20, 10, 460, 80);

        txtSearch = new JTextField();
        txtSearch.setBounds(40, 40, 300, 30);
        add(txtSearch);

        JButton btnRefresh = new JButton("Làm mới");
        btnRefresh.setBounds(350, 40, 100, 30);
        add(btnRefresh);

        add(searchPanel);

        // Bảng trái (danh sách sản phẩm)
        String[] leftCols = {"Mã máy", "Tên máy", "Số lượng", "Đơn giá"};
        tblSanPham = new JTable(new DefaultTableModel(leftCols, 0));
        JScrollPane scrollLeft = new JScrollPane(tblSanPham);
        scrollLeft.setBounds(20, 100, 460, 480);
        add(scrollLeft);

        // Số lượng + nút thêm
        JLabel lblQuantity = new JLabel("Số lượng");
        lblQuantity.setBounds(100, 600, 70, 30);
        add(lblQuantity);

        txtSoLuong = new JTextField("1");
        txtSoLuong.setBounds(160, 600, 50, 30);
        add(txtSoLuong);

        JButton btnAdd = new JButton("➕ Thêm");
        btnAdd.setBounds(220, 600, 100, 30);
        add(btnAdd);

        // Form phải (thông tin phiếu)
        JLabel lblMaPhieu = new JLabel("Mã phiếu nhập");
        lblMaPhieu.setBounds(510, 20, 100, 25);
        add(lblMaPhieu);

        txtMaPhieu = new JTextField();
        txtMaPhieu.setBounds(620, 20, 350, 25);
        add(txtMaPhieu);

        JLabel lblNCC = new JLabel("Nhà cung cấp");
        lblNCC.setBounds(510, 60, 100, 25);
        add(lblNCC);

        cbNCC = new JComboBox<>(new String[]{"-- Chọn --"});
        cbNCC.setBounds(620, 60, 350, 25);
        add(cbNCC);

        JLabel lblNguoiTao = new JLabel("Người tạo phiếu");
        lblNguoiTao.setBounds(510, 100, 100, 25);
        add(lblNguoiTao);

        txtNguoiTao = new JTextField();
        txtNguoiTao.setBounds(620, 100, 350, 25);
        add(txtNguoiTao);

        // Bảng phải (sản phẩm được thêm)
        String[] rightCols = {"STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá"};
        tblNhapHang = new JTable(new DefaultTableModel(rightCols, 0));
        JScrollPane scrollRight = new JScrollPane(tblNhapHang);
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

        lblTotalValue = new JLabel("0đ");
        lblTotalValue.setBounds(650, 600, 200, 30);
        lblTotalValue.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotalValue.setForeground(Color.RED);
        add(lblTotalValue);

        // Nút tạo phiếu
        JButton btnCreate = new JButton("Tạo phiếu");
        btnCreate.setBounds(850, 600, 150, 35);
        add(btnCreate);

        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });

        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });

        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSQuantityActionPerformed(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        allProduct = MayTinhDAO.getInstance().selectAllExist();
        initTable();
        loadDataToTableProduct(allProduct);
        loadNccToComboBox();
        tblSanPham.setDefaultEditor(Object.class, null);
        tblNhapHang.setDefaultEditor(Object.class, null);
        MaPhieu = createId(PhieuNhapDAO.getInstance().selectAll());
        txtMaPhieu.setText(MaPhieu);
        CTPhieu = new ArrayList<ChiTietPhieu>();
    }

    private void loadNccToComboBox() {
        for (NhaCungCap i : arrNcc) {
            cbNCC.addItem(i.getTenNhaCungCap());
        }
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"Mã máy", "Tên máy", "Số lượng", "Đơn giá"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblNhapHang.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblNhapHang.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblNhapHang.getColumnModel().getColumn(2).setPreferredWidth(250);
    }

    private void loadDataToTableProduct(ArrayList<MayTinh> arrProd) {
        try {
            tblModel.setRowCount(0);
            for (MayTinh i : arrProd) {
                tblModel.addRow(new Object[]{
                        i.getMaMay(), i.getTenMay(), i.getSoLuong(), formatter.format(i.getGia()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }
    public double tinhTongTien() {
        double tt = 0;
        for (ChiTietPhieu i : CTPhieu) {
            tt += i.getDonGia() * i.getSoLuong();
        }
        return tt;
    }

    public MayTinh findMayTinh(String maMay) {
        for (MayTinh i : allProduct) {
            if (maMay.equals(i.getMaMay())) {
                return i;
            }
        }
        return null;
    }

    public ChiTietPhieu findCTPhieu(String maMay) {
        for (ChiTietPhieu i : CTPhieu) {
            if (maMay.equals(i.getMaMay())) {
                return i;
            }
        }
        return null;
    }

    public void loadDataToTableNhapHang() {
        double sum = 0;
        try {
            DefaultTableModel tblNhapHangmd = (DefaultTableModel) tblNhapHang.getModel();
            tblNhapHangmd.setRowCount(0);

            for (int i = 0; i < CTPhieu.size(); i++) {
                tblNhapHangmd.addRow(new Object[]{
                        i + 1, CTPhieu.get(i).getMaMay(), findMayTinh(CTPhieu.get(i).getMaMay()).getTenMay(), CTPhieu.get(i).getSoLuong(), formatter.format(CTPhieu.get(i).getDonGia()) + "đ"
                });
                sum += CTPhieu.get(i).getDonGia() * CTPhieu.get(i).getSoLuong();
            }
        } catch (Exception e) {
        }
        lblTotalValue.setText(formatter.format(sum) + "đ");
    }

    public String createId(ArrayList<PhieuNhap> arr) {
        int id = arr.size() + 1;
        String check = "";
        for (PhieuNhap phieuNhap : arr) {
            if (phieuNhap.getMaPhieu().equals("PN" + id)) {
                check = phieuNhap.getMaPhieu();
            }
        }
        while (check.length() != 0) {
            String c = check;
            id++;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getMaPhieu().equals("PN" + id)) {
                    check = arr.get(i).getMaPhieu();
                }
            }
            if (check.equals(c)) {
                check = "";
            }
        }
        return "PN" + id;
    }

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {
        if (CTPhieu.size() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để nhập hàng !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn nhập hàng ?", "Xác nhận nhập hàng", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                // Lay thoi gian hien tai
                long now = System.currentTimeMillis();
                Timestamp sqlTimestamp = new Timestamp(now);
                // Tao doi tuong phieu nhap
                PhieuNhap pn = new PhieuNhap(arrNcc.get(cbNCC.getSelectedIndex()).getMaNhaCungCap(), MaPhieu, sqlTimestamp, txtNguoiTao.getText(), CTPhieu, tinhTongTien());
                try {
                    PhieuNhapDAO.getInstance().insert(pn);
                    MayTinhDAO mtdao = MayTinhDAO.getInstance();
                    for (ChiTietPhieu i : CTPhieu) {
                        ChiTietPhieuNhapDAO.getInstance().insert(i);
                        mtdao.updateSoLuong(i.getMaMay(), mtdao.selectById(i.getMaMay()).getSoLuong() + i.getSoLuong());
                    }
                    JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
                    int res = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất file pdf ?","",JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        WritePDF writepdf = new WritePDF();
                        writepdf.writePhieuNhap(MaPhieu);
                    }
                    ArrayList<MayTinh> productUp = MayTinhDAO.getInstance().selectAllExist();
                    txtSoLuong.setText("1");
                    loadDataToTableProduct(productUp);
                    DefaultTableModel r = (DefaultTableModel) tblNhapHang.getModel();
                    r.setRowCount(0);
                    CTPhieu = new ArrayList<>();
                    lblTotalValue.setText("0");
                    this.MaPhieu = createId(PhieuNhapDAO.getInstance().selectAll());
                    txtMaPhieu.setText(this.MaPhieu);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi !");
                }
            }
        }
    }

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {
        int i_row = tblSanPham.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để nhập hàng !");
        } else {
            int soluong;
            try {
                soluong = Integer.parseInt(txtSoLuong.getText().trim());
                if (soluong > 0) {
                    System.out.println("sinh");
                    ChiTietPhieu mtl = findCTPhieu((String) tblSanPham.getValueAt(i_row, 0));
                    if (mtl != null) {
                        mtl.setSoLuong(mtl.getSoLuong() + soluong);
                    } else {
                        MayTinh mt = SearchProduct.getInstance().searchId((String) tblSanPham.getValueAt(i_row, 0));
                        ChiTietPhieu ctp = new ChiTietPhieu(MaPhieu, mt.getMaMay(), soluong, mt.getGia());
                        CTPhieu.add(ctp);
                    }
                    loadDataToTableNhapHang();
                    lblTotalValue.setText(formatter.format(tinhTongTien()) + "đ");
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
            }
        }
    }

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {
        DefaultTableModel tblsp = (DefaultTableModel) tblSanPham.getModel();
        String textSearch = txtSearch.getText().toLowerCase();
        ArrayList<MayTinh> Mtkq = new ArrayList<>();
        for (MayTinh i : allProduct) {
            if (i.getMaMay().concat(i.getTenMay()).toLowerCase().contains(textSearch)) {
                Mtkq.add(i);
            }
        }
        loadDataToTableProduct(Mtkq);
    }

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá khỏi bảng nhập hàng !");
        } else {
            CTPhieu.remove(i_row);
            loadDataToTableNhapHang();
            lblTotalValue.setText(formatter.format(tinhTongTien()) + "đ");
        }
    }

    private void editSQuantityActionPerformed(java.awt.event.ActionEvent evt) {
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá sửa số lượng !");
        } else {
            String newSL = JOptionPane.showInputDialog(this, "Nhập số lượng cần thay đổi", "Thay đổi số lượng", QUESTION_MESSAGE);
            if (newSL != null) {
                int soLuong;
                try {
                    soLuong = Integer.parseInt(newSL);
                    if (soLuong > 0) {
                        CTPhieu.get(i_row).setSoLuong(soLuong);
                        loadDataToTableNhapHang();
                        lblTotalValue.setText(formatter.format(tinhTongTien()) + "đ");
                    } else {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
                }
            }
        }
    }

}
