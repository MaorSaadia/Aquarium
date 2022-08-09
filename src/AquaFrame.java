/**
 * AquaFrame Contain the MenuBar
 * @author Maor Saadia 206748360
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AquaFrame extends JFrame implements ActionListener {

    private static AquaPanel panel;
    private static final int WIDTH = 1050;
    private static final int HEIGHT = 600;
    private JMenu File, Background, Help,Memento;
    private JMenuItem Exit, Image, Blue, None, help,SaveObjectState,RestoreObjectState;
    private JMenuBar menu_bar;
    private static Image image = null;
    private JDialog SaveAnimalsDialog,SavePlanetDialog,RestoreAnimalsDialog,RestorePlanetDialog;
    private static CareTaker carTakerMemento;

    public static void main(String[] args) {

        //Create the frame
        AquaFrame frame = new AquaFrame("My Aquarium");

        carTakerMemento=new CareTaker();
        panel=new AquaPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        frame.add(panel);
        frame.setVisible(true);
    }

    //Constructor
    public AquaFrame(String string) {
        super(string); //set name

        //Add all the MenuBar
        File = new JMenu("File");
        Background = new JMenu("Background");
        Help = new JMenu("Help");
        Memento = new JMenu("Memento");

        Exit = new JMenuItem("Exit");
        Exit.addActionListener(this);

        Image = new JMenuItem("Image");
        Image.addActionListener(this);

        Blue = new JMenuItem("Blue");
        Blue.addActionListener(this);

        None = new JMenuItem("None");
        None.addActionListener(this);

        SaveObjectState = new JMenuItem("SaveObjectState");
        SaveObjectState.addActionListener(this);

        RestoreObjectState = new JMenuItem("RestoreObjectState");
        RestoreObjectState.addActionListener(this);

        help = new JMenuItem("Help");
        help.addActionListener(this);

        File.add(Exit);
        Background.add(Image);
        Background.add(Blue);
        Background.add(None);
        Memento.add(SaveObjectState);
        Memento.add(RestoreObjectState);
        Help.add(help);

        menu_bar = new JMenuBar();
        menu_bar.add(File);
        menu_bar.add(Background);
        menu_bar.add(Memento);
        menu_bar.add(Help);
        setJMenuBar(menu_bar);
    }

    //return the image background
    public static java.awt.Image getImage() {
        return image;
    }

    //return the panel
    public static AquaPanel getPanel() {
        return panel;
    }

    public static CareTaker getCarTakerMemento() {
        return carTakerMemento;
    }

    //implements all the action
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Exit))
            System.exit(0);

        //set background to blue
        if (e.getSource().equals(Blue)) {
            image=null;
            panel.repaint();
            panel.setBackground(Color.BLUE);
        }

        //set Image
        if(e.getSource().equals(Image)) {
            if(image==null) {
                image = new ImageIcon("aquarium.jpg").getImage();
                panel.repaint();
            }
        }

        //set Default background
        if (e.getSource().equals(None)) {
            image=null;
            panel.repaint();
            Color color = UIManager.getColor("Panel.background"); //default background
            panel.setBackground(color);
        }

        if(e.getSource().equals(SaveObjectState)) {
            Object[] options = {"Animals", "Planet"};

            int choice = JOptionPane.showOptionDialog(getContentPane(), "Select What You Want To Save", "Save Object Dialog", 0,
                    JOptionPane.INFORMATION_MESSAGE, null, options, null);

            if(choice==0) {
                SaveAnimalsDialog = new JDialog();
                SaveAnimalsDialog.setSize(560, 200);
                SaveAnimalsDialog.setLayout(new BorderLayout());
                SaveAnimalsDialog.setLocationRelativeTo(null);
                SaveAnimalsDialog.setVisible(true);
                SaveAnimalsDialog.setTitle("Save Animal Dialog");

                SaveAnimalsObject SOD = new SaveAnimalsObject(SaveAnimalsDialog);
                SaveAnimalsDialog.add(SOD);
            }
            else if(choice==1){

                SavePlanetDialog = new JDialog();
                SavePlanetDialog.setSize(560, 200);
                SavePlanetDialog.setLayout(new BorderLayout());
                SavePlanetDialog.setLocationRelativeTo(null);
                SavePlanetDialog.setVisible(true);
                SavePlanetDialog.setTitle("Save Planet Dialog");

                SavePlanetObject SPD = new SavePlanetObject(SavePlanetDialog);
                SavePlanetDialog.add(SPD);

            }
        }
        if(e.getSource().equals(RestoreObjectState)){

            Object[] options = {"Animals", "Planet"};

            int choice = JOptionPane.showOptionDialog(getContentPane(), "Select What You Want To Restore", "Restore Object Dialog", 0,
                    JOptionPane.INFORMATION_MESSAGE, null, options, null);

            if(choice==0) {
                RestoreAnimalsDialog = new JDialog();
                RestoreAnimalsDialog.setSize(560, 200);
                RestoreAnimalsDialog.setLayout(new BorderLayout());
                RestoreAnimalsDialog.setLocationRelativeTo(null);
                RestoreAnimalsDialog.setVisible(true);
                RestoreAnimalsDialog.setTitle("Restore Animal Dialog");

                RestoreAnimalsObject ROD = new RestoreAnimalsObject(RestoreAnimalsDialog);
                RestoreAnimalsDialog.add(ROD);
            }
            else if(choice==1){

                RestorePlanetDialog = new JDialog();
                RestorePlanetDialog.setSize(560, 200);
                RestorePlanetDialog.setLayout(new BorderLayout());
                RestorePlanetDialog.setLocationRelativeTo(null);
                RestorePlanetDialog.setVisible(true);
                RestorePlanetDialog.setTitle("Restore Planet Dialog");

                RestorePlanetObject RPD = new RestorePlanetObject(RestorePlanetDialog);
                RestorePlanetDialog.add(RPD);

            }
        }

        if (e.getSource().equals(help))
            JOptionPane.showMessageDialog(this, "Home Work 3\nGui @ Threads");
    }
}


