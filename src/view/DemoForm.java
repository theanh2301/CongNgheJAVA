package view;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoForm extends JFrame {
    private JPanel menuPanel, contentPanel;

    public DemoForm(Account acc) {
        setTitle("Admin Dashboard");
        setSize(1150, 610);
        setLocationRelativeTo(null);
        setResizable(true); // Cho phép thay đổi kích thước
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sử dụng BorderLayout chính để chia trái - phải
        setLayout(new BorderLayout());

        // Menu trái
        menuPanel = new JPanel();
        menuPanel.setBackground(new Color(46, 139, 87));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(200, 0)); // chỉ cố định chiều ngang
        add(menuPanel, BorderLayout.WEST);

        // Nội dung phải
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout()); // để co giãn nội dung
        add(contentPanel, BorderLayout.CENTER);

        initMenu();
    }

    private void initMenu() {
        menuPanel.add(Box.createVerticalStrut(20));

        JLabel helloLabel = createLabel("HI !", 14);
        menuPanel.add(helloLabel);

        JLabel roleLabel = createLabel("ADMIN", 14);
        menuPanel.add(roleLabel);

        menuPanel.add(Box.createVerticalStrut(10)); // khoảng cách sau label

        String[] menuItems = {
                "📦 SẢN PHẨM", "👤 NHÀ CUNG CẤP", "📥 NHẬP HÀNG",
                "📄 PHIẾU NHẬP", "📤 XUẤT HÀNG", "📦 PHIẾU XUẤT",
                "🏠 TỒN KHO", "👥 TÀI KHOẢN", "📊 THỐNG KÊ"
        };

        for (String item : menuItems) {
            JButton btn = createMenuButton(item);
            menuPanel.add(btn);
            menuPanel.add(Box.createVerticalStrut(10));
        }

        // Thay vì đẩy hết xuống đáy, chỉ đẩy khoảng 50px
        menuPanel.add(Box.createVerticalStrut(40));

        JButton btnInfo = createMenuButton("ℹ️ ĐỔI THÔNG TIN");
        btnInfo.setForeground(Color.CYAN);
        menuPanel.add(btnInfo);

        menuPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa hai nút cuối

        JButton btnLogout = createMenuButton("⏻ ĐĂNG XUẤT");
        btnLogout.setForeground(Color.RED);
        menuPanel.add(btnLogout);
    }


    private JLabel createLabel(String text, int fontSize) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(180, 30));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(46, 139, 87));
        btn.setForeground(Color.WHITE);
        btn.setBorder(null);
        btn.setHorizontalAlignment(SwingConstants.LEFT);

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadContentPanel(text);
            }
        });

        return btn;
    }

    private void loadContentPanel(String pageName) {
        contentPanel.removeAll();
        JLabel label = new JLabel("Bạn đang ở trang: " + pageName);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
        contentPanel.add(label, BorderLayout.NORTH);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DemoForm(new Account()).setVisible(true);
        });
    }
}
