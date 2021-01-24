package entities;

public class StudentTableEntry {

    private int id;
    private int utilizatorId;

    private String badge;
    private String name;
    private String cnp;
    private String domicile;
    private String dateOfBirth;
    private String dateOfRegistration;
    private String mail;
    private String group;

    public StudentTableEntry() {
        this.badge = "?";
        this.name = "?";
        this.cnp = "?";
        this.domicile = "?";
        this.dateOfBirth = "?";
        this.dateOfRegistration = "?";
        this.mail = "?";
        this.group = "?";
    }

    public StudentTableEntry(String badge, String name, String cnp, String domicile, String dateOfBirth, String dateOfRegistration, String mail, String group) {
        this.badge = badge;
        this.name = name;
        this.cnp = cnp;
        this.domicile = domicile;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
        this.mail = mail;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilizatorId() {
        return utilizatorId;
    }

    public void setUtilizatorId(int utilizatorId) {
        this.utilizatorId = utilizatorId;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
