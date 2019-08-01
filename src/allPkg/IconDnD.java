package allPkg;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.awt.dnd.*;

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

        JPanel panel = new JPanel();
        JPanel panelBox = new JPanel(new GridLayout(2,1,0,0));
        JPanel panelBoxV = new JPanel();
        JPanel visual1 = new JPanel();
        JPanel visual2 = new JPanel();
        JPanel visual3 = new JPanel();

        panelBox.setBounds(0, 0, 200, 100);
        panelBox.setBackground(Color.lightGray);
        panelBox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));

        panelBoxV.setBounds(0, 100, 200, 500);
        panelBoxV.setBackground(Color.lightGray);
        panelBoxV.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));

        visual1.setBounds(200, 0, 700, 200);
        visual1.setBackground(Color.white);

        visual2.setBounds(200, 200, 700, 200);
        visual2.setBackground(Color.pink);

        visual3.setBounds(200, 400, 700, 200);
        visual3.setBackground(Color.green);

        panel.setBounds(0, 600, 900, 100);
        panel.setBackground(Color.blue);

        frame.add(panelBoxV);
        frame.add(panelBox);
        frame.add(visual1);
        frame.add(visual2);
        frame.add(visual3);
        frame.add(panel);

        // Dimension for menu buttons
        /*
        Dimension buttonDimension = new Dimension();
        buttonDimension.height = 40;
        buttonDimension.width = 200;
        */

        Icon importBI = new ImageIcon("/Users/Chapman/Downloads/import.png");
        JButton importB = new JButton(importBI);
        importB.setText("Import");
        //importB.setPreferredSize(buttonDimension);
        importB.setMargin(new Insets(0,0,0,0));
        importB.setOpaque(true);
        importB.setBorderPainted(false);
        importB.setBackground(Color.gray);
        importB.setFocusPainted(false);

        Icon buildBI = new ImageIcon("/Users/Chapman/Downloads/tools.png");
        JButton buildB = new JButton(buildBI);
        buildB.setText("Build");
        //buildB.setPreferredSize(buttonDimension);
        buildB.setMargin(new Insets(0,0,0,0));
        buildB.setOpaque(true);
        buildB.setBorderPainted(false);
        buildB.setBackground(Color.gray);

        // Action Listeners
        importB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildB.setBackground(Color.gray);
                importB.setBackground(Color.gray.darker());
                panelBoxV.removeAll();
                panelBoxV.revalidate();
                panelBoxV.repaint();
                addImportMenu(panelBoxV,visual1,panel);
                frame.setVisible(true);

            }
        });
        panelBox.add(importB);

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

        panelBox.add(buildB);
        frame.setVisible(true);

    }

    public void callODRL(String nameFile, JPanel visual) {
        TestParse parser = new TestParse();
        parser.setXmlFunction(nameFile, visual);
        System.out.println("test");
    }

    public void addImportMenu(JPanel panelBoxV, JPanel visual, JPanel panel) {
        // Manual copy paste xml text box
        JTextField inputXML = new JTextField();
        inputXML.setText("Input ODRL xml here.");
        inputXML.setForeground(Color.gray.brighter());
        inputXML.setPreferredSize(new Dimension(150, 150));
        panelBoxV.add(inputXML);

        // User attaches XML .txt file
        JButton addFile = new JButton("Attach XML .txt file");
        panelBoxV.add(addFile);
        addFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChoose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int res = fileChoose.showSaveDialog(null);

                if (res == fileChoose.APPROVE_OPTION) {
                    // Accepted if the file is .txt otherwise error message
                    if (fileChoose.getSelectedFile().getAbsolutePath().endsWith("txt") || fileChoose.getSelectedFile().getAbsolutePath().endsWith("xml")) {
                        String filePath = fileChoose.getSelectedFile().getAbsolutePath();
                        System.out.println(filePath);
                        // Creates the parser and the ODRL policy constructor
                        callODRL(filePath, visual);
                    } else {
                        JOptionPane errorWarning = new JOptionPane();
                        errorWarning.showMessageDialog(panel, "Could not open file", "Error", res);
                    }
                }
            }
        });
    }

    public void addBuildMenu(JPanel panelBoxV, JPanel visual, JPanel panel) {
        // Preparing drag and drop elements
        Icon permissionI = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
        JButton permission = new JButton(permissionI);
        permission.setPreferredSize(new Dimension(60, 60));
        permission.setFocusPainted(false);
        panelBoxV.add(permission);
        permission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visual.add(permission);
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
                visual.add(prohibition);
            }
        });
    }

    public static void main(String[] args) {
        new IconDnD().display();
    }

}