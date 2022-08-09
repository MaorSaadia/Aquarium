/**
 * SaveAnimalsObject class is dialog that save current state of Animals object in Linekedhashmap Caretaker class
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class SaveAnimalsObject extends JPanel implements ActionListener {

    private JTable table;
    private JScrollPane jp = null;
    private JDialog SaveDialog;
    private JButton SaveState;
    private BorderLayout myBorderLayout = new BorderLayout();
    private static int row = -1; //get the row of the animal in the Jtable
    private Iterator<Swimmable> itrAnimals;
    private Swimmable SaveSwimmable;
    private String Swimmable_type;
    private Memento memento = null;

    public SaveAnimalsObject(JDialog SaveDialog) {

        //create the table
        String[] header = {"ID","Animal", "Color", "Size", "X Front", "Y Front", "Hor.Speed", "Ver.speed","Eat.freq"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
        itrAnimals = AquaPanel.Animals.iterator();
        while (itrAnimals.hasNext()) {
            Swimmable sw = itrAnimals.next();
            String[] AnimalInfo = new String[]{String.valueOf(sw.getID()),sw.getAnimalName(), sw.getColor(),
                    String.valueOf(sw.getSize()), String.valueOf(sw.getX_front()),
                    String.valueOf(sw.getY_front()), String.valueOf(Math.abs(sw.getHorSpeed())),
                    String.valueOf(Math.abs(sw.getVerSpeed())),String.valueOf(sw.getEatfreq())};
            tableModel.addRow(AnimalInfo);
        }

        table.setPreferredSize(new Dimension(500, 116));
        SaveState = new JButton("Save State");
        SaveState.addActionListener(this);
        SaveState.setBackground(Color.DARK_GRAY);
        SaveState.setForeground(Color.WHITE);
        this.setLayout(myBorderLayout);
        add(SaveState, BorderLayout.SOUTH);
        this.SaveDialog = SaveDialog;

        table.getSelectionModel().addListSelectionListener(e -> row = table.getSelectedRow());

        jp = new JScrollPane(table);
        add(jp);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(SaveState)) {

            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please Select a Animal You Want To Save",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Swimmable_type = (String) (table.getValueAt(row, 1));

            itrAnimals = AquaPanel.Animals.iterator();
            if (Swimmable_type == "Fish") {
                for (int i = 0; i < row; i++)
                    itrAnimals.next();
                SaveSwimmable = (Fish) itrAnimals.next();
            }
            if (Swimmable_type == "Jellyfish") {
                for (int i = 0; i < row; i++)
                    itrAnimals.next();
                SaveSwimmable = (Jellyfish) itrAnimals.next();
            }
            memento = new Memento(SaveSwimmable);
            AquaFrame.getCarTakerMemento().addSwimmableMemento(memento);
            SaveDialog.dispose();

        }
    }
}
