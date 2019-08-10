package allPkg;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.awt.dnd.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class IconDnD implements ActionListener {
    private Boolean importedPolicy = false;
    private ArrayList<Policy> policies = new ArrayList<Policy>();
    private JFrame frame;
    private TitledBorder menu;
    private JPanel visual;
    private JPanel panelBoxV;
    private JPanel panel;
    private JPanel panelBox;
    private ArrayList<JButton> myMenuList;
    private ArrayList<JComboBox> colorComBox;
    private ArrayList<String> menuName = new ArrayList<>() {};
    private Hashtable<String, Color> stringToColor = new Hashtable<>() {};
    private String[] colors = {"black","blue","cyan","gray","green","magenta","orange","red", "pink","yellow"};
    private String[] nameParty= {"assigner","assignee","attributedParty","consentingParting","informedParty","compensatedParty","trackingParty"};
    private int widthOfPage = 1400;
    private int heightOfPage = 800;
    private geometry2Builder geometry2Builder;
    private geometry2 visualization;
    private bubbleMapBuilder bubbleBuilder;
    private bubbleMap bubble;



    IconDnD() {
        stringToColor();
        menuItems();
    }



    private void display() {
        JFrame frameIntro = new JFrame("ODRL Visual");
        frameIntro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameIntro.setSize(widthOfPage, heightOfPage);
        JButton start = new JButton("Start: ODRL Visualizer");
        JTextField field = new JTextField(10);
        field.setText("ODRL Visualization Tool");
        start.setBounds(50, 100, 95, 30);
        start.setBackground(Color.gray);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameIntro.setVisible(false);
                display2();
                frameIntro.removeAll();
            }
        });
        frameIntro.add(field);
        frameIntro.add(start);
        frameIntro.setVisible(true);
    }

    private void display2() {
        frame = new JFrame("ODRL Visual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(widthOfPage, heightOfPage);

        menu = new TitledBorder("Menu");
        menu.setTitlePosition(TitledBorder.CENTER);
        menu.setTitlePosition(TitledBorder.TOP);

        panelBox = new JPanel();
        panelBox.setLayout(new GridLayout(5,1,0,0));
        panel = new JPanel();
        panelBoxV = new JPanel();
        visual = new JPanel();

        panelBox.setBounds(0, 0, 250, 200);
        panelBox.setBackground(Color.lightGray);
        panelBox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
        panelBox.setBorder(menu);

        panelBoxV.setBounds(0, 200, 250, (heightOfPage-200));
        panelBoxV.setBackground(Color.lightGray);
        panelBoxV.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));


        visual.setBounds(250, 0, (widthOfPage-250), heightOfPage);
        visual.setBackground(Color.white);
        visual.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));

        /*
        panel.setBounds(0, 600, 900, 100);
        panel.setBackground(Color.blue);
*/
        frame.add(panelBoxV);
        frame.add(panelBox);
        frame.add(visual);
        //frame.add(panel);

        myMenuList = new ArrayList<JButton>();

        for (int i = 0 ; i < menuName.size(); i++) {
            Icon mainMenuButtonI = new ImageIcon("/Users/Chapman/Desktop/icons/Interface/MainMenu/" + menuName + ".png");
            JButton mainMenuButton = new JButton(mainMenuButtonI);
            mainMenuButton.setText(menuName.get(i));
            mainMenuButton.setMargin(new Insets(0,0,0,0));
            mainMenuButton.setOpaque(true);
            mainMenuButton.setBorderPainted(false);
            mainMenuButton.setBackground(Color.gray);
            mainMenuButton.setFocusPainted(false);
            panelBox.add(mainMenuButton);
            mainMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
            mainMenuButton.setIconTextGap(20);
            mainMenuButton.addActionListener(this);
            myMenuList.add(mainMenuButton);
        }

        frame.setVisible(true);
    }

    public void callXMLPolicyBuilder(String nameFile) {
        testPolicyMaker parser = new testPolicyMaker();
        parser.setXmlFunction(nameFile);
        policies.add(parser.getPolicy());
    }

    public void addImportMenu() {
                JFileChooser fileChoose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int res = fileChoose.showSaveDialog(null);

                if (res == fileChoose.APPROVE_OPTION) {
                    // Accepted if the file is .txt otherwise error message
                    if (fileChoose.getSelectedFile().getAbsolutePath().endsWith("txt") || fileChoose.getSelectedFile().getAbsolutePath().endsWith("xml")) {
                        String filePath = fileChoose.getSelectedFile().getAbsolutePath();
                        System.out.println(filePath);
                        // Creates the parser and the ODRL policy constructor
                        callXMLPolicyBuilder(filePath);
                    } else {
                        JOptionPane errorWarning = new JOptionPane();
                        errorWarning.showMessageDialog(panel, "Could not open file", "Error", res);
                    }
                }
    }

    public void addBuildMenu() {
        TitledBorder buildMenu = new TitledBorder("Build");
        buildMenu.setTitlePosition(TitledBorder.CENTER);
        buildMenu.setTitlePosition(TitledBorder.TOP);

        JPanel panelPP = new JPanel();

        TitledBorder buildMenuPP = new TitledBorder("Rule");
        buildMenu.setTitlePosition(TitledBorder.CENTER);
        buildMenu.setTitlePosition(TitledBorder.TOP);
        panelPP.setBorder(buildMenuPP);
        panelBoxV.add(panelPP);
        panelBoxV.setBorder(buildMenu);

        Icon permissionI = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
        JButton permission = new JButton(permissionI);
        permission.setPreferredSize(new Dimension(60, 60));
        permission.setFocusPainted(false);
        panelPP.add(permission);

        PolicyBuilder newPolicy = new PolicyBuilder();


        permission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel panelNewPermission = new JPanel();
                GroupLayout layoutNewPermission = new GroupLayout(panelNewPermission);
                layoutNewPermission.setAutoCreateGaps(true);
                panelNewPermission.setBorder(new TitledBorder("Permission"));

                Party party = new Party();
                JLabel partyLabel = new JLabel("Party: ");
                JComboBox newParty = new JComboBox(nameParty);
                newParty.setBounds(50, 50,90,20);
                newParty.addActionListener(this);

                JTextField nameParty = new JTextField("Party name");

                layoutNewPermission.setHorizontalGroup(layoutNewPermission.createSequentialGroup()
                                .addComponent(partyLabel)
                                .addComponent(newParty)
                        );

                visual.add(panelNewPermission);
                //visual.add(permission);
            }


        });

        Icon prohibitionI = new ImageIcon("/Users/Chapman/Downloads/error.png");
        JButton prohibition = new JButton(prohibitionI);
        prohibition.setPreferredSize(new Dimension(60, 60));
        prohibition.setFocusPainted(false);
        panelPP.add(prohibition);
        prohibition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visual.add(prohibition);
            }


        });
    }

    public void addVisualMenu() {
        JPanel panelVisualMenu = new JPanel();
        GroupLayout layoutNewPermission = new GroupLayout(panelVisualMenu);
        panelVisualMenu.setLayout(layoutNewPermission);
        panelVisualMenu.setBackground(Color.lightGray);
        layoutNewPermission.setAutoCreateGaps(true);
        panelVisualMenu.setLayout(layoutNewPermission);

        geometry2Builder = new geometry2Builder();

        TitledBorder buildMenu = new TitledBorder("Parameters");
        buildMenu.setTitlePosition(TitledBorder.ABOVE_TOP);
        buildMenu.setTitlePosition(TitledBorder.ABOVE_TOP);

        panelVisualMenu.setBorder(buildMenu);

        String[] listColorLabel = {"Permission", "Prohibition", "Constraint","Duty"};

        colorComBox = new ArrayList<>();
        ArrayList<JLabel> labels= new ArrayList<>();

        for (int i = 0 ; i < 4; i++) {
            JLabel labelColor = new JLabel(listColorLabel[i]+ ": ");
            JComboBox boxColor = new JComboBox(colors);
            boxColor.setActionCommand("boxColor"+i);
            boxColor.addActionListener(this);
            panelVisualMenu.add(labelColor);
            panelVisualMenu.add(boxColor);
            labels.add(labelColor);
            colorComBox.add(boxColor);
        }

        JButton done = new JButton("Done");
        panelVisualMenu.add(done);
        done.addActionListener(this);

        JButton reset = new JButton("Reset");
        panelVisualMenu.add(reset);
        reset.addActionListener(this);


        layoutNewPermission.setHorizontalGroup(
                layoutNewPermission.createSequentialGroup()
                        .addGroup(layoutNewPermission.createParallelGroup()
                                .addComponent(labels.get(0))
                                .addComponent(labels.get(1))
                                .addComponent(labels.get(2))
                                .addComponent(labels.get(3))
                                .addComponent(done))
                        .addGroup(layoutNewPermission.createParallelGroup()
                                .addComponent(colorComBox.get(0))
                                .addComponent(colorComBox.get(1))
                                .addComponent(colorComBox.get(2))
                                .addComponent(colorComBox.get(3))
                                .addComponent(reset))
        );

        layoutNewPermission.setVerticalGroup(
                layoutNewPermission.createSequentialGroup()
                        .addGroup(layoutNewPermission.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels.get(0)).addComponent(colorComBox.get(0)))
                        .addGroup(layoutNewPermission.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels.get(1)).addComponent(colorComBox.get(1)))
                        .addGroup(layoutNewPermission.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels.get(2)).addComponent(colorComBox.get(2)))
                        .addGroup(layoutNewPermission.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels.get(3)).addComponent(colorComBox.get(3)))
                        .addGroup(layoutNewPermission.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(done).addComponent(reset))
        );


        panelBoxV.add(panelVisualMenu);

    }


    public void stringToColor() {
        stringToColor.put("black",Color.black);
        stringToColor.put("blue",Color.blue);
        stringToColor.put("cyan",Color.cyan);
        stringToColor.put("gray",Color.gray);
        stringToColor.put("green",Color.green);
        stringToColor.put("magenta",Color.magenta);
        stringToColor.put("orange",Color.orange);
        stringToColor.put("red",Color.red);
        stringToColor.put("pink",Color.pink);
        stringToColor.put("yellow",Color.yellow);
    }

    public void menuItems() {
        menuName.add("Import");
        menuName.add("Build");
        menuName.add("Export");
        menuName.add("Granular");
        menuName.add("Map");
    }


    public void actionPerformed(ActionEvent e) {
        if (menuName.contains(e.getActionCommand())) {
            for (int i = 0; i < myMenuList.size(); i++) {
                if (e.getActionCommand().equals(myMenuList.get(i).getText())) {
                    myMenuList.get(i).setBackground(Color.gray.darker());
                } else {
                    myMenuList.get(i).setBackground(Color.gray);
                }
            }

            panelBoxV.removeAll();
            panelBoxV.revalidate();
            panelBoxV.repaint();

            if (e.getActionCommand().equals("Build")) {
                addBuildMenu();
            }

            if (e.getActionCommand().equals("Import")) {
                addImportMenu();
                importedPolicy = true;
            }

            if (e.getActionCommand().equals("Granular")) {
                addVisualMenu();
            }

        }

            if (e.getActionCommand().equals("Map")) {
                visual.setVisible(false);
                frame.remove(visualization);
                if (importedPolicy = true) {
                    bubbleBuilder.setPolicy(policies.get(policies.size() - 1));
                    JFrame frameNewN = new JFrame();
                    frameNewN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameNewN.setSize(widthOfPage, heightOfPage);
                    frameNewN.getContentPane().add(bubble);
                    frameNewN.setVisible(true);
                }
            }


            if (e.getActionCommand().equals("Done")) {
                if (importedPolicy = true) {
                    geometry2Builder.setPolicy(policies.get(policies.size() - 1));
                    visualization = geometry2Builder.build();
                    //visualization.setLocation(visualization.getX()+250,0);
                    visual.setVisible(false);
                    frame.add(visualization);
                    //visualization.setLocation(visualization.getX()+250,0);
                    frame.setVisible(true);
                }

                frame.setVisible(true);
            }

        if (e.getActionCommand().equals("Reset")) {
            frame.remove(visualization);
            visual.setVisible(true);
            geometry2Builder = new geometry2Builder();
            geometry2Builder.setPolicy(policies.get(policies.size() - 1));
        }


            if (e.getActionCommand().contains("boxColor")) {
                String lastNumber = e.getActionCommand().substring((e.getActionCommand().length())-1);
                System.out.println(lastNumber);
                switch (lastNumber) {
                    case "0":
                        String choice0 = (String) colorComBox.get(0).getSelectedItem();
                        geometry2Builder.setColorPermission(stringToColor.remove(choice0));
                        break;

                    case "1":
                        String choice1 = (String) colorComBox.get(1).getSelectedItem();
                        geometry2Builder.setColorProhibition(stringToColor.remove(choice1));
                        break;

                    case "2":
                        String choice2 = (String) colorComBox.get(2).getSelectedItem();
                        geometry2Builder.setColorConstraint(stringToColor.remove(choice2));
                        break;

                    case "3":
                        String choice3 = (String) colorComBox.get(3).getSelectedItem();
                        geometry2Builder.setColorDuty(stringToColor.remove(choice3));
                        break;
                }
            }
    }


    public static void main(String[] args) {
        new IconDnD().display();
    }

}