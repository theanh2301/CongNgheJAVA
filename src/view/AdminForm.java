package view;

import javax.swing.*;
import java.awt.*;

public class AdminForm extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public AdminForm() {
        setTitle("Quản trị hệ thống");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1240, 700);
        setLocationRelativeTo(null);

        // Main container with BorderLayout
        setLayout(new BorderLayout());

        // Sidebar with vertical BoxLayout
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(76, 175, 80));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 0));

        JLabel hiLabel = new JLabel("HI !", SwingConstants.CENTER);
        hiLabel.setFont(new Font("Arial", Font.BOLD, 14));
        hiLabel.setForeground(Color.WHITE);
        hiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(hiLabel);

        JLabel adminLabel = new JLabel("ADMIN", SwingConstants.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(adminLabel);

        sidebar.add(Box.createVerticalStrut(20));

        // Menu buttons
        String[] menuItems = {
                "📦 SẢN PHẨM", "👤 NHÀ CUNG CẤP", "📥 NHẬP HÀNG",
                "📄 PHIẾU NHẬP", "📤 XUẤT HÀNG", "📦 PHIẾU XUẤT",
                "🏠 TỒN KHO", "👥 TÀI KHOẢN", "📊 THỐNG KÊ"
        };

        JPanel[] pages = {
                new ProductForm(), new NhaCungCapForm(), new NhapHangForm(), new PhieuNhapForm(),
                new XuatHangForm(), new PhieuXuatForm(), new TonKhoForm(), new AccountForm(), new ThongKeForm()
        };

        // Add buttons to sidebar
        for (int i = 0; i < menuItems.length; i++) {
            JButton btn = new JButton(menuItems[i]);
            btn.setMaximumSize(new Dimension(180, 35));
            btn.setMinimumSize(new Dimension(180, 35));
            btn.setPreferredSize(new Dimension(180, 35));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            int index = i;
            btn.addActionListener(e -> cardLayout.show(contentPanel, menuItems[index]));
            sidebar.add(Box.createVerticalStrut(10));
            sidebar.add(btn);
        }

        sidebar.add(Box.createVerticalGlue());

        JButton btnInfo = new JButton("ĐỔI THÔNG TIN");
        btnInfo.setMaximumSize(new Dimension(180, 35));
        btnInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(btnInfo);

        sidebar.add(Box.createVerticalStrut(10));

        JButton btnLogout = new JButton("ĐĂNG XUẤT");
        btnLogout.setMaximumSize(new Dimension(180, 35));
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(btnLogout);

        sidebar.add(Box.createVerticalStrut(20));

        add(sidebar, BorderLayout.WEST);

        // Content area with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        for (int i = 0; i < menuItems.length; i++) {
            contentPanel.add(pages[i], menuItems[i]);
        }
        add(contentPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminForm frame = new AdminForm();
            frame.setVisible(true);
        });
    }
}