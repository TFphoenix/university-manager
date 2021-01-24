package entities;

import java.util.Date;

public class Student {

    private int id;
    private String badge;
    private String name;
    private String cnp;
    private String domicile;
    private Date dateOfBirth;
    private Date dateOfRegistration;
    private int idUtilizator;
    private int idGrupa;

    public Student() {
        this.id = 0;
        this.badge = "";
        this.name = "";
        this.cnp = "";
        this.domicile = "";
        this.dateOfBirth = new Date(0);
        this.dateOfRegistration = new Date(0);
        this.idUtilizator = 0;
        this.idGrupa = 0;
    }

    public Student(int id, String badge, String name, String cnp, String domicile, Date dateOfBirth, Date dateOfRegistration, int idUtilizator, int idGrupa) {
        this.id = id;
        this.badge = badge;
        this.name = name;
        this.cnp = cnp;
        this.domicile = domicile;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
        this.idUtilizator = idUtilizator;
        this.idGrupa = idGrupa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public int getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }
}
