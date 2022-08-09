/**
 * JPanelDecorator class that the user can change animals color with JColorChooser
 * to choose color to specific swimmable
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class JPanelDecorator extends JPanel implements ActionListener {

    private JTable table;
    private JScrollPane jp = null;
    private JDialog ChangeColorDialog;
    private JButton BChangeColor;
    private BorderLayout myBorderLayout = new BorderLayout();
    private Color clr;
    private int row=-1; //get the row of the animal in the Jtable
    private Iterator <Swimmable>itrAnimals;
    private String Swimmable_type;
    private MarineAnimal NewColorSwimmable;


    public JPanelDecorator(JDialog ChangeColorDialog)
    {

        String[] header = {"Animal","Color", "Size","Hor.Speed","Ver.speed"};
        DefaultTableModel tableModel = new DefaultTableModel(header,0);
        table=new JTable(tableModel);
        itrAnimals= AquaPanel.Animals.iterator();
        while (itrAnimals.hasNext()){
            Swimmable sw = itrAnimals.next();
            String[] AnimalInfo = new String[]{sw.getAnimalName(),sw.getColor(),
                    String.valueOf(sw.getSize()),String.valueOf(Math.abs(sw.getHorSpeed())),String.valueOf(Math.abs(sw.getVerSpeed()))};
            tableModel.addRow(AnimalInfo);
        }

        table.setPreferredSize(new Dimension(400, 116));
        BChangeColor = new JButton("Change Color");
        BChangeColor.addActionListener(this);
        BChangeColor.setBackground(Color.PINK);
        this.setLayout(myBorderLayout);
        add(BChangeColor,BorderLayout.SOUTH);
        this.ChangeColorDialog=ChangeColorDialog;

        table.getSelectionModel().addListSelectionListener(e -> row = table.getSelectedRow());

        jp = new JScrollPane(table);
        add(jp);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(BChangeColor)){

            if(row==-1){
                JOptionPane.showMessageDialog(null, "Please Select a Animal You Want To Change Is Color",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Swimmable_type = (String) (table.getValueAt(row, 2));

            //iterate to get the right fish or jellyfish to Change is color
            itrAnimals = AquaPanel.Animals.iterator();
                for (int i = 0; i < row; i++)
                    itrAnimals.next();
                NewColorSwimmable = (MarineAnimal) itrAnimals.next();

            clr=JColorChooser.showDialog(null,"Pick Color",clr);
            if(clr==null)
                clr=Color.black;

            MarineAnimal newcolor = new MarineAnimalDecorator(NewColorSwimmable);
            newcolor.PaintFish(clr);
            ChangeColorDialog.dispose();

        }
    }
}
