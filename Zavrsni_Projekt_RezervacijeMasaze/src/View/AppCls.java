package View;

import javax.swing.*;

/**
 * Client class.
 */
public class AppCls {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
