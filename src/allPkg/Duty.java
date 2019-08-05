package allPkg;

public class Duty {
    public String uid;
    public String id;
    public String idref;

    public Duty(String id, String idref) {
        this.id = id;
        this.idref = idref;
    }

    public Duty(String uid) {
        this.id = uid;
    }

}
