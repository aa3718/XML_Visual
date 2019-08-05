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

public class IconDnD {

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
        JFrame frame = new JFrame("ODRL Visual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        TitledBorder menu = new TitledBorder("MENU");
        menu.setTitlePosition(TitledBorder.CENTER);
        menu.setTitlePosition(TitledBorder.TOP);

        JPanel panel = new JPanel();
        JPanel panelBox = new JPanel(new GridLayout(4,1,0,0));
        JPanel panelBoxV = new JPanel();
        JPanel visual1 = new JPanel();
        JPanel visual2 = new JPanel();
        JPanel visual3 = new JPanel();
        JPanel visual = new JPanel();

        panelBox.setBounds(0, 0, 200, 150);
        panelBox.setBackground(Color.lightGray);
        panelBox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
        panelBox.setBorder(menu);

        panelBoxV.setBounds(0, 150, 200, 450);
        panelBoxV.setBackground(Color.lightGray);
        panelBoxV.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));

        visual1.setBounds(200, 0, 700, 200);
        visual1.setBackground(Color.white);
        visual1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));

        visual2.setBounds(200, 200, 700, 200);
        visual2.setBackground(Color.white);

        visual3.setBounds(200, 400, 700, 200);
        visual3.setBackground(Color.white);
        visual3.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.gray));

        visual.setBounds(200, 0, 700, 500);
        visual.setBackground(Color.white);
        visual.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));


        panel.setBounds(0, 600, 900, 100);
        panel.setBackground(Color.gray);

        frame.add(panelBoxV);
        frame.add(panelBox);
        frame.add(visual1);
        frame.add(visual2);
        frame.add(visual3);
        frame.add(visual);
        frame.add(panel);

        // Menu Buttons
        ArrayList<JButton> myMenuList = new ArrayList<JButton>();
        Icon importBI = new ImageIcon("/Users/Chapman/Downloads/import.png");
        JButton importB = new JButton(importBI);
        importB.setText("Import");

        Icon buildBI = new ImageIcon("/Users/Chapman/Downloads/tools.png");
        JButton buildB = new JButton(buildBI);
        buildB.setText("Build");

        Icon exportBI = new ImageIcon("/Users/Chapman/Downloads/share.png");
        JButton exportB = new JButton(exportBI);
        exportB.setText("Export");

        Icon helpBI = new ImageIcon("/Users/Chapman/Downloads/info.png");
        JButton helpB = new JButton(helpBI);
        helpB.setText("Help");

        myMenuList.add(importB);
        myMenuList.add(buildB);
        myMenuList.add(exportB);
        myMenuList.add(helpB);

        // Action Listeners
        importB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildB.setBackground(Color.gray);
                importB.setBackground(Color.gray.darker());
                panelBoxV.removeAll();
                panelBoxV.revalidate();
                panelBoxV.repaint();
                addImportMenu(panelBoxV,visual1,panel,visual, frame);
                frame.setVisible(true);

            }
        });

        buildB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importB.setBackground(Color.gray);
                buildB.setBackground(Color.gray.darker());
                panelBoxV.removeAll();
                panelBoxV.revalidate();
                panelBoxV.repaint();
                addBuildMenu(panelBoxV,visual1,panel);
                frame.setVisible(true);
            }
        });

        for (JButton button : myMenuList) {
            button.setMargin(new Insets(0,0,0,0));
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setBackground(Color.gray);
            button.setFocusPainted(false);
            panelBox.add(button);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setIconTextGap(20);
        }

        frame.setVisible(true);
    }

    public void callODRL(String nameFile, JPanel visual1, JPanel visual, JFrame frame) {
        testPolicyMaker parser = new testPolicyMaker();
        parser.setXmlFunction(nameFile);
    }

    public void addImportMenu(JPanel panelBoxV, JPanel visual1, JPanel panel, JPanel visual, JFrame frame) {
                JFileChooser fileChoose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int res = fileChoose.showSaveDialog(null);

                if (res == fileChoose.APPROVE_OPTION) {
                    // Accepted if the file is .txt otherwise error message
                    if (fileChoose.getSelectedFile().getAbsolutePath().endsWith("txt") || fileChoose.getSelectedFile().getAbsolutePath().endsWith("xml")) {
                        String filePath = fileChoose.getSelectedFile().getAbsolutePath();
                        System.out.println(filePath);
                        // Creates the parser and the ODRL policy constructor
                        callODRL(filePath, visual1,visual, frame);
                    } else {
                        JOptionPane errorWarning = new JOptionPane();
                        errorWarning.showMessageDialog(panel, "Could not open file", "Error", res);
                    }
                }
    }

    public void addBuildMenu(JPanel panelBoxV, JPanel visual1, JPanel panel) {
        // Preparing drag and drop elements
        Icon permissionI = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
        JButton permission = new JButton(permissionI);
        permission.setPreferredSize(new Dimension(60, 60));
        permission.setFocusPainted(false);
        panelBoxV.add(permission);
        permission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visual1.add(permission);
            }
        });

        Icon prohibitionI = new ImageIcon("/Users/Chapman/Downloads/error.png");
        JButton prohibition = new JButton(prohibitionI);
        prohibition.setPreferredSize(new Dimension(60, 60));
        prohibition.setFocusPainted(false);
        panelBoxV.add(prohibition);
        prohibition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visual1.add(prohibition);
            }
        });
    }

    public static void main(String[] args) {
        new IconDnD().display();
    }

}