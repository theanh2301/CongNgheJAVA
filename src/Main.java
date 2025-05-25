

import view.AdminForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SwingUtilities.invokeLater(() -> new AdminForm().setVisible(true));

        //ConnectDatabase.getConnection();
    }
}