package allPkg;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public class IconDnD implements ActionListener,ChangeListener {
    private boolean importedPolicy = false;
    private boolean builtPolicy = false;
    private ArrayList<Policy> policies = new ArrayList<Policy>();
    private JFrame frame;
    private TitledBorder menu;
    private JPanel visual;
    private JPanel panelBoxV;
    private JPanel panel;
    private JPanel panelBox;
    private JPanel panelVisualMenu;
    private ArrayList<JButton> myMenuList;
    private ArrayList<JComboBox> colorComBox;
    private ArrayList<String> menuName;
    private Hashtable<String, Color> stringToColor = new Hashtable<>() {};
    private String[] colors = {"default","blue","cyan","gray","green","magenta","orange","red", "pink","yellow"};
    private int widthOfPage = 1400;
    private int heightOfPage = 800;
    private boolean inBubble = false;
    private boolean inGranular = false;
    private boolean inSituation = false;
    private boolean inBuildArea = false;

    private geometry2Builder geometry2Builder;
    private situationalBuilder situationalBuilder;
    private bubbleMapBuilder bubbleBuilder;
    private JPanel visualization;
    private JPanel bubble;
    private JPanel situational;

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
        frame.setLayout(null);

        menu = new TitledBorder("Menu");
        menu.setTitlePosition(TitledBorder.CENTER);
        menu.setTitlePosition(TitledBorder.TOP);
        menu.setTitleColor(Color.lightGray);

        panelBox = new JPanel();
        panelBox.setLayout(new GridLayout(6,1,0,0));
        panel = new JPanel();
        panelBoxV = new JPanel();
        visual = new JPanel();

        panelBox.setBounds(0, 0, 250, 200);
        panelBox.setBackground(Color.white);
        panelBox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
        panelBox.setBorder(menu);

        panelBoxV.setBounds(0, 200, 250, (heightOfPage-200));
        panelBoxV.setBackground(Color.white);
        panelBoxV.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.lightGray));
        panelBoxV.setLayout(null);
        TitledBorder buildMenu = new TitledBorder("Build");
        buildMenu.setTitlePosition(TitledBorder.CENTER);
        buildMenu.setTitlePosition(TitledBorder.TOP);
        buildMenu.setTitleColor(Color.lightGray);
        panelBoxV.setBorder(buildMenu);

        visual.setBounds(250, 0, (widthOfPage-250), heightOfPage);
        visual.setBackground(Color.white);
        visual.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
        visual.setLayout(new GridLayout(2,3,5,5));

        frame.add(panelBoxV);
        frame.add(panelBox);
        frame.add(visual);

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
                    if (fileChoose.getSelectedFile().getAbsolutePath().endsWith("xml")) {
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
        odrlBuilding build = new odrlBuilding(visual,panelBoxV,policies);
        build.addBuildMenu();
        panelBoxV.revalidate();
        panelBoxV.repaint();
        builtPolicy = true;
    }

    public void addVisualMenu() {
        panelVisualMenu = new JPanel();
        panelVisualMenu.setBounds(10, 20, 210, (heightOfPage-200));
        GroupLayout layoutNewPermission = new GroupLayout(panelVisualMenu);
        panelVisualMenu.setLayout(layoutNewPermission);
        panelVisualMenu.setBackground(Color.white);
        layoutNewPermission.setAutoCreateGaps(true);
        panelVisualMenu.setLayout(layoutNewPermission);

        String[] listColorLabel = {"Permission", "Prohibition", "Obligation", "Duty", "Constraint"};

        colorComBox = new ArrayList<>();
        ArrayList<JLabel> labels= new ArrayList<>();

        for (int i = 0 ; i < listColorLabel.length; i++) {
            JLabel labelColor = new JLabel(listColorLabel[i]+ ": ");
            labelColor.setForeground(Color.gray);
            JComboBox boxColor = new JComboBox(colors);
            boxColor.setUI(new BasicComboBoxUI(){
                protected JButton createArrowButton()
                {
                    BasicArrowButton arrowButton = new BasicArrowButton(BasicArrowButton.SOUTH, null, null, Color.white, null);
                    return arrowButton;
                }
            });
            boxColor.setActionCommand("boxColor"+i);
            boxColor.addActionListener(this);
            panelVisualMenu.add(labelColor);
            panelVisualMenu.add(boxColor);
            labels.add(labelColor);
            colorComBox.add(boxColor);
        }

        JLabel lineThicknessLabel = new JLabel("Line Thickness: ");
        lineThicknessLabel.setForeground(Color.gray);

        JSlider lineThickness = new JSlider(JSlider.HORIZONTAL,1,8,2);
        lineThickness.addChangeListener(this);
        lineThickness.setForeground(Color.lightGray);

        lineThickness.setMajorTickSpacing(2);
        lineThickness.setMinorTickSpacing(1);
        lineThickness.setPaintTicks(true);
        lineThickness.setPaintLabels(true);

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
                                .addComponent(labels.get(4))
                                .addComponent(lineThicknessLabel)
                                .addComponent(done))
                        .addGroup(layoutNewPermission.createParallelGroup()
                                .addComponent(colorComBox.get(0))
                                .addComponent(colorComBox.get(1))
                                .addComponent(colorComBox.get(2))
                                .addComponent(colorComBox.get(3))
                                .addComponent(colorComBox.get(4))
                                .addComponent(lineThickness)
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
                                .addComponent(labels.get(4)).addComponent(colorComBox.get(4)))
                        .addGroup(layoutNewPermission.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lineThicknessLabel).addComponent(lineThickness))
                        .addGroup(layoutNewPermission.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(done).addComponent(reset))
        );

        //Dimension dimension = new Dimension(210,400);
        //panelVisualMenu.setPreferredSize(dimension);

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
        menuName = new ArrayList<String>();
        menuName.add("Import");
        menuName.add("Build");
        menuName.add("Export");
        menuName.add("Granular");
        menuName.add("Map");
        menuName.add("Scene");
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

            /*
            panelBoxV.removeAll();
            panelBoxV.revalidate();
            panelBoxV.repaint();
            */

            if (e.getActionCommand().equals("Export")) {
                xmlMaking xmlFile = new xmlMaking(policies.get(policies.size() - 1));
                xmlFile.createFile();
                JOptionPane successfulFile = new JOptionPane();
                successfulFile.showMessageDialog(panel, "File created.", "Export", JOptionPane.INFORMATION_MESSAGE);
            }

            if (e.getActionCommand().equals("Build")) {
                inBuildArea = true;
                System.out.println(inBuildArea + "<- building area");
                panelVisualMenu = null;
                panelBoxV.removeAll();
                panelBoxV.revalidate();
                panelBoxV.repaint();
                addBuildMenu();

            }

            if (e.getActionCommand().equals("Import")) {
                addImportMenu();
                importedPolicy = true;
            }

            if (e.getActionCommand().equals("Granular")) {
                System.out.println(builtPolicy + "<=built in bool");
                System.out.println(policies.get(0));
                if (inBuildArea) {
                    panelBoxV.removeAll();
                    panelBoxV.revalidate();
                    panelBoxV.repaint();
                }
                inGranular = true;
                inBubble = false;
                inSituation = false;
                inBuildArea = false;
                geometry2Builder = new geometry2Builder();
                if (panelVisualMenu == null) {
                    System.out.println("IN NULL");
                    addVisualMenu(); }
                if (bubble != null) {frame.remove(bubble); }
                if (situational != null) {frame.remove(situational); }
            }

            if (e.getActionCommand().equals("Map")) {
                if (inBuildArea) {
                    panelBoxV.removeAll();
                    panelBoxV.revalidate();
                    panelBoxV.repaint();
                }
                inBubble = true;
                inSituation = false;
                inGranular = false;
                inBuildArea = false;
                bubbleBuilder = new bubbleMapBuilder();
                if (panelVisualMenu == null) { addVisualMenu(); }
                if (visualization != null) { frame.remove(visualization); }
                if (situational != null) {frame.remove(situational); }
            }

            if (e.getActionCommand().equals("Scene")) {
                if (inBuildArea) {
                    panelBoxV.removeAll();
                    panelBoxV.revalidate();
                    panelBoxV.repaint();
                }
                inSituation = true;
                inBubble = false;
                inGranular = false;
                inBuildArea = false;
                situationalBuilder = new situationalBuilder();
                if (panelVisualMenu != null) { frame.remove(visualization); }
                if (bubble != null) { frame.remove(bubble); }
                if (panelBoxV == null) { addVisualMenu(); }
            }
            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("Done")) {
            if (importedPolicy && inGranular || builtPolicy && inGranular) {
                geometry2Builder.setPolicy(policies.get(policies.size() - 1));
                visualization = geometry2Builder.build();
                visualization.setBounds(0,0,widthOfPage,heightOfPage);
                visual.setVisible(false);
                frame.add(visualization);
            }

            if (importedPolicy && inSituation || builtPolicy && inSituation) {
                situationalBuilder.setPolicy(policies.get(policies.size() - 1));
                situational = situationalBuilder.build();
                situational.setBounds(0,0,widthOfPage,heightOfPage);
                visual.setVisible(false);
                frame.add(situational);
            }

            if (importedPolicy && inBubble || builtPolicy && inBubble) {
                bubbleBuilder.setPolicy(policies.get(policies.size() - 1));
                bubbleBuilder.setUseIcon(true);
                bubble = bubbleBuilder.build();
                bubble.setBounds(0,0,widthOfPage,heightOfPage);
                visual.setVisible(false);
                frame.add(bubble);
            }
            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("Reset")) {
            if (inBubble) {
                frame.remove(bubble);
                bubbleBuilder = new bubbleMapBuilder();
                System.out.println("InBubbleReset");
            }
            if (inGranular) {
                frame.remove(visualization);
                geometry2Builder = new geometry2Builder();
                System.out.println("InGranularReset");
            }
            if (inSituation) {
                frame.remove(situational);
                situationalBuilder = new situationalBuilder();
                System.out.println("InSituationReset");
            }

            for (int i = 0; i < colorComBox.size(); i++) {
                colorComBox.get(i).setSelectedIndex(0);
            }

            visual.setVisible(true);
        }

        if (e.getActionCommand().contains("boxColor")) {
                String lastNumber = e.getActionCommand().substring((e.getActionCommand().length())-1);
                System.out.println(lastNumber);
                switch (lastNumber) {
                    case "0":
                        String choice0 = (String) colorComBox.get(0).getSelectedItem();
                        System.out.println(choice0 + "<-choice0");
                        System.out.println(!choice0.equals("default"));
                        if (!choice0.equals("default")) {
                            if (inGranular) { geometry2Builder.setColorPermission(stringToColor.remove(choice0)); }
                            if (inBubble) { bubbleBuilder.setColorPermission(stringToColor.remove(choice0)); }
                            if (inSituation) { situationalBuilder.setColorPermission(stringToColor.remove(choice0)); }
                        }
                        break;

                    case "1":
                        String choice1 = (String) colorComBox.get(1).getSelectedItem();
                        System.out.println(choice1 + "<-choice1");
                        System.out.println(!choice1.equals("default"));
                        if (!choice1.equals("default")) {
                            if (inGranular) {
                                geometry2Builder.setColorProhibition(stringToColor.remove(choice1));
                            }
                            if (inBubble) {
                                bubbleBuilder.setColorProhibition(stringToColor.remove(choice1));
                            }
                            if (inSituation) {
                                situationalBuilder.setColorProhibition(stringToColor.remove(choice1));
                            }
                        }
                        break;

                    case "2":
                        String choice2 = (String) colorComBox.get(2).getSelectedItem();
                        System.out.println(choice2 + "<-choice1");
                        System.out.println(!choice2.equals("default"));
                        if (!choice2.equals("default")) {
                            if (inGranular) {
                                geometry2Builder.setColorObligation(stringToColor.remove(choice2));
                            }
                            if (inBubble) {
                                bubbleBuilder.setColorObligation(stringToColor.remove(choice2));
                            }
                            if (inSituation) {
                                situationalBuilder.setColorObligation(stringToColor.remove(choice2));
                            }
                        }
                        break;

                    case "3":
                        String choice3 = (String) colorComBox.get(3).getSelectedItem();
                        System.out.println(choice3 + "<-choice1");
                        System.out.println(!choice3.equals("default"));
                        if (!choice3.equals("default")) {
                            if (inGranular) {
                                geometry2Builder.setColorDuty(stringToColor.remove(choice3));
                            }
                            if (inBubble) {
                                bubbleBuilder.setColorDuty(stringToColor.remove(choice3));
                            }
                            if (inSituation) {
                                situationalBuilder.setColorDuty(stringToColor.remove(choice3));
                            }
                        }
                        break;

                    case "4":
                        String choice4 = (String) colorComBox.get(4).getSelectedItem();
                        System.out.println(choice4 + "<-choice1");
                        System.out.println(!choice4.equals("default"));
                        if (!choice4.equals("default")) {
                            if (inGranular) {
                                geometry2Builder.setColorConstraint(stringToColor.remove(choice4));
                            }
                            if (inBubble) {
                                bubbleBuilder.setColorConstraint(stringToColor.remove(choice4));
                            }
                            if (inSituation) {
                                situationalBuilder.setColorConstraint(stringToColor.remove(choice4));
                            }
                        }
                        break;
                }
            }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int lineSize = (int)source.getValue();
            if (inGranular) {
                geometry2Builder.setLineThickness(lineSize);
            }
            if (inBubble) {
                bubbleBuilder.setSizeLines(lineSize);
            }
            if (inSituation) {
                situationalBuilder.setLineThickness(lineSize);
            }

        }
    }

    public static void main(String[] args) {
        new IconDnD().display();
    }

}