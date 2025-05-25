package view;

import format.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class ThongKeForm extends JPanel {

    public ThongKeForm() {
        setLayout(null);  // dùng tọa độ tuyệt đối

        // Header Panel - 3 sections
        JPanel headerPanel1 = createHeaderPanel("100", "Sản phẩm trong kho", Color.ORANGE, new ImageIcon("icons/icons8-monitor-80.png"));
        headerPanel1.setBounds(10, 10, 300, 90);
        add(headerPanel1);

        JPanel headerPanel2 = createHeaderPanel("100", "Nhà cung cấp", Color.RED, new ImageIcon("icons/icons8-supplier-80.png"));
        headerPanel2.setBounds(335, 10, 300, 90);
        add(headerPanel2);

        JPanel headerPanel3 = createHeaderPanel("100", "Tài khoản người dùng", Color.CYAN, new ImageIcon("icons/icons8-account-80.png"));
        headerPanel3.setBounds(660, 10, 300, 90);
        add(headerPanel3);

        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(10, 120, 950, 540);
        JPanel productTab = new JPanel(null); // sử dụng tọa độ tuyệt đối trong tab
        tabbedPane.add("Sản phẩm", productTab);
        tabbedPane.add("Phiếu", new JPanel());
        tabbedPane.add("Tài khoản", new JPanel());
        add(tabbedPane);

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        searchPanel.setBounds(10, 10, 460, 80);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(40, 40, 300, 30);
        productTab.add(txtSearch);

        JButton btnRefresh = new JButton("Làm mới");
        btnRefresh.setBounds(350, 40, 100, 30);
        productTab.add(btnRefresh);

        productTab.add(searchPanel);

        // Panel loc theo ngay
        JPanel datePanel = new JPanel();
        datePanel.setLayout(null);
        datePanel.setBounds(490, 10, 440, 80);
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
        productTab.add(datePanel);

        // Table
        JTable table = new JTable(new String[][] {}, new String[] {
                "STT", "Mã máy", "Tên máy", "Số lượng nhập", "Số lượng xuất"
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 920, 400);
        productTab.add(scrollPane);

        setVisible(true);
    }

    private JPanel createHeaderPanel(String number, String label, Color color, Icon icon) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(color);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(10, 10, 64, 64);
        panel.add(iconLabel);

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 28));
        numberLabel.setForeground(Color.WHITE);
        numberLabel.setBounds(90, 10, 100, 30);
        panel.add(numberLabel);

        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        textLabel.setForeground(Color.WHITE);
        textLabel.setBounds(90, 40, 180, 30);
        panel.add(textLabel);

        return panel;
    }

}
