import javax.swing.*;

public class PreLoader {
    private static JTable table;

    public PreLoader(JTable table) {
        PreLoader.table = table;
    }

    static SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            Jims.getTableModel();
            System.out.println("Retrieving table model");
            return null;
        }

        @Override
        protected void done() {
            Jims.getTable();
            System.out.println("Table: Items has been loaded.");
            System.out.println("PreLoader finished");
        }
    };
}
