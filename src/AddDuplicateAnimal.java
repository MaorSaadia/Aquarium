/**
 * dialog to add a duplicate animal
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class AddDuplicateAnimal extends JPanel implements ActionListener {

    private EditDuplicateAnimal Editdialog;
    private JTable table;
    private JScrollPane jp = null;
    private JDialog DuplicateDialog;
    private JButton DuplicateAnimal;
    private BorderLayout myBorderLayout = new BorderLayout();
    private int row = -1; //get the row of the animal in the Jtable
    private Iterator<Swimmable> itrAnimals;
    private Swimmable duplicateSwimmable;
    private String Swimmable_type;

    public AddDuplicateAnimal(JDialog DuplicateDialog) {

        //Create the table
        String[] header = {"Animal", "Color", "Size", "Hor.Speed", "Ver.speed"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
        itrAnimals = AquaPanel.Animals.iterator();
        while (itrAnimals.hasNext()) {
            Swimmable sw = itrAnimals.next();
            String[] AnimalInfo = new String[]{sw.getAnimalName(), sw.getColor(),
                    String.valueOf(sw.getSize()), String.valueOf(Math.abs(sw.getHorSpeed())), String.valueOf(Math.abs(sw.getVerSpeed()))};
            tableModel.addRow(AnimalInfo);
        }

        //Create Dialog
        table.setPreferredSize(new Dimension(400, 116));
        DuplicateAnimal = new JButton("Duplicate Animal");
        DuplicateAnimal.addActionListener(this);
        DuplicateAnimal.setBackground(Color.DARK_GRAY);
        DuplicateAnimal.setForeground(Color.WHITE);
        this.setLayout(myBorderLayout);
        add(DuplicateAnimal, BorderLayout.SOUTH);
        this.DuplicateDialog = DuplicateDialog;

        table.getSelectionModel().addListSelectionListener(e -> row = table.getSelectedRow());

        jp = new JScrollPane(table);
        add(jp);

    }

    //implements all the action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(DuplicateAnimal)) {
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please Select a Animal You Want To Duplicate",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Swimmable_type = (String) (table.getValueAt(row, 0));
            duplicateAnimal();//Create The Clone Of the Animal

            //Ask the user if he wants to edit the Clone animal
            if (JOptionPane.showConfirmDialog(null, "Do You Want Edit To The Animal?", "Edit Animal",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                //open the edit dialog
                Editdialog = new EditDuplicateAnimal("Edit Animal Dialog", duplicateSwimmable);
                Editdialog.setVisible(true);
                DuplicateDialog.dispose();
                for (Swimmable i : AquaPanel.getAnimals()) {
                    (new Thread(i)).start();
                }

                //Duplicate without editing
            } else {
                DuplicateDialog.dispose();
                AquaPanel.Animals.add(duplicateSwimmable);
                for (Swimmable i : AquaPanel.getAnimals()) {
                    (new Thread(i)).start();
                }
            }
        }
    }

    private void duplicateAnimal() {

        //iterate to get the right fish or jellyfish to clone
        itrAnimals = AquaPanel.Animals.iterator();
        if (Swimmable_type == "Fish") {
            for (int i = 0; i < row; i++)
                itrAnimals.next();
            duplicateSwimmable = (Fish) itrAnimals.next().clone();
        }

        else if (Swimmable_type == "Jellyfish") {
            for (int i = 0; i < row; i++)
                itrAnimals.next();
            duplicateSwimmable = (Jellyfish) itrAnimals.next().clone();
        }
    }
}



