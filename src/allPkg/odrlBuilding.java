package allPkg;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class odrlBuilding implements ActionListener{
    private JPanel visual;
    private JPanel panelBoxV;
    private String[] nameParty= {"assigner","assignee","attributedParty","consentingParting","informedParty","compensatedParty","trackingParty"};
    private PolicyBuilder newPolicy = new PolicyBuilder();

    odrlBuilding(JPanel panel, JPanel panelBoxV) {
       this.visual = panel;
       this.panelBoxV = panelBoxV;
    }

    public void addBuildMenu() {

        JPanel panelPP = new JPanel();
        panelPP.setBounds(10,20,200,80);
        panelPP.setBackground(Color.white);

        TitledBorder buildMenuPP = new TitledBorder("Rule");
        buildMenuPP.setTitlePosition(TitledBorder.CENTER);
        buildMenuPP.setTitlePosition(TitledBorder.BELOW_TOP);
        buildMenuPP.setTitleColor(Color.gray);

        panelPP.setBorder(buildMenuPP);
        panelBoxV.add(panelPP);

        Icon permissionI = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
        JButton permission = new JButton(permissionI);
        permission.setPreferredSize(new Dimension(35, 35));
        permission.setFocusPainted(false);
        panelPP.add(permission);
        permission.addActionListener(this);

        Icon prohibitionI = new ImageIcon("/Users/Chapman/Downloads/error.png");
        JButton prohibition = new JButton(prohibitionI);
        prohibition.setPreferredSize(new Dimension(35, 35));
        prohibition.setFocusPainted(false);
        panelPP.add(prohibition);
        prohibition.addActionListener(this);

        Icon obligationI = new ImageIcon("/Users/Chapman/Desktop/icons/obligationI.png");
        JButton obligation = new JButton(obligationI);
        obligation.setPreferredSize(new Dimension(35, 35));
        obligation.setFocusPainted(false);
        panelPP.add(obligation);
        obligation.addActionListener(this);

        JPanel panelAttr = new JPanel();
        panelAttr.setBounds(10,120,200,300);

        panelAttr.setLayout(new BoxLayout(panelAttr,BoxLayout.Y_AXIS));
        panelAttr.setBackground(Color.white);

        TitledBorder buildMenuAttr = new TitledBorder("Elements");
        buildMenuAttr.setTitlePosition(TitledBorder.CENTER);
        buildMenuAttr.setTitlePosition(TitledBorder.BELOW_TOP);
        buildMenuAttr.setTitleColor(Color.gray);

        panelAttr.setBorder(buildMenuAttr);
        panelBoxV.add(panelAttr);

        JButton asset = new JButton("Asset");
        asset.setMaximumSize(new Dimension(140,35));
        asset.setFocusPainted(false);
        panelAttr.add(asset);
        asset.addActionListener(this);

        JButton action = new JButton("Action");
        action.setMaximumSize(new Dimension(140, 35));
        action.setFocusPainted(false);
        panelAttr.add(action);
        action.addActionListener(this);

        JButton party = new JButton("Party");
        party.setMaximumSize(new Dimension(140, 35));
        party.setFocusPainted(false);
        panelAttr.add(party);
        party.addActionListener(this);

        JButton constraint = new JButton("Constraint");
        constraint.setMaximumSize(new Dimension(140, 35));
        constraint.setFocusPainted(false);
        panelAttr.add(constraint);
        constraint.addActionListener(this);

        JButton duty = new JButton("Duty");
        duty.setMaximumSize(new Dimension(140, 35));
        duty.setFocusPainted(false);
        panelAttr.add(duty);
        duty.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {



    }

}
