package allPkg;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;

public class IconDnD {

    private void display() {

        JFrame frameIntro = new JFrame("ODRL Visual");
        frameIntro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameIntro.setSize(900, 700);
        JButton start = new JButton("Start: ODRL Visualizer");
        JTextField field = new JTextField(10);
        field.setText("ODRL Visualization Tool");
        start.setBounds(50, 100, 95, 30);
        start.setBackground(Color.pink.brighter());
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameIntro.setVisible(false);
                display2();
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
        JPanel panelBox = new JPanel();
        JPanel visual = new JPanel();

        panelBox.setBounds(0, 0, 200, 600);
        panelBox.setBackground(Color.pink.brighter());
        panelBox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray.brighter()));

        visual.setBounds(200, 0, 700, 600);
        visual.setBackground(Color.white);

        panel.setBounds(0, 0, 900, 700);
        panel.setBackground(Color.lightGray);

        JTextField textField = new JTextField(10);
        textField.setText("ODRL elements");
        panelBox.add(textField);
        frame.add(panelBox);
        frame.add(visual);
        frame.add(panel);
        frame.setVisible(true);

        // Preparing drag and drop elements
        Icon permissionI = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
        JButton permission = new JButton(permissionI);
        permission.setPreferredSize(new Dimension(60, 60));
        panelBox.add(permission);
        permission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visual.add(permission);
            }
        });

        // Manual copy paste xml text box
        JTextField inputXML = new JTextField();
        inputXML.setText("Input ODRL xml here.");
        inputXML.setForeground(Color.gray.brighter());
        inputXML.setPreferredSize(new Dimension(150, 150));
        panelBox.add(inputXML);

        // User attaches XML .txt file
        JButton addFile = new JButton("Attach XML .txt file");
        panelBox.add(addFile);
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

    public void callODRL(String nameFile, JPanel visual) {
        TestParse parser = new TestParse();
        parser.setXmlFunction(nameFile, visual);
    }

    public static void main(String[] args) {
        new IconDnD().display();
    }

}