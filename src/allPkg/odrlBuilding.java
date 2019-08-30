package allPkg;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public class odrlBuilding implements ActionListener{
    private Policy policy;
    private ArrayList<Policy> policies;
    private JPanel visual;
    private JPanel panelBoxV;
    private JPanel globalRulePanel;
    private ArrayList<String> nameAction = new ArrayList<String>();
    private ArrayList<String> nameParty = new ArrayList<String>();
    private PolicyBuilder newPolicy = new PolicyBuilder();
    private ArrayList<JButton> elementMenu = new ArrayList<JButton>();
    private ArrayList<String> elementMenuName = new ArrayList<String>();
    private ArrayList<JPanel> rulePanels = new ArrayList<JPanel>();
    private boolean addAsset = false;
    private boolean addAction = false;
    private boolean addConstraint = false;
    private boolean addParty = false;
    private boolean addDuty = false;
    private ArrayList<String> nameOperators = new ArrayList<String>();
    private ArrayList<String> nameLeftOperand = new ArrayList<String>();
    private int count = 0;
    private int refinementCount = 0;
    private ArrayList<Rules> allRules = new ArrayList<Rules>();
    private ArrayList<attributeHolders> globalAttributes = new ArrayList<attributeHolders>();
    private Hashtable<String,Rules> mappingNumberToRule = new Hashtable<String,Rules>();
    private Hashtable<String,JPanel> mappingNumberToPanel = new Hashtable<String,JPanel>();


    odrlBuilding(JPanel panel, JPanel panelBoxV, ArrayList<Policy> policies) {
        this.policies = policies;
        this.visual = panel;
        this.panelBoxV = panelBoxV;
        buildAction();
        buildParty();
        buildOperators();
        buildLeftOperand();
    }

    public void addBuildMenu() {

        // Adding Policy Menu Element
        JPanel panelPolicy = new JPanel();
        panelPolicy.setLayout(new BoxLayout(panelPolicy,BoxLayout.Y_AXIS));
        panelPolicy.setBounds(10,20,200,80);
        panelPolicy.setBackground(Color.white);

        TitledBorder buildMenuPolicy = new TitledBorder("Global Elements");
        buildMenuPolicy.setTitlePosition(TitledBorder.CENTER);
        buildMenuPolicy.setTitlePosition(TitledBorder.BELOW_TOP);
        buildMenuPolicy.setTitleColor(Color.gray);

        JButton globalElement = new JButton("Add");
        globalElement.setFocusPainted(false);
        globalElement.addActionListener(this);
        globalElement.setActionCommand("addGlobal");

        panelPolicy.setBorder(buildMenuPolicy);
        panelPolicy.add(globalElement);
        panelBoxV.add(panelPolicy);

        // Adding Rule Menu Element
        JPanel panelPP = new JPanel();
        panelPP.setBounds(10,130,200,80);
        panelPP.setBackground(Color.white);

        TitledBorder buildMenuPP = new TitledBorder("Rule");
        buildMenuPP.setTitlePosition(TitledBorder.CENTER);
        buildMenuPP.setTitlePosition(TitledBorder.BELOW_TOP);
        buildMenuPP.setTitleColor(Color.gray);

        panelPP.setBorder(buildMenuPP);
        panelBoxV.add(panelPP);

        Icon permissionI = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
        JButton permission = new JButton(permissionI);
        permission.setActionCommand("permission");
        permission.setPreferredSize(new Dimension(35, 35));
        permission.setFocusPainted(false);
        panelPP.add(permission);
        permission.addActionListener(this);

        Icon prohibitionI = new ImageIcon("/Users/Chapman/Downloads/error.png");
        JButton prohibition = new JButton(prohibitionI);
        prohibition.setActionCommand("prohibition");
        prohibition.setPreferredSize(new Dimension(35, 35));
        prohibition.setFocusPainted(false);
        panelPP.add(prohibition);
        prohibition.addActionListener(this);

        Icon obligationI = new ImageIcon("/Users/Chapman/Desktop/icons/obligationI.png");
        JButton obligation = new JButton(obligationI);
        obligation.setActionCommand("obligation");
        obligation.setPreferredSize(new Dimension(35, 35));
        obligation.setFocusPainted(false);
        panelPP.add(obligation);
        obligation.addActionListener(this);

        // Adding Element Menu Element
        JPanel panelAttr = new JPanel();
        panelAttr.setBounds(10,240,200,200);

        panelAttr.setLayout(new BoxLayout(panelAttr,BoxLayout.Y_AXIS));
        panelAttr.setBackground(Color.white);

        TitledBorder buildMenuAttr = new TitledBorder("Elements");
        buildMenuAttr.setTitlePosition(TitledBorder.CENTER);
        buildMenuAttr.setTitlePosition(TitledBorder.BELOW_TOP);
        buildMenuAttr.setTitleColor(Color.gray);

        panelAttr.setBorder(buildMenuAttr);
        panelBoxV.add(panelAttr);

        elementMenuName.add("Asset");
        elementMenuName.add("Action");
        elementMenuName.add("Party");
        elementMenuName.add("Constraint");
        elementMenuName.add("Duty");

        for (int i = 0; i < elementMenuName.size(); i++) {
            JButton menuElementButton = new JButton(elementMenuName.get(i));
            menuElementButton.setMaximumSize(new Dimension(140, 35));
            menuElementButton.setActionCommand("menuElementButton" + i);
            menuElementButton.setOpaque(true);
            menuElementButton.setFocusPainted(false);
            panelAttr.add(menuElementButton);
            menuElementButton.addActionListener(this);
            elementMenu.add(menuElementButton);
        }

        JPanel panelDone = new JPanel();
        panelDone.setBounds(10,490,200,150);
        panelDone.setBackground(Color.white);

        JButton done = new JButton("Done");
        done.setOpaque(true);
        done.setFocusPainted(false);
        done.addActionListener(this);
        done.setActionCommand("done");

        panelDone.add(done);
        panelBoxV.add(panelDone);
    }

    public void addGlobalElements() {

        globalRulePanel = new JPanel();
        globalRulePanel.setBackground(Color.darkGray);
        globalRulePanel.setBorder(BorderFactory.createLineBorder(Color.white));
        globalRulePanel.setPreferredSize(new Dimension(350,500));
        globalRulePanel.setLayout(new BoxLayout(globalRulePanel,BoxLayout.Y_AXIS));

        JLabel globalRuleLabel = new JLabel("Global Elements");
        globalRuleLabel.setForeground(Color.white);
        globalRuleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        globalRulePanel.add(globalRuleLabel);

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel,BoxLayout.X_AXIS));
        editPanel.setBackground(Color.darkGray);

        Icon selectIcon = new ImageIcon("/Users/Chapman/Desktop/icons/select.png");
        JButton select = new JButton(selectIcon);
        select.setBackground(Color.darkGray);

        select.setActionCommand("globalS");
        select.setMaximumSize(new Dimension(40, 40));
        select.setFocusPainted(false);
        select.setOpaque(true);
        select.addActionListener(this);
        editPanel.add(select);

        Icon removeIcon = new ImageIcon("/Users/Chapman/Desktop/icons/delete.png");
        JButton remove = new JButton(removeIcon);
        remove.setActionCommand("globalR");
        remove.setBackground(Color.darkGray);
        remove.setMaximumSize(new Dimension(40, 40));
        remove.setFocusPainted(false);
        remove.setOpaque(true);
        remove.addActionListener(this);
        editPanel.add(remove);

        editPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        globalRulePanel.add(editPanel);
        visual.add(globalRulePanel);
        visual.revalidate();
        visual.repaint();
    }

    public void addDutyElement(String number, attributeHolders element) {

        JPanel rulePanel = new JPanel();
        rulePanel.setBackground(Color.white);
        rulePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        rulePanel.setLayout(new BoxLayout(rulePanel,BoxLayout.Y_AXIS));

        JLabel ruleLabel = new JLabel("Duty");
        ruleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        rulePanel.add(ruleLabel);

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel,BoxLayout.X_AXIS));

        Icon selectIcon = new ImageIcon("/Users/Chapman/Desktop/icons/select.png");
        JButton select = new JButton(selectIcon);
        select.setActionCommand("select"+count);
        select.setBackground(Color.white);
        select.setMaximumSize(new Dimension(40, 40));
        select.setFocusPainted(false);
        select.setOpaque(true);
        select.addActionListener(this);
        editPanel.add(select);

        mappingNumberToPanel.get(number).add(rulePanel);
        mappingNumberToRule.put(Integer.toString(count),(Duty) element);
        mappingNumberToPanel.put(Integer.toString(count),rulePanel);

        //mappingSelectToRule.put("select"+count,(Duty) element);

        editPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        rulePanel.add(editPanel);
        //rulePanels.add(rulePanel);

        count++;
    }

    public void addAssetElement(String number, attributeHolders element, boolean isGlobal) {
        JPanel elementPanel = new JPanel();
        elementPanel.setLayout(new BoxLayout(elementPanel,BoxLayout.X_AXIS));

        JTextField assetName = new JTextField();
        assetName.setBackground(Color.white);
        assetName.setMaximumSize(new Dimension(140, 35));
        assetName.setText("Asset UID");

        JButton getAssetName = new JButton("Set");
        getAssetName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAssetName.setBackground(Color.lightGray);
                element.setFullAttribute("uid",assetName.getText());
                print();
            }
        });
        assetName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAssetName.setBackground(Color.white);
            }
        });

        Icon selectIcon = new ImageIcon("/Users/Chapman/Desktop/icons/select.png");

        /*
        JButton select = new JButton(selectIcon);
        select.setActionCommand("select"+rulePanelNumber);
        select.setMaximumSize(new Dimension(40, 40));
        select.setFocusPainted(false);
        select.setOpaque(true);
        select.addActionListener(this);
*/
        if (isGlobal) {
            elementPanel.setBackground(Color.darkGray);
            //select.setBackground(Color.darkGray);
            getAssetName.setBackground(Color.darkGray);
        } else {
            elementPanel.setBackground(Color.white);
            //select.setBackground(Color.white);
            getAssetName.setBackground(Color.white);
        }

        elementPanel.add(assetName);
        elementPanel.add(getAssetName);
        //elementPanel.add(select);
        elementPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (!isGlobal) {
            mappingNumberToPanel.get(number).add(elementPanel);
        } else {
            globalRulePanel.add(elementPanel);
        }

        //mappingSelectToElement.put("selRefinement"+refinementCount,element);
        //refinementCount++;
    }

    public void addActionElement(String number, attributeHolders element,boolean isGlobal) {
        JPanel elementPanel = new JPanel();
        elementPanel.setLayout(new BoxLayout(elementPanel,BoxLayout.X_AXIS));

        JLabel actionLabel = new JLabel("Action type:");

        JComboBox actionName = new JComboBox(nameAction.toArray());
        actionName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = "http://www.w3.org/ns/odrl/2/" + actionName.getSelectedItem();
                element.setFullAttribute("name",value);
                System.out.println((String) actionName.getSelectedItem() + "<- Name of action");
            }
        });

        if (isGlobal) {
            elementPanel.setBackground(Color.darkGray);
            actionLabel.setBackground(Color.darkGray);
            actionName.setBackground(Color.darkGray);
        } else {
            elementPanel.setBackground(Color.white);
            actionLabel.setBackground(Color.white);
            actionName.setBackground(Color.white);
        }

        elementPanel.setMaximumSize(new Dimension(300, 35));
        elementPanel.add(actionLabel);
        elementPanel.add(actionName);
        elementPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (!isGlobal) {
            mappingNumberToPanel.get(number).add(elementPanel);
        } else {
            globalRulePanel.add(elementPanel);
        }

        //mappingSelectToElement.put("selRefinement"+refinementCount,element);
        //refinementCount++;
    }

    public void addPartyElement(String number, attributeHolders element, boolean isGlobal) {

        JPanel elementPanel = new JPanel();
        elementPanel.setLayout(new BoxLayout(elementPanel,BoxLayout.X_AXIS));

        JTextField partyName = new JTextField();
        partyName.setBackground(Color.white);
        partyName.setMaximumSize(new Dimension(140, 35));
        partyName.setText("Party UID");

        JButton getPartyName = new JButton("Set");
        getPartyName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setFullAttribute("uid", partyName.getText());
                print();
            }
        });

        elementPanel.setMaximumSize(new Dimension(300, 35));
        elementPanel.add(partyName);
        elementPanel.add(getPartyName);
        elementPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel elementPanelParty = new JPanel();

        JLabel partyLabel = new JLabel("Party function:");

        JComboBox partyFunction = new JComboBox(nameParty.toArray());
        partyFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = "http://www.w3.org/ns/odrl/2/" + partyFunction.getSelectedItem();
                element.setFullAttribute("function",value);
                System.out.println((String) partyFunction.getSelectedItem() + "<- Name of action");
            }
        });

        if (isGlobal) {
            elementPanel.setBackground(Color.darkGray);
            getPartyName.setBackground(Color.darkGray);
            elementPanelParty.setBackground(Color.darkGray);
            partyLabel.setBackground(Color.darkGray);
            partyFunction.setBackground(Color.darkGray);
        } else {
            elementPanel.setBackground(Color.white);
            getPartyName.setBackground(Color.white);
            elementPanelParty.setBackground(Color.white);
            partyLabel.setBackground(Color.white);
            partyFunction.setBackground(Color.white);
        }

        elementPanelParty.setMaximumSize(new Dimension(300, 35));
        elementPanelParty.add(partyLabel);
        elementPanelParty.add(partyFunction);
        elementPanelParty.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (!isGlobal) {
            mappingNumberToPanel.get(number).add(elementPanel);
            mappingNumberToPanel.get(number).add(elementPanelParty);
        } else {
            globalRulePanel.add(elementPanel);
            globalRulePanel.add(elementPanelParty);
        }

        //mappingSelectToElement.put("selRefinement"+refinementCount,element);
        //refinementCount++;
    }

    public void addConstraintElement(String number, attributeHolders element, boolean isGlobal, boolean isRefinement) {

        JPanel elementPanel = new JPanel();
        elementPanel.setLayout(new BoxLayout(elementPanel,BoxLayout.Y_AXIS));

        JPanel elementPanelConstraintName = new JPanel();

        elementPanelConstraintName.setLayout(new BoxLayout(elementPanelConstraintName,BoxLayout.X_AXIS));

        JLabel constraintLabelName = new JLabel("Constraint name:");

        JComboBox constraintFunctionName = new JComboBox(nameLeftOperand.toArray());
        constraintFunctionName.setMaximumSize(new Dimension(240, 35));
        constraintFunctionName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = "http://www.w3.org/ns/odrl/2/" + constraintFunctionName.getSelectedItem();
                element.setFullAttribute("name",value);
                System.out.println((String) constraintFunctionName.getSelectedItem() + "<- Name operand");
            }
        });

        elementPanelConstraintName.add(constraintLabelName);
        elementPanelConstraintName.add(constraintFunctionName);
        elementPanelConstraintName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel elementPanelConstraintOperand = new JPanel();
        elementPanelConstraintOperand.setLayout(new BoxLayout(elementPanelConstraintOperand,BoxLayout.X_AXIS));

        JLabel constraintLabelOperand = new JLabel("Constraint operand:");

        JComboBox constraintFunctionOperand = new JComboBox(nameOperators.toArray());
        constraintFunctionOperand.setMaximumSize(new Dimension(140, 35));
        constraintFunctionOperand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = "http://www.w3.org/ns/odrl/2/" + constraintFunctionOperand.getSelectedItem();
                element.setFullAttribute("operator",value);
                System.out.println((String) constraintFunctionOperand.getSelectedItem() + "<- Name of action");
            }
        });

        elementPanelConstraintOperand.add(constraintLabelOperand);
        elementPanelConstraintOperand.add(constraintFunctionOperand);
        elementPanelConstraintOperand.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel constraintRightPanel = new JPanel();
        constraintRightPanel.setLayout(new BoxLayout(constraintRightPanel, BoxLayout.X_AXIS));

        JTextField elementPanelConstraintRight = new JTextField();
        elementPanelConstraintRight.setMaximumSize(new Dimension(140, 35));
        elementPanelConstraintRight.setText("Constraint value");
        JButton getConstraintRight = new JButton("Set");
        elementPanelConstraintRight.setBackground(Color.white);
        getConstraintRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setFullAttribute("rightOperand", elementPanelConstraintRight.getText());
                print();
            }
        });

        if (isGlobal) {
            elementPanel.setBackground(Color.darkGray);
            elementPanelConstraintName.setBackground(Color.darkGray);
            constraintLabelName.setBackground(Color.darkGray);
            constraintFunctionName.setBackground(Color.darkGray);
            elementPanelConstraintOperand.setBackground(Color.darkGray);
            constraintLabelOperand.setBackground(Color.darkGray);
            constraintFunctionOperand.setBackground(Color.darkGray);
            constraintRightPanel.setBackground(Color.darkGray);
            getConstraintRight.setBackground(Color.darkGray);
        } else {
            elementPanel.setBackground(Color.white);
            elementPanelConstraintName.setBackground(Color.white);
            constraintLabelName.setBackground(Color.white);
            constraintFunctionName.setBackground(Color.white);
            elementPanelConstraintOperand.setBackground(Color.white);
            constraintLabelOperand.setBackground(Color.white);
            constraintFunctionOperand.setBackground(Color.white);
            constraintRightPanel.setBackground(Color.white);
            getConstraintRight.setBackground(Color.white);
        }

        constraintRightPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        constraintRightPanel.add(elementPanelConstraintRight);
        constraintRightPanel.add(getConstraintRight);

        elementPanel.add(elementPanelConstraintName);
        elementPanel.add(elementPanelConstraintOperand);
        elementPanel.add(constraintRightPanel);
        elementPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (!isGlobal) {
            mappingNumberToPanel.get(number).add(elementPanel);
        } else {
            globalRulePanel.add(elementPanel);
        }

    }

    public void addRule(Rules rule) {

        JPanel rulePanel = new JPanel();
        rulePanel.setBackground(Color.white);
        rulePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        rulePanel.setPreferredSize(new Dimension(350,700));
        rulePanel.setLayout(new BoxLayout(rulePanel,BoxLayout.Y_AXIS));

        JLabel ruleLabel;
        if (rule instanceof Permission) {
            ruleLabel = new JLabel("Permission");

        } else if (rule instanceof Prohibition) {
            ruleLabel = new JLabel("Prohibition");

        } else {
            ruleLabel = new JLabel("Obligation");
        }
        ruleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rulePanel.add(ruleLabel);

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel,BoxLayout.X_AXIS));

        Icon selectIcon = new ImageIcon("/Users/Chapman/Desktop/icons/select.png");
        JButton select = new JButton(selectIcon);
        select.setActionCommand("select"+count);
        select.setBackground(Color.white);
        select.setMaximumSize(new Dimension(40, 40));
        select.setFocusPainted(false);
        select.setOpaque(true);
        select.addActionListener(this);
        editPanel.add(select);

        Icon removeIcon = new ImageIcon("/Users/Chapman/Desktop/icons/delete.png");
        JButton remove = new JButton(removeIcon);
        remove.setActionCommand("remove"+count);
        remove.setBackground(Color.white);
        remove.setMaximumSize(new Dimension(40, 40));
        remove.setFocusPainted(false);
        remove.setOpaque(true);
        remove.addActionListener(this);
        editPanel.add(remove);

        mappingNumberToPanel.put(Integer.toString(count),rulePanel);
        mappingNumberToRule.put(Integer.toString(count),rule);


        editPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        rulePanel.add(editPanel);

        visual.add(rulePanel);
        visual.revalidate();
        visual.repaint();
        count++;
    }

    public void print() {
        //System.out.println(mappingSelectToRule.get("select" + 0).getAsset().get(0).getUID());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().contains("menuElementButton")) {
            for (int i = 0; i < elementMenu.size(); i++) {
                if (e.getActionCommand().equals("menuElementButton" + i)) {
                    elementMenu.get(i).setForeground(Color.white);
                    switch (i) {
                        case 0:
                            addAsset = true;
                            break;
                        case 1:
                            addAction = true;
                            break;
                        case 2:
                            addParty = true;
                            break;
                        case 3:
                            addConstraint = true;
                            break;
                        case 4:
                            addDuty = true;
                            break;
                    }
                } else {
                    elementMenu.get(i).setForeground(Color.black);
                    switch (i) {
                        case 0:
                            addAsset = false;
                            break;
                        case 1:
                            addAction = false;
                            break;
                        case 2:
                            addParty = false;
                            break;
                        case 3:
                            addConstraint = false;
                            break;
                        case 4:
                            addDuty = false;
                            break;
                    }
                }
            }
            return;
        }

        if (e.getActionCommand().equals("addGlobal")) {
            addGlobalElements();
            return;
        }

        if (e.getActionCommand().equals("permission")) {
            Permission permission = new Permission();
            allRules.add(permission);
            addRule(permission);
            return;
        }

        if (e.getActionCommand().equals("prohibition")) {
            Prohibition prohibition = new Prohibition();
            allRules.add(prohibition);
            addRule(prohibition);
            return;
        }

        if (e.getActionCommand().equals("obligation")) {
            Obligation obligation = new Obligation();
            allRules.add(obligation);
            addRule(obligation);
            return;
        }

        if (e.getActionCommand().contains("remove")) {
            String number = e.getActionCommand().substring(6);
            visual.remove(mappingNumberToPanel.get(number));
            mappingNumberToPanel.remove(number);

            allRules.remove(mappingNumberToRule.get(number));
            mappingNumberToRule.remove(number);
            visual.revalidate();
            visual.repaint();
            return;
        }

        if (e.getActionCommand().equals("globalS")) {
            if (addAction) {
                Action action = new Action();
                globalAttributes.add(action);
                addActionElement("", action,true);
            } else if (addAsset) {
                Asset asset = new Asset();
                globalAttributes.add(asset);
                addAssetElement("", asset,true);
            } else if (addParty) {
                Party party = new Party();
                globalAttributes.add(party);
                addPartyElement("", party,true);
            } else if (addConstraint){
                Constraint constraint = new Constraint();
                globalAttributes.add(constraint);
                addConstraintElement("", constraint,true,false);
            }
            visual.revalidate();
            visual.repaint();
            return;
        }

        if (e.getActionCommand().equals("globalR")) {
            visual.remove(globalRulePanel);
            visual.revalidate();
            visual.repaint();
            for (int i = 0; i < globalAttributes.size(); i++) {
                globalAttributes.clear();
            }
            return;
        }

        if (e.getActionCommand().contains("select")) {
            String number = e.getActionCommand().substring(6);
            if (addAction) {
                Action action = new Action();
                mappingNumberToRule.get(number).setAction(action);
                addActionElement(number, action, false);
            } else if (addAsset) {
                Asset asset = new Asset();
                mappingNumberToRule.get(number).setAsset(asset);
                addAssetElement(number, asset, false);
            } else if (addParty) {
                Party party = new Party();
                mappingNumberToRule.get(number).setParty(party);
                addPartyElement(number, party, false);
            } else if (addConstraint) {
                Constraint constraint = new Constraint();
                mappingNumberToRule.get(number).setConstraint(constraint);
                addConstraintElement(number, constraint, false, false);
            } else if (addDuty) {
                Duty duty = new Duty();
                mappingNumberToRule.get(number).setDuty(duty);
                addDutyElement(number, duty);
            }
            visual.revalidate();
            visual.repaint();
            return;
        }

        if (e.getActionCommand().equals("done")) {
            for (int i = 0; i < allRules.size(); i++) {
                for (int j = 0; j < globalAttributes.size(); j++) {
                    if (globalAttributes.get(j) instanceof Constraint) {
                        allRules.get(i).setConstraint((Constraint) globalAttributes.get(j));
                    }
                    if (globalAttributes.get(j) instanceof Asset) {
                        allRules.get(i).setAsset((Asset) globalAttributes.get(j));
                    }
                    if (globalAttributes.get(j) instanceof Action) {
                        allRules.get(i).setAction((Action) globalAttributes.get(j));
                    }
                    if (globalAttributes.get(j) instanceof Party) {
                        allRules.get(i).setParty((Party) globalAttributes.get(j));
                    }
                }

                if (allRules.get(i) instanceof Permission) {
                    newPolicy.withPermission((Permission) allRules.get(i));
                }
                if (allRules.get(i) instanceof Prohibition) {
                    newPolicy.withProhibition((Prohibition) allRules.get(i));
                }
                if (allRules.get(i) instanceof Obligation) {
                    newPolicy.withObligation((Obligation) allRules.get(i));
                }

                // Constraints
                for (int k = 0; k < allRules.get(i).getConstraint().size(); k++) {
                    newPolicy.withConstraint(allRules.get(i).getConstraint().get(k));
                }

                // Assets
                for (int k = 0; k < allRules.get(i).getAsset().size(); k++) {
                    newPolicy.withAsset(allRules.get(i).getAsset().get(k));
                }

                // Parties
                for (int k = 0; k < allRules.get(i).getParty().size(); k++) {
                    newPolicy.withParty(allRules.get(i).getParty().get(k));
                }

                // Actions
                for (int j = 0; j < allRules.get(i).getAction().size(); j++) {
                    newPolicy.withAction(allRules.get(i).getAction().get(j));
                    allRules.get(i).getAction().get(j).setParentType(allRules.get(i));
                }

                // Duties
                for (int k = 0; k < allRules.get(i).getDuty().size(); k++) {
                    newPolicy.withDuty(allRules.get(i).getDuty().get(k));
                    setAllElements(allRules.get(i).getDuty().get(k));
                }

            }

            policy = newPolicy.build();

            //System.out.println(policy.getObligation(0).getAction() + "<-obligation action there");

            policies.add(policy);
            visual.removeAll();
            visual.revalidate();
            visual.repaint();
        }

    }


    public void setAllElements(Rules rule) {
        // Constraints
        for (int k = 0; k < rule.getConstraint().size(); k++) {
            newPolicy.withConstraint(rule.getConstraint().get(k));
        }

        //Asset
        for (int k = 0; k < rule.getAsset().size(); k++) {
            newPolicy.withAsset(rule.getAsset().get(k));
        }

        // Party
        for (int k = 0; k < rule.getParty().size(); k++) {
            newPolicy.withParty(rule.getParty().get(k));
        }

        // Actions
        for (int j = 0; j < rule.getAction().size(); j++) {
            newPolicy.withAction(rule.getAction().get(j));
            rule.getAction().get(j).setParentType(rule);
        }

        // Duty
        for (int k = 0; k < rule.getDuty().size(); k++) {
            newPolicy.withDuty(rule.getDuty().get(k));
            setAllElements(rule.getDuty().get(k));
        }
    }


    public void buildAction() {
        nameAction.add("Attribute");
        nameAction.add("CommercialUse"); //
        nameAction.add("DerivativeWorks");
        nameAction.add("Distribution");
        nameAction.add("Notice");
        nameAction.add("Reproduction");
        nameAction.add("ShareAlike");
        nameAction.add("Sharing");
        nameAction.add("SourceCode");
        nameAction.add("acceptTracking"); //
        nameAction.add("adHocShare");
        nameAction.add("aggregate");
        nameAction.add("annotate"); //
        nameAction.add("anonymize"); //
        nameAction.add("append"); //
        nameAction.add("appendTo"); //
        nameAction.add("archive");
        nameAction.add("attachPolicy");
        nameAction.add("attachSource");
        nameAction.add("attribute");
        nameAction.add("commercialize");
        nameAction.add("compensate"); //
        nameAction.add("concurrentUse");
        nameAction.add("copy"); //
        nameAction.add("delete"); //
        nameAction.add("derive"); //
        nameAction.add("digitize");  //
        nameAction.add("display");
        nameAction.add("distribute");
        nameAction.add("ensureExclusivity");
        nameAction.add("execute");
        nameAction.add("export");
        nameAction.add("extract");
        nameAction.add("extractChar");
        nameAction.add("extractPage");
        nameAction.add("extractWord");
        nameAction.add("give");
        nameAction.add("grantUse");
        nameAction.add("include");
        nameAction.add("index");
        nameAction.add("inform");
        nameAction.add("install");
        nameAction.add("lease");
        nameAction.add("lend"); //
        nameAction.add("license");
        nameAction.add("modify");
        nameAction.add("move");
        nameAction.add("nextPolicy");
        nameAction.add("obtainConsent");
        nameAction.add("pay");
        nameAction.add("play");
        nameAction.add("present");
        nameAction.add("preview");//
        nameAction.add("print");
        nameAction.add("read");
        nameAction.add("reproduce");
        nameAction.add("reviewPolicy");
        nameAction.add("secondaryUse");
        nameAction.add("sell");
        nameAction.add("share");
        nameAction.add("shareAlike");
        nameAction.add("stream");
        nameAction.add("synchronize");
        nameAction.add("textToSpeech");
        nameAction.add("transfer");
        nameAction.add("transform");
        nameAction.add("translate");
        nameAction.add("uninstall");
        nameAction.add("use");
        nameAction.add("watermark");
        nameAction.add("write");
        nameAction.add("writeTo");
    }

    public void buildParty() {
        nameParty.add("assigner");
        nameParty.add("assignee");
        nameParty.add("attributedParty");
        nameParty.add("consentingParting");
        nameParty.add("informedParty");
        nameParty.add("compensatedParty");
        nameParty.add("trackingParty");
    }

    public void buildOperators() {
        nameOperators.add("eq");
        nameOperators.add("gt");
        nameOperators.add("gteq");
        nameOperators.add("hasPart");
        nameOperators.add("isA");
        nameOperators.add("isAllOf");
        nameOperators.add("isAnyOf");
        nameOperators.add("isNoneOf");
        nameOperators.add("isPartOf");
        nameOperators.add("It");
        nameOperators.add("Iteq");
        nameOperators.add("neq");

    }

    public void buildLeftOperand() {
        nameLeftOperand.add("absolutePosition");
        nameLeftOperand.add("absoluteSize");
        nameLeftOperand.add("absoluteSpatialPosition");
        nameLeftOperand.add("absoluteTemporalPosition");
        nameLeftOperand.add("count");
        nameLeftOperand.add("dateTime");
        nameLeftOperand.add("delayPeriod");
        nameLeftOperand.add("deliveryChannel");
        nameLeftOperand.add("device");
        nameLeftOperand.add("elapsedTime");
        nameLeftOperand.add("event");
        nameLeftOperand.add("fileFormat");
        nameLeftOperand.add("industry");
        nameLeftOperand.add("language");
        nameLeftOperand.add("media");
        nameLeftOperand.add("meteredTime");
        nameLeftOperand.add("payAmount");
        nameLeftOperand.add("percentage");
        nameLeftOperand.add("product");
        nameLeftOperand.add("purpose");
        nameLeftOperand.add("recipient");
        nameLeftOperand.add("relativePosition");
        nameLeftOperand.add("relativeSize");
        nameLeftOperand.add("relativeSpatialPosition");
        nameLeftOperand.add("relativeTemporalPosition");
        nameLeftOperand.add("resolution");
        nameLeftOperand.add("spatial");
        nameLeftOperand.add("spatialCoordinates");
        nameLeftOperand.add("system");
        nameLeftOperand.add("systemDevice");
        nameLeftOperand.add("timeInternal");
        nameLeftOperand.add("unitOfCount");
        nameLeftOperand.add("version");
        nameLeftOperand.add("virtualLocation");

    }

    public Policy getPolicy() {
        return policy;
    }

}
