/**
 * AquaPanel Contain all the buttons at the bottom of the frame
 * and implements the main function
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CyclicBarrier;

public class AquaPanel extends JPanel implements ActionListener, Observer {

    private JPanel panel = new JPanel();
    private BorderLayout myBorderLayout = new BorderLayout();
    private JButton AddAnimal,AddPlanet, Duplicate, Sleep, Reset, WakeUp, Food,Decorator, Info, Exit;
    private AddAnimalDialog dialog = new AddAnimalDialog("Add Animal Dialog");
    private AddPlanetDialog pdialog = new AddPlanetDialog("Add Planet Dialog");
    private static Image icon = null;
    private static boolean isFood =false;
    private static WromSingleton Worm_instance;
    private JTable table;
    private JScrollPane jp = null;
    public static LinkedHashSet<Swimmable> Animals = new LinkedHashSet<Swimmable>();
    public static LinkedHashSet<Immobile> Planet = new LinkedHashSet<>();
    private CyclicBarrier barrier=null;
    private JDialog ChangeColorDialog,DuplicateDialog;

    //Constructor
    public AquaPanel() {
        panel.setLayout(new GridLayout(1, 7, 0, 0)); //set the layout of the buttons

        //Add all the buttons with ActionListener
        AddAnimal = new JButton("Add Animal");
        AddAnimal.addActionListener(this);

        AddPlanet = new JButton("Add Planet");
        AddPlanet.addActionListener(this);

        Duplicate = new JButton("Duplicate");
        Duplicate.addActionListener(this);

        Sleep = new JButton("Sleep");
        Sleep.addActionListener(this);

        WakeUp = new JButton("Wake Up");
        WakeUp.addActionListener(this);

        Reset = new JButton("Reset");
        Reset.addActionListener(this);

        Food = new JButton("Food");
        Food.addActionListener(this);

        Decorator = new JButton("Decorator");
        Decorator.addActionListener(this);

        Info = new JButton("Info");
        Info.addActionListener(this);

        Exit = new JButton("Exit");
        Exit.addActionListener(this);

        //Set Color for the buttons
        AddAnimal.setBackground(Color.lightGray);
        AddPlanet.setBackground(Color.lightGray);
        Duplicate.setBackground(Color.lightGray);
        Sleep.setBackground(Color.lightGray);
        WakeUp.setBackground(Color.lightGray);
        Reset.setBackground(Color.lightGray);
        Food.setBackground(Color.lightGray);
        Decorator.setBackground(Color.lightGray);
        Info.setBackground(Color.lightGray);
        Exit.setBackground(Color.lightGray);

        //Add all the buttons to the panel
        this.setLayout(myBorderLayout);
        panel.add(AddAnimal);
        panel.add(AddPlanet);
        panel.add(Duplicate);
        panel.add(Sleep);
        panel.add(WakeUp);
        panel.add(Reset);
        panel.add(Food);
        panel.add(Decorator);
        panel.add(Info);
        panel.add(Exit);
        add(panel, BorderLayout.SOUTH);

    }

    //draw all the staff to the panel
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if(AquaFrame.getImage()!=null){
            Dimension dm = getSize();
            g.drawImage(AquaFrame.getImage(), 0, 0, dm.width, dm.height, this);
        }

        for(Immobile j : Planet){
            j.drawCreatrue(g);
        }

        for (Swimmable i : Animals) {
            i.drawCreatrue(g);
        }

        //Check if Singleton Worm_instance is not null
        if(Worm_instance!=null){
            Graphics2D g2d = (Graphics2D) g;
            int x = (this.getWidth() - icon.getWidth(null)) / 2;
            int y = (this.getHeight() - icon.getHeight(null)) / 2;
            g2d.drawImage(icon, x, y, null);
        }

    }

    //Get the hashset animals
    public static LinkedHashSet<Swimmable> getAnimals() {
        return Animals;
    }
    //Get the Worm icon

    public static Image getIcon() {
        return icon;
    }
    //Ser the worm icon
    public static void setIcon(Image icon) {
        AquaPanel.icon = icon;
    }

    //increment eatcount to animal
    public void callback(Swimmable cb){
        cb.eatInc();
    }

    public static WromSingleton getWorm_instance() {
        return Worm_instance;
    }

    public static void setWorm_instance(WromSingleton worm_instance) {
        Worm_instance = worm_instance;
    }

    static public boolean checkFood() {
        return isFood;
    }

    /**
     * CallBack function
     * @param sw
     */
    synchronized public void eatFood(Swimmable sw)
    {
        if(isFood)
        {
            icon = null;
            isFood=false;
            WromSingleton.set();
            sw.eatInc();
            System.out.println("The "+sw.getColor()+" "+sw.getAnimalName()+" with size "+sw.getSize()+" ate food.");
        }
        else
        {
            System.out.println("The "+sw.getColor()+" "+sw.getAnimalName()+" with size "+sw.getSize()+" missed food.");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        JOptionPane.showMessageDialog(null,arg +" Wants Food! ","Time To Eat",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    //implements all the action
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(AddAnimal)) {

            //Check if the Aquarium Contain 5 animals
            if(Animals.size()==5){
                JOptionPane.showMessageDialog(this, "The Aquarium Can Contain Only 5 Animals",
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            dialog.setVisible(true);
        }

        if (e.getSource().equals(AddPlanet)) {
            //Check if the Aquarium Contain 5 Planets
            if(AquaPanel.Planet.size()==5){
                JOptionPane.showMessageDialog(null, "The Aquarium Can Contain Only 5 Planets",
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            pdialog.setVisible(true);
        }

        if(e.getSource().equals(Duplicate)) {
            //Check if the Aquarium Contain 5 animals
            if (Animals.size() == 5) {
                JOptionPane.showMessageDialog(null, "You Can't Duplicate More Animals\nThe Aquarium Can Contain Only 5 Animals",
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            //Check if the Aquarium Contain 0 animals
            if (Animals.size() == 0) {
                JOptionPane.showMessageDialog(null, "There's No Animals In The Aquarium To Duplicate",
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            //Create Dialog
            DuplicateDialog=new JDialog();
            DuplicateDialog.setSize(400,200);
            DuplicateDialog.setLayout(new BorderLayout());
            DuplicateDialog.setLocationRelativeTo(null);
            DuplicateDialog.setVisible(true);
            DuplicateDialog.setTitle("Duplicate Animals Dialog");

            AddDuplicateAnimal ADA  = new AddDuplicateAnimal(DuplicateDialog);
            DuplicateDialog.add(ADA);

        }

        //set Animals to sleep
        if (e.getSource().equals(Sleep)) {
            for (Swimmable i : Animals) {
                i.setFlag(false);
            }
        }

        //Reset all Animals And Planet set
        if (e.getSource().equals(Reset)) {

            for(Swimmable sw:Animals){
                sw.setID(0);
                sw.Stop();
            }

            //clear all
            Animals.clear();
            Planet.clear();
            CareTaker.getRestoreAnimals().clear();
            CareTaker.getRestorePlanet().clear();
            isFood=false;
            if(icon!=null)
                icon=null;
           repaint();
        }

        //Wakeup all animals
        if (e.getSource().equals(WakeUp)) {
            for (Swimmable i : Animals) {
                i.setResume();
            }
        }

        //set the worm icon on screen
        if (e.getSource().equals(Food)) {

            if (!Animals.isEmpty()) {
                barrier = new CyclicBarrier(Animals.size());
                for (Swimmable i : Animals) {
                    i.setBarrier(barrier);
                }
            }

            icon = new ImageIcon("wormicon.png").getImage();
            Worm_instance = WromSingleton.getInstance();
            isFood=true;
            repaint();

        }

        if(e.getSource().equals(Decorator)){

            if(Animals.size()==0){
                JOptionPane.showMessageDialog(null, "There's No Animals In The Aquarium To Change There Color",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //Create Dialog
            ChangeColorDialog=new JDialog();
            ChangeColorDialog.setSize(400,200);
            ChangeColorDialog.setLayout(new BorderLayout());
            ChangeColorDialog.setLocationRelativeTo(null);
            ChangeColorDialog.setVisible(true);
            ChangeColorDialog.setTitle("Change Animals Color");

            JPanelDecorator JPD=new JPanelDecorator(ChangeColorDialog);
            ChangeColorDialog.add(JPD);

        }

        //set jtable to show all info about the animal
        if (e.getSource().equals(Info)) {
            String[][] data = new String[Animals.size()+1][8];
            int i = 0;
            int sum = 0;

            for (Swimmable s : Animals) {
                sum+=s.getEatCount();
            }

            data[Animals.size()][0] = "Total";
            data[Animals.size()][7] = "" + sum;
            for (Swimmable j : Animals) {
                data[i][0] = j.getAnimalName();
                data[i][1] = "" + j.getColor();
                data[i][2] = "" + j.getSize();
                data[i][3] = "" + Math.abs(j.getHorSpeed());
                data[i][4] = "" + Math.abs(j.getVerSpeed());
                data[i][5] = "" + j.getEatfreq();
                data[i][6] = "" + j.getHungerState();
                data[i][7] = "" + j.getEatCount();
                i++;
            }

            String[] header = {"Animal","Color", "Size","Hor.Speed","Ver.speed","Eat Frequency","HungerState","Eat counter"};
            table = new JTable(data, header);
            if (jp == null) {
                table.setPreferredSize(new Dimension(200, 490));
                jp = new JScrollPane(table);
                jp.setVisible(true);
                add(jp);
                this.revalidate();
            } else {
                jp.setVisible(false);
                jp = null;
            }
        }

        //exit from the program
        if (e.getSource().equals(Exit)) {
            System.exit(0);
        }
    }

}

