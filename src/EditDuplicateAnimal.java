/**
 * EditDuplicateAnimal to edit and add a duplicate animal
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDuplicateAnimal extends JDialog implements ActionListener {

    private JButton Edit,Cancel;
    private final JLabel label = new JLabel(" ",SwingConstants.CENTER);
    private final JLabel esize = new JLabel("Choice new Size (20-320):",SwingConstants.CENTER);
    private final JLabel ehor = new JLabel("Choice new HorSpeed:",SwingConstants.CENTER);
    private final JLabel ever = new JLabel("Choice new VerSpeed:",SwingConstants.CENTER);
    private final JLabel ecolor = new JLabel("Choice new Color:",SwingConstants.CENTER);
    private final JLabel eempty = new JLabel(" ",SwingConstants.CENTER);
    private Integer numbers[]={1,2,3,4,5,6,7,8,9,10};
    private JComboBox<Integer> Cmd_hor;
    private JComboBox<Integer> Cmd_ver;
    private String colors[] = {"Black", "Red", "Blue", "Green", "Cyan",
            "Orange", "Yellow", "Magenta", "Pink"};
    private JComboBox<String> Cmd_color;
    private JTextField tsize;
    private Swimmable duplicateSwimmable;

    //Constructor
    public EditDuplicateAnimal (String string ,Swimmable duplicateSwimmable) {
        this.setTitle(string);

        this.duplicateSwimmable=duplicateSwimmable;

        //Set the size TextField
        add(esize);
        tsize = new JTextField();
        tsize.setText(String.valueOf(duplicateSwimmable.getSize()));
        add(tsize);

        //Set the ComboBox of the Hor.Speed
        Cmd_hor = new JComboBox<>();
        for (int i = 0; i < numbers.length; i++)
            Cmd_hor.addItem(numbers[i]);
        add(ehor);
        add(Cmd_hor);

        //Set the ComboBox of the Ver.Speed
        Cmd_ver = new JComboBox<>();
        for (int i = 0; i < numbers.length; i++)
            Cmd_ver.addItem(numbers[i]);
        add(ever);
        add(Cmd_ver);

        //Set the ComboBox of the Color
        Cmd_color = new JComboBox<>();
        for (int i = 0; i < colors.length; i++)
            Cmd_color.addItem(colors[i]);

        add(ecolor);
        add(Cmd_color);

        Edit = new JButton("Edit");
        Edit.addActionListener(this);

        Cancel = new JButton("Cancel");
        Cancel.addActionListener(this);

        add(label);
        Edit.setBackground(Color.DARK_GRAY);
        Edit.setForeground(Color.WHITE);
        add(Edit);
        add(eempty);
        Cancel.setBackground(Color.DARK_GRAY);
        Cancel.setForeground(Color.WHITE);
        add(Cancel);

        //set the layout of the dialog
        setLayout(new GridLayout(0, 2, 0, 2));
        setSize(280, 250);
        setLocationRelativeTo(null);

    }

    //implements all the action
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(Edit)){
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
            String color = Cmd_color.getItemAt(Cmd_color.getSelectedIndex());
            int X_front = duplicateSwimmable.getX_front();
            int Y_front = duplicateSwimmable.getY_front();

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

            //Edit The duplicate Swimmable
            duplicateSwimmable.Edit_Swimmable(size,X_front,Y_front,hor,ver,clr);
            AquaPanel.Animals.add(duplicateSwimmable);
            for (Swimmable i : AquaPanel.getAnimals()) {
                (new Thread(i)).start();
            }
            this.dispose();
        }

        if(e.getSource().equals(Cancel)){
            //Cancel and keep the Swimmable as it is
            AquaPanel.Animals.add(duplicateSwimmable);
            for (Swimmable i : AquaPanel.getAnimals()) {
                (new Thread(i)).start();
            }
            this.dispose();
        }

    }
}
