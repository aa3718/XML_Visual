package allPkg;

import java.util.ArrayList;
import java.util.List;

public abstract class attributeHolders {

abstract ArrayList<String> getAttributeList();

abstract void setAttribute(String attribute, String value);

abstract void setConstraint(Constraint refinement);

abstract List<Constraint> getConstraint();

abstract String getID();

abstract void buildAttributeList();


}
