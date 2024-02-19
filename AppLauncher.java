import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // display our weather app gui
                new JimsGUI().setVisible(true);

                // System.out.println(weatherApp.getLocationData("Oslo"));

            }
        });
    }
}
