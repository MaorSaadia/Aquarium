/**
 * SavePlanetObject class is dialog that save current state of Planet object in Linekedhashmap Caretaker class
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class SavePlanetObject extends JPanel implements ActionListener {

    private JTable table;
    private JScrollPane jp = null;
    private JDialog SaveDialog;
    private JButton SaveState;
    private BorderLayout myBorderLayout = new BorderLayout();
    private int row = -1; //get the row of the animal in the Jtable
    private Immobile SaveImmobile;
    private Iterator<Immobile> itrPlanet;
    private String Immobile_type;
    private Memento memento = null;

    public SavePlanetObject(JDialog SaveDialog) {

        String[] header = {"ID","Planet Name", "Color", "Size", "X Front", "Y Front"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
        itrPlanet = AquaPanel.Planet.iterator();
        while (itrPlanet.hasNext()) {
            Immobile im = itrPlanet.next();
            String[] PlanetInfo = new String[]{String.valueOf(im.getID()),im.getPlanetName(),String.valueOf(im.getColor()),
                    String.valueOf(im.getSize()), String.valueOf(im.getX_front()),
                    String.valueOf(im.getY_front())};
            tableModel.addRow(PlanetInfo);
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
                JOptionPane.showMessageDialog(null, "Please Select a Planet You Want To Save",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Immobile_type = (String) (table.getValueAt(row, 1));

            itrPlanet = AquaPanel.Planet.iterator();
            if (Immobile_type == "Zostera") {
                for (int i = 0; i < row; i++)
                    itrPlanet.next();
                SaveImmobile = (Zostera) itrPlanet.next();
            }
            if (Immobile_type == "Laminaria") {
                for (int i = 0; i < row; i++)
                    itrPlanet.next();
                SaveImmobile = (Laminaria) itrPlanet.next();
            }
            memento = new Memento(SaveImmobile);
            AquaFrame.getCarTakerMemento().addImmobileMemento(memento);
            SaveDialog.dispose();
        }
    }
}
