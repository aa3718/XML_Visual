package allPkg;


public class LogicalConstraint {
    public String idStringG;

    LogicalConstraint() {
        idStringG = "#1D #2D 3D";
    }


    public static void main(String[] argv) {
        LogicalConstraint log = new LogicalConstraint();
        String idString = log.idStringG.replaceAll("#","");
        System.out.println(idString);
        String[] ids = idString.split(" ");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
    }



}
