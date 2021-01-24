package entities;

public class Subject {

    private int id;
    private String name;
    private int credits;
    private int year;
    private int semester;
    private int idProfessor;

    public Subject() {
        this.id = 0;
        this.name = "";
        this.credits = 0;
        this.year = 0;
        this.semester = 0;
        this.idProfessor = 0;
    }

    public Subject(int id, String name, int credits, int year, int semester, int idProfessor) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.year = year;
        this.semester = semester;
        this.idProfessor = idProfessor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
}
