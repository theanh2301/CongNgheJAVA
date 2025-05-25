package view;

import javax.swing.*;
import java.awt.*;

public class QuanLiKhoForm extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public QuanLiKhoForm() {
        setTitle("Quản trị hệ thống");
        setSize(1240, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Panel bên trái (Menu)
        JPanel sidebar = new JPanel(null);
        sidebar.setBackground(new Color(76, 175, 80));
        sidebar.setBounds(0, 0, 200, 700);
        add(sidebar);

        JLabel hiLabel = new JLabel("HI !");
        hiLabel.setFont(new Font("Arial", Font.BOLD, 14));
        hiLabel.setForeground(Color.WHITE);
        hiLabel.setBounds(70, 10, 100, 30);
        sidebar.add(hiLabel);

        JLabel adminLabel = new JLabel("ADMIN");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setBounds(60, 30, 100, 30);
        sidebar.add(adminLabel);

        // Tạo các nút menu
        String[] menuItems = {
                "📦 SẢN PHẨM", "👤 NHÀ CUNG CẤP", "📥 NHẬP HÀNG",
                "📄 PHIẾU NHẬP", "📤 XUẤT HÀNG", "📦 PHIẾU XUẤT",
                "🏠 TỒN KHO", "📊 THỐNG KÊ"
        };

        JPanel[] pages = {
                new ProductForm(), new NhaCungCapForm(), new NhapHangForm(), new PhieuNhapForm(),
                new XuatHangForm(), new PhieuXuatForm(), new TonKhoForm(), new ThongKeForm()
        };

        int y = 80;
        for (int i = 0; i < menuItems.length; i++) {
            JButton btn = new JButton(menuItems[i]);
            btn.setBounds(10, y, 180, 35);
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            int index = i; // dùng cho listener
            btn.addActionListener(e -> cardLayout.show(contentPanel, menuItems[index]));
            sidebar.add(btn);
            y += 40;
        }

        // Nút đổi thông tin và đăng xuất
        JButton btnInfo = new JButton("ĐỔI THÔNG TIN");
        btnInfo.setBounds(10, 550, 180, 35);
        sidebar.add(btnInfo);

        JButton btnLogout = new JButton("ĐĂNG XUẤT");
        btnLogout.setBounds(10, 600, 180, 35);
        sidebar.add(btnLogout);

        // Panel nội dung chính
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBounds(200, 0, 1100, 700);
        for (int i = 0; i < menuItems.length; i++) {
            contentPanel.add(pages[i], menuItems[i]);
        }
        add(contentPanel);

    }

}
