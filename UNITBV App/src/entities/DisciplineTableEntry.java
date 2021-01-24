package entities;

public class DisciplineTableEntry {

    private String nume;
    private int credite;
    private int an;
    private int semestru;

    public DisciplineTableEntry() {
        this.nume = "?";
        this.credite = 0;
        this.an = 0;
        this.semestru = 0;
    }

    public DisciplineTableEntry(String nume, int credite, int an, int semestru) {
        this.nume = nume;
        this.credite = credite;
        this.an = an;
        this.semestru = semestru;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCredite() {
        return credite;
    }

    public void setCredite(int credite) {
        this.credite = credite;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public int getSemestru() {
        return semestru;
    }

    public void setSemestru(int semestru) {
        this.semestru = semestru;
    }
}
