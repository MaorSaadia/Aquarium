/**
 * RestorePlanetObject class that restore the specific object that his details saved in memento object
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class RestorePlanetObject extends JPanel implements ActionListener {

    private int size, x_front, y_front,ID;
    private Color clr=Color.GREEN;
    private JTable table;
    private JScrollPane jp = null;
    private JDialog RestoreDialog;
    private JButton RestoreState;
    private BorderLayout myBorderLayout = new BorderLayout();
    private int row = -1; //get the row of the animal in the Jtable
    private Iterator<Memento> itrresPlanet;
    private Iterator<Immobile> itrPlanet;

    public RestorePlanetObject(JDialog RestoreDialog) {

        String[] header = {"ID","Planet Name", "Color", "Size", "X Front", "Y Front"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
        itrresPlanet = CareTaker.getRestorePlanet().iterator();
        while (itrresPlanet.hasNext()) {
            Memento me = itrresPlanet.next();
            String[] PlanetInfo = new String[]{String.valueOf(me.getid()),me.getPlanetName(),me.getColor(),
                    String.valueOf(me.getSize()), String.valueOf(me.getXfront()),
                    String.valueOf(me.getYfront())};
            tableModel.addRow(PlanetInfo);
        }

        table.setPreferredSize(new Dimension(500, 116));
        RestoreState = new JButton("Restore State");
        RestoreState.addActionListener(this);
        RestoreState.setBackground(Color.DARK_GRAY);
        RestoreState.setForeground(Color.WHITE);
        this.setLayout(myBorderLayout);
        add(RestoreState, BorderLayout.SOUTH);
        this.RestoreDialog = RestoreDialog;

        table.getSelectionModel().addListSelectionListener(e -> row = table.getSelectedRow());

        jp = new JScrollPane(table);
        add(jp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please Select a Planet You Want To Restore",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        ID = Integer.valueOf((String) table.getValueAt(row, 0));
        size = Integer.valueOf((String) table.getValueAt(row, 3));
        x_front = Integer.valueOf((String) table.getValueAt(row, 4));
        y_front = Integer.valueOf((String) table.getValueAt(row, 5));

        if (e.getSource().equals(RestoreState)) {

            itrPlanet = AquaPanel.Planet.iterator();
            if (e.getSource().equals(RestoreState)) {
                for (int i = 0; i < ID; i++) {
                    if (itrPlanet.next().getID() == ID) {
                        break;
                    }
                }
                itrPlanet.next().setState(size, x_front, y_front);
                AquaFrame.getPanel().repaint();
                RestoreDialog.dispose();
            }
        }
    }
}
