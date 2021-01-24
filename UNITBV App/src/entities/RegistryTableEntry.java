package entities;

public class RegistryTableEntry {

    private String materie;
    private int credite;
    private String profesor;
    private String status;
    private String nota;
    private String dataNotarii;
    private int an;
    private int semestru;

    public RegistryTableEntry() {
        this.materie = "?";
        this.credite = 0;
        this.profesor = "?";
        this.status = "nenotat";
        this.nota = "?";
        this.dataNotarii = "?";
        this.an = 0;
        this.semestru = 0;
    }

    public RegistryTableEntry(String materie, int credite, String profesor, String status, String nota, String dataNotarii, int an, int semestru) {
        this.materie = materie;
        this.credite = credite;
        this.profesor = profesor;
        this.status = status;
        this.nota = nota;
        this.dataNotarii = dataNotarii;
        this.an = an;
        this.semestru = semestru;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public int getCredite() {
        return credite;
    }

    public void setCredite(int credite) {
        this.credite = credite;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDataNotarii() {
        return dataNotarii;
    }

    public void setDataNotarii(String dataNotarii) {
        this.dataNotarii = dataNotarii;
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
