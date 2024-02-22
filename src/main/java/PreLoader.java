import javax.swing.*;

public class PreLoader {
    private static JTable table;

    public PreLoader(JTable table) {
        PreLoader.table = table;
    }

    static SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            Jims.displayDatabase();
            return null;
        }

        @Override
        protected void done() {
            Jims.updateJTable(table);
        }
    };
}
