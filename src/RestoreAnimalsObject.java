/**
 * RestoreAnimalsObject class that restore the specific object that his details saved in memento object
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Scanner;

public class RestoreAnimalsObject extends JPanel implements ActionListener {

    private int size, x_front, y_front, horSpeed, verSpeed, ID,eatfreq;
    private String color = null;
    private Color clr = null;
    private JTable table;
    private JScrollPane jp = null;
    private JDialog RestoreDialog;
    private JButton RestoreState;
    private BorderLayout myBorderLayout = new BorderLayout();
    private int row = -1; //get the row of the animal in the Jtable
    private Iterator<Memento> itrresAnimals;
    private Iterator<Swimmable> itrAnimals;

    public RestoreAnimalsObject(JDialog RestoreDialog) {

        //Create the table
        String[] header = {"ID", "Animal", "Color", "Size", "X Front", "Y Front", "Hor.Speed", "Ver.speed","Eat.freq"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
        itrresAnimals = CareTaker.getRestoreAnimals().iterator();
        while (itrresAnimals.hasNext()) {
            Memento me = itrresAnimals.next();
            String[] AnimalInfo = new String[]{String.valueOf(me.getid()), me.getAnimalName(), String.valueOf(me.getColor()),
                    String.valueOf(me.getSize()), String.valueOf(me.getXfront()),
                    String.valueOf(me.getYfront()), String.valueOf(Math.abs(me.getHorSpeed())),
                    String.valueOf(Math.abs(me.getVerSpeed())),String.valueOf(me.getEatfreq())};
            tableModel.addRow(AnimalInfo);
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
            JOptionPane.showMessageDialog(null, "Please Select a Animal You Want To Restore",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        ID = Integer.valueOf((String) table.getValueAt(row, 0));
        color = (String) table.getValueAt(row, 2);
        size = Integer.valueOf((String) table.getValueAt(row, 3));
        x_front = Integer.valueOf((String) table.getValueAt(row, 4));
        y_front = Integer.valueOf((String) table.getValueAt(row, 5));
        horSpeed = Integer.valueOf((String) table.getValueAt(row, 6));
        verSpeed = Integer.valueOf((String) table.getValueAt(row, 7));
        eatfreq = Integer.valueOf((String) table.getValueAt(row, 8));

        switch (color) {
            case "Black":
                clr = Color.BLACK;
                break;
            case "Red":
                clr = Color.RED;
                break;
            case "Blue":
                clr = Color.BLUE;
                break;
            case "Green":
                clr = Color.GREEN;
                break;
            case "Cyan":
                clr = Color.CYAN;
                break;
            case "Orange":
                clr = Color.ORANGE;
                break;
            case "Yellow":
                clr = Color.YELLOW;
                break;
            case "Magenta":
                clr = Color.MAGENTA;
                break;
            case "Pink":
                clr = Color.PINK;
                break;
            default:
                //Get The RGB color from the table and restore it
                Scanner sc = new Scanner(color);
                sc.useDelimiter("\\D+");
                clr = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
                break;
        }

        itrAnimals = AquaPanel.Animals.iterator();
        if (e.getSource().equals(RestoreState)) {
            for (int i = 0; i < ID; i++) {
                if (itrAnimals.next().getID() == ID) {
                    break;
                }
            }
            itrAnimals.next().setState(size, x_front, y_front, horSpeed, verSpeed, clr,eatfreq);
            AquaFrame.getPanel().repaint();
            RestoreDialog.dispose();
        }
    }
}

