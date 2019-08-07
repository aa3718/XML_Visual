package allPkg;

import java.util.List;

interface Rules {

     void setAsset(Asset asset);

     void setAction(Action action);

     void setConstraint(Constraint constraint);

     void setDuty(Duty duty);

     void setParty(Party party);

     void setEntireConstraint(List<Constraint> constraints);

     void setEntireParty(List<Party> parties);

     void setEntireDuty(List<Duty> duties);

     Asset getAsset();

     Action getAction();

     List<Constraint> getConstraint();

     List<Duty> getDuty();

     List<Party> getParty();

}
