/**
 * AddAnimalDialog Create dialog that user fill the details with the new Animals
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class AddAnimalDialog extends JDialog implements ActionListener {

    private JButton fish,jellyfish;
    private final JLabel label = new JLabel("Choice a Fish:",SwingConstants.CENTER);
    private final JLabel lsize = new JLabel("Choice a Size (20-320):",SwingConstants.CENTER);
    private final JLabel lhor = new JLabel("Choice a HorSpeed:",SwingConstants.CENTER);
    private final JLabel lver = new JLabel("Choice a VerSpeed:",SwingConstants.CENTER);
    private final JLabel lcolor = new JLabel("Choice a Color:",SwingConstants.CENTER);
    private final JLabel leat = new JLabel("Frequency to Eat:",SwingConstants.CENTER);
    private final JLabel lempty = new JLabel(" ",SwingConstants.CENTER);
    private Integer numbers[]={1,2,3,4,5,6,7,8,9,10};
    private JComboBox<Integer> Cmd_hor;
    private JComboBox<Integer> Cmd_ver;
    private String colors[] = {"Black", "Red", "Blue", "Green", "Cyan",
            "Orange", "Yellow", "Magenta", "Pink"};
    private JComboBox<String> Cmd_color;
    private JSlider seat;
    private JTextField tsize;

    private AbstractSeaFactory seaFactory;
    private SeaCreature SeaC;

    //Constructor
    public AddAnimalDialog (String string){
        this.setTitle(string);

        //Set the size TextField
        add(lsize);
        tsize=new JTextField();
        add(tsize);

        //Set the ComboBox of the Hor.Speed
        Cmd_hor = new JComboBox<>();
        for(int i=0;i<numbers.length;i++)
            Cmd_hor.addItem(numbers[i]);
        add(lhor);
        add(Cmd_hor);

        //Set the ComboBox of the Ver.Speed
        Cmd_ver = new JComboBox<>();
        for(int i=0;i<numbers.length;i++)
            Cmd_ver.addItem(numbers[i]);
        add(lver);
        add(Cmd_ver);

        //Set the ComboBox of the Color
        Cmd_color = new JComboBox<>();
        for(int i=0;i<colors.length;i++)
            Cmd_color.addItem(colors[i]);

        add(lcolor);
        add(Cmd_color);

        //add Frequency to Eat
        seat=new JSlider(2,8); //set the frequency by x number the animal Shifts to the right or left screen.
        seat.setMajorTickSpacing(1);
        //seat.setMinorTickSpacing(1);
        seat.setPaintTicks(true);
        seat.setPaintLabels(true);
        add(leat);
        add(seat);

        //add fish button
        fish = new JButton("Fish");
        fish.addActionListener(this);

        //add Jellyfish button
        jellyfish = new JButton("Jellyfish");
        jellyfish.addActionListener(this);

        add(label);
        fish.setBackground(Color.CYAN);
        add(fish);
        add(lempty);
        jellyfish.setBackground(Color.CYAN);
        add(jellyfish);

        //set the layout of the dialog
        setLayout(new GridLayout(0,2,0,2));
        setSize(300,350);
        setLocationRelativeTo(null);

    }

    //implements all the action
    public void actionPerformed(ActionEvent e) {

        Color clr;

        //Check for not null value
        if(tsize.getText()==null || tsize.getText().length()==0){
            JOptionPane.showMessageDialog(null, "You Must Enter Animal Size",
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

        //Check for legal values on size fish between 20-320
        int size=Integer.parseInt(tsize.getText());
        if(size>320||size<20){
            JOptionPane.showMessageDialog(null, "The Animal Size Must to be\nBetween 20 to 320",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int hor = Cmd_hor.getItemAt(Cmd_hor.getSelectedIndex());
        int ver = Cmd_ver.getItemAt(Cmd_ver.getSelectedIndex());
        int eatfreq=seat.getValue();
        String color = Cmd_color.getItemAt(Cmd_color.getSelectedIndex());
        int randomX_front = ThreadLocalRandom.current().nextInt(50, 800);
        int randomY_front = ThreadLocalRandom.current().nextInt(50, 500);

        //Check for Color
        switch(color) {
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
                clr= Color.PINK;
                break;
            default:
                clr = Color.WHITE;
                break;
        }

        //Add new Fish
        if(e.getSource().equals(fish)){

            seaFactory=new AnimalFactory(size,randomX_front,randomY_front,hor,ver,clr,eatfreq);
            SeaC=seaFactory.produceSeaCreature("Fish");
            AquaPanel.Animals.add((Swimmable)SeaC);
            setVisible(false);
            AquaFrame.getPanel().repaint();

            setVisible(false);
            for (Swimmable i : AquaPanel.getAnimals()) {
                (new Thread(i)).start();

            }
            AquaFrame.getPanel().repaint();
        }

        //Add new JellyFish
        if(e.getSource().equals(jellyfish)){
            seaFactory=new AnimalFactory(size,randomX_front,randomY_front,hor,ver,clr,eatfreq);
            SeaC=seaFactory.produceSeaCreature("JellyFish");
            AquaPanel.Animals.add((Swimmable)SeaC);
            setVisible(false);
            AquaFrame.getPanel().repaint();

            setVisible(false);
            for (Swimmable i : AquaPanel.getAnimals()) {
                (new Thread(i)).start();
            }
            AquaFrame.getPanel().repaint();
        }

    }
}
