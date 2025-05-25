package view;
import dao.LaptopDAO;
import dao.MayTinhDAO;
import dao.PCDAO;
import model.Laptop;
import model.MayTinh;
import model.PC;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;


public class AddProductForm extends JPanel {
    private JTextField tfMaSP, tfTenSP, tfDonGia, tfXuatXu, tfMainBoard, tfCongSuatNguon;
    private JTextField tfCPU, tfRAM, tfROM, tfGPU, tfScreenSize, tfBattery;
    private JComboBox<String> cbLoaiSP;
    private JButton btnSave, btnCancel;
    private JLabel lblMainBoard, lblCongSuatNguon, lblDungLuongPin;

    private ProductForm owner;

    public AddProductForm() {
        setLayout(null); // dùng tọa độ tuyệt đối
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(710, 400));

        // Tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87)); // xanh lá đậm
        titlePanel.setBounds(0, 0, 720, 50);
        titlePanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("THÊM SẢN PHẨM MỚI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        add(titlePanel);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 13);
        int labelW = 120, fieldW = 180, h = 25;

        addLabelAndField("Mã sản phẩm:", 30, 65, labelW, h, labelFont);
        tfMaSP = createTextField(30, 90, fieldW, h);

        addLabelAndField("Tên sản phẩm:", 30, 120, labelW, h, labelFont);
        tfTenSP = createTextField(30, 145, fieldW, h);

        addLabelAndField("Đơn giá:", 30, 175, labelW, h, labelFont);
        tfDonGia = createTextField(30, 200, fieldW, h);

        addLabelAndField("Xuất xứ:", 30, 230, labelW, h, labelFont);
        tfXuatXu = createTextField(30, 255, fieldW, h);

        addLabelAndField("CPU:", 270, 65, labelW, h, labelFont);
        tfCPU = createTextField(270, 90, fieldW, h);

        addLabelAndField("RAM:", 270, 120, labelW, h, labelFont);
        tfRAM = createTextField(270, 145, fieldW, h);

        addLabelAndField("Dung lượng lưu trữ:", 270, 175, labelW, h, labelFont);
        tfROM = createTextField(270, 200, fieldW, h);

        addLabelAndField("Card đồ họa:", 270, 230, labelW, h, labelFont);
        tfGPU = createTextField(270, 255, fieldW, h);

        addLabelAndField("Loại sản phẩm:", 510, 65, labelW, h, labelFont);
        cbLoaiSP = new JComboBox<>();
        cbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laptop", "PC" }));
        cbLoaiSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxloaispItemStateChanged(evt);
            }
        });
        cbLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxloaispActionPerformed(evt);
            }
        });
        cbLoaiSP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxloaispPropertyChange(evt);
            }
        });

        cbLoaiSP.setBounds(510, 90, fieldW, h);
        add(cbLoaiSP);

        addLabelAndField("Kích thước màn:", 510, 120, labelW, h, labelFont);
        tfScreenSize = createTextField(510, 145, fieldW, h);


        lblDungLuongPin = new JLabel("Dung lượng PIN:");
        lblDungLuongPin.setBounds(510, 175, labelW, h);
        lblDungLuongPin.setFont(labelFont);
        add(lblDungLuongPin);
        tfBattery = createTextField(510, 200, fieldW, h);

        lblCongSuatNguon = new JLabel("Cong Suat Nguon:");
        lblCongSuatNguon.setBounds(510, 175, labelW, h);
        lblCongSuatNguon.setFont(labelFont);
        lblCongSuatNguon.setVisible(false);
        add(lblCongSuatNguon);

        lblMainBoard = new JLabel("Main Board:");
        lblMainBoard.setBounds(510, 230, labelW, h);
        lblMainBoard.setFont(labelFont);
        add(lblMainBoard);
        tfMainBoard = createTextField(510, 255, fieldW, h);
        lblMainBoard.setVisible(false);
        tfMainBoard.setVisible(false);

        // Buttons
        btnSave = new JButton("Lưu");
        btnSave.setBounds(220, 320, 120, 35);
        btnSave.setBackground(Color.WHITE);
        btnSave.setBorder(new LineBorder(new Color(46, 139, 87), 1));
        btnSave.setForeground(new Color(46, 139, 87));

        btnCancel = new JButton("Hủy");
        btnCancel.setBounds(370, 320, 120, 35);
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setBorder(new LineBorder(Color.RED, 1));
        btnCancel.setForeground(Color.RED);

        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        add(btnSave);
        add(btnCancel);
    }

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {
        String maMay = tfMaSP.getText();
        String tenMay = tfTenSP.getText();
        double dongia = 0;
        double kichthuocman = 0;
        try {
            dongia = Double.parseDouble(tfDonGia.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá ở dạng số !");
        }
        String cpu = tfCPU.getText();
        String ram = tfRAM.getText();
        String rom = tfROM.getText();
        String gpu = tfGPU.getText();
        String xuatxu = tfXuatXu.getText();
        int trangThai = 1;
        if (cbLoaiSP.getSelectedItem().equals("Laptop")) {
            try {
                kichthuocman = Double.parseDouble(tfScreenSize.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập kích thước màn ở dạng số !");
            }
            String dungluongpin = tfBattery.getText();
            if (maMay.equals("") && tenMay.equals("") && cpu.equals("") && ram.equals("") && rom.equals("") && gpu.equals("") && xuatxu.equals("") && dungluongpin.equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
            } else {
                Laptop lp = new Laptop(kichthuocman, dungluongpin, maMay, tenMay, 0, dongia, cpu, ram, xuatxu, gpu, rom,trangThai);
                try {
                    LaptopDAO.getInstance().insert(lp);
                    Window window = SwingUtilities.getWindowAncestor(this);
                    if (window != null) {
                        window.dispose();
                    }
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
                    owner.loadDataToTable();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại !");
                }
            }
        }
        if (cbLoaiSP.getSelectedItem().equals("PC")) {
            String mainboard = tfMainBoard.getText();
            int congsuatnguon = 0;
            try {
                congsuatnguon = Integer.parseInt(tfDonGia.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số !");
            }
            if (maMay.equals("") && tenMay.equals("") && cpu.equals("") && ram.equals("") && rom.equals("") && gpu.equals("") && xuatxu.equals("") && mainboard.equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
            } else {
                PC pc = new PC(mainboard, congsuatnguon, maMay, tenMay, 0, dongia, cpu, ram, xuatxu, gpu, rom,trangThai);
                PCDAO.getInstance().insert(pc);
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) {
                    window.dispose();
                }
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
                owner.loadDataToTable();
            }
        }
    }

    private void addLabelAndField(String text, int x, int y, int w, int h, Font font) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font);
        add(label);
    }

    private JTextField createTextField(int x, int y, int w, int h) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, h);
        add(tf);
        return tf;
    }

    private void cbxloaispActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void cbxloaispPropertyChange(java.beans.PropertyChangeEvent evt) {

    }

    private void cbxloaispItemStateChanged(java.awt.event.ItemEvent evt) {
        String selected = cbLoaiSP.getSelectedItem().toString();

        if (selected.equals("Laptop")) {
            tfMaSP.setText(createIdLT());

            lblDungLuongPin.setVisible(true);
            lblCongSuatNguon.setVisible(false);
            lblMainBoard.setVisible(false);
            tfMainBoard.setVisible(false);
        }

        if (selected.equals("PC")) {
            tfMaSP.setText(createIdPC());

            lblDungLuongPin.setVisible(false);
            lblCongSuatNguon.setVisible(true);
            lblMainBoard.setVisible(true);
            tfMainBoard.setVisible(true);
        }

        // Nếu cần cập nhật giao diện:
        this.revalidate();
        this.repaint();
    }



    public String createIdPC() {
        ArrayList<MayTinh> mtAll = MayTinhDAO.getInstance().selectAll();
        ArrayList<MayTinh> pcAll = new ArrayList<MayTinh>();
        for (MayTinh mayTinh : mtAll) {
            if (mayTinh.getMaMay().contains("PC")) {
                pcAll.add(mayTinh);
            }
        }
        int i = pcAll.size();
        String check ="check";
        while(check.length()!=0){
            i++;
            for (MayTinh mayTinh : pcAll) {
                if(mayTinh.getMaMay().equals("PC"+i)){
                    check="";
                }
            }
            if(check.length()==0){
                check ="check";
            } else {
                check = "";
            }
        }
        return "PC" + i;
    }

    public String createIdLT() {
        ArrayList<MayTinh> mtAll = MayTinhDAO.getInstance().selectAll();
        ArrayList<MayTinh> lpAll = new ArrayList<MayTinh>();
        for (MayTinh mayTinh : mtAll) {
            if (mayTinh.getMaMay().contains("LP")) {
                lpAll.add(mayTinh);
            }
        }
        int i = lpAll.size();
        String check ="check";
        while(check.length()!=0){
            i++;
            for (MayTinh mayTinh : lpAll) {
                if(mayTinh.getMaMay().equals("LP"+i)){
                    check="";
                }
            }
            if(check.length()==0){
                check ="check";
            } else {
                check = "";
            }
        }
        return "LP" + i;
    }
}
