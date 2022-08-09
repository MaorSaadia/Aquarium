
/**
 * AddPlanetDialog Create dialog that user fill the details with the new planet
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class AddPlanetDialog extends JDialog implements ActionListener {

    private JButton Laminaria,Zostera;
    private final JLabel label = new JLabel("Choice a Planet:",SwingConstants.CENTER);
    private final JLabel lsize = new JLabel("Choice Planet Size(100-400):",SwingConstants.CENTER);
    private JTextField tsize;

    private AbstractSeaFactory seaFactory;
    private SeaCreature SeaC;

    //Constructor
    public AddPlanetDialog (String string) {
        this.setTitle(string);

        //Set the size TextField
        add(lsize);
        tsize = new JTextField();
        tsize.setHorizontalAlignment(JTextField.CENTER);
        add(tsize);

        //add Laminaria button
        Laminaria = new JButton("Laminaria");
        Laminaria.addActionListener(this);

        //add Zostera button
        Zostera = new JButton("Zostera");
        Zostera.addActionListener(this);

        add(label);
        Laminaria.setBackground(Color.GREEN);
        add(Laminaria);
        //add(lempty);
        Zostera.setBackground(Color.GREEN);
        add(Zostera);

        setLayout(new GridLayout(0,1,2,2));
        setSize(280,250);
        setLocationRelativeTo(null);

    }

    @Override
    //implements all the action
    public void actionPerformed(ActionEvent e) {

        //Check for not null value
        if(tsize.getText()==null || tsize.getText().length()==0){
            JOptionPane.showMessageDialog(null, "You Must Enter Planet Size",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Check for only Number
        try {
            Integer.parseInt(tsize.getText());
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Please Enter Number Only",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Check for legal values on size planet between 100-400
        int size=Integer.parseInt(tsize.getText());

        if(size>400||size<100){
            JOptionPane.showMessageDialog(null, "The Planet Size Must to be\nBetween 100 to 400",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int randomX_front = ThreadLocalRandom.current().nextInt(50, 800);
        int randomY_front = ThreadLocalRandom.current().nextInt(500, 550);

        //Add new Laminaria
        if(e.getSource().equals(Laminaria)){

            seaFactory=new PlantFactory(size,randomX_front,randomY_front);
            SeaC=seaFactory.produceSeaCreature("Laminaria");
            AquaPanel.Planet.add((Immobile)SeaC);
            setVisible(false);
            AquaFrame.getPanel().repaint();
        }

        //Add new Zostera
        if(e.getSource().equals(Zostera)){

            seaFactory=new PlantFactory(size,randomX_front,randomY_front);
            SeaC=seaFactory.produceSeaCreature("Zostera");
            AquaPanel.Planet.add((Immobile)SeaC);
            setVisible(false);
            AquaFrame.getPanel().repaint();
        }

    }
}
