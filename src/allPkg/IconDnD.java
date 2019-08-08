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

public class IconDnD implements ActionListener{
    private Boolean importedPolicy = false;
    private ArrayList<Policy> policies = new ArrayList<Policy>();
    private JFrame frame;
    private TitledBorder menu;
    private JPanel visual;
    private JPanel panelBoxV;
    private JPanel panel;
    private JPanel panelBox;
    private ArrayList<JButton> myMenuList;
    private ArrayList<String> menuName = new ArrayList<String>() {};


    private void display() {

        JFrame frameIntro = new JFrame("ODRL Visual");
        frameIntro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameIntro.setSize(900, 700);
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
        frame.setSize(900, 700);

        menu = new TitledBorder("MENU");
        menu.setTitlePosition(TitledBorder.CENTER);
        menu.setTitlePosition(TitledBorder.TOP);

        panelBox = new JPanel();
        panelBox.setLayout(new GridLayout(5,1,0,0));
        panel = new JPanel();
        panelBoxV = new JPanel();
        visual = new JPanel();

        panelBox.setBounds(0, 0, 200, 200);
        panelBox.setBackground(Color.lightGray);
        panelBox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
        panelBox.setBorder(menu);

        panelBoxV.setBounds(0, 200, 200, 400);
        panelBoxV.setBackground(Color.lightGray);
        panelBoxV.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));

        visual.setBounds(200, 0, 700, 600);
        visual.setBackground(Color.white);
        visual.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));

        panel.setBounds(0, 600, 900, 100);
        panel.setBackground(Color.gray);


        /*
        JPanel visual1 = new JPanel();
        JPanel visual2 = new JPanel();
        JPanel visual3 = new JPanel();

        visual1.setBounds(200, 0, 700, 200);
        visual1.setBackground(Color.white);
        visual1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));

        visual2.setBounds(200, 200, 700, 200);
        visual2.setBackground(Color.white);

        visual3.setBounds(200, 400, 700, 200);
        visual3.setBackground(Color.white);
        visual3.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.gray));
        frame.add(visual1);
        frame.add(visual2);
        frame.add(visual3);
*/

        frame.add(panelBoxV);
        frame.add(panelBox);
        frame.add(visual);
        frame.add(panel);


        menuName.add("Import");
        menuName.add("Build");
        menuName.add("Export");
        menuName.add("Visual");
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

        /*
        Icon buildBI = new ImageIcon("/Users/Chapman/Downloads/tools.png");
        JButton buildB = new JButton(buildBI);
        buildB.setText("Build");

        Icon exportBI = new ImageIcon("/Users/Chapman/Downloads/share.png");
        JButton exportB = new JButton(exportBI);
        exportB.setText("Export");

        Icon visualBI = new ImageIcon("/Users/Chapman/Desktop/icons/visual.png");
        JButton visualB = new JButton(visualBI);
        visualB.setText("Visual");

        Icon helpBI = new ImageIcon("/Users/Chapman/Downloads/info.png");
        JButton helpB = new JButton(helpBI);
        helpB.setText("Help");

        myMenuList.add(importB);
        myMenuList.add(buildB);
        myMenuList.add(exportB);
        myMenuList.add(visualB);
        myMenuList.add(helpB);
        */

/*
        // Action Listeners
        importB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualB.setBackground(Color.gray);
                buildB.setBackground(Color.gray);
                importB.setBackground(Color.gray.darker());
                panelBoxV.removeAll();
                panelBoxV.revalidate();
                panelBoxV.repaint();
                addImportMenu(panelBoxV,visual1,panel,visual,frame);
                frame.setVisible(true);
                importedPolicy = true;
            }
        });

        buildB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualB.setBackground(Color.gray);
                importB.setBackground(Color.gray);
                buildB.setBackground(Color.gray.darker());
                panelBoxV.removeAll();
                panelBoxV.revalidate();
                panelBoxV.repaint();
                addBuildMenu(panelBoxV,visual1,panel);
                frame.setVisible(true);
            }
        });

        visualB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildB.setBackground(Color.gray);
                importB.setBackground(Color.gray);
                visualB.setBackground(Color.gray.darker());
                if (importedPolicy = true) {
                    JFrame frameNew = new JFrame();
                    frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameNew.setSize(900, 900);
                    geometry2 visualization = new geometry2(policies.get(policies.size()-1));
                    JScrollPane scroll = new JScrollPane(visualization,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    frameNew.getContentPane().add(scroll);
                    frameNew.setVisible(true);
                }
            }
        });
*/

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
        // Preparing drag and drop elements
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
                JComboBox newParty = new JComboBox(party.nameParty.toArray());
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

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand() + " <-name button & if true that same as in list->" + menuName.contains(e.getActionCommand()));
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

            if (e.getActionCommand().equals("Visual")) {
                if (importedPolicy = true) {
                    JFrame frameNew = new JFrame();
                    frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameNew.setSize(900, 900);
                    geometry2 visualization = new geometry2(policies.get(policies.size() - 1));
                    JScrollPane scroll = new JScrollPane(visualization, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    frameNew.getContentPane().add(scroll);
                    frameNew.setVisible(true);
                }
            }

            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new IconDnD().display();
    }

}