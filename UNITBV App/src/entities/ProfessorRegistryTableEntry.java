package entities;

public class ProfessorRegistryTableEntry {

    private int id;
    private String disciplina;
    private int nota;
    private String dataNotarii;
    private String grupa;
    private String student;

    private String profesor;

    public ProfessorRegistryTableEntry(){
        this.disciplina = "?";
        this.nota = 0;
        this.dataNotarii = "?";
        this.grupa = "?";
        this.student = "?";
    }

    public ProfessorRegistryTableEntry(String disciplina, int nota, String dataNotarii, String grupa, String student) {
        this.disciplina = disciplina;
        this.nota = nota;
        this.dataNotarii = dataNotarii;
        this.grupa = grupa;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDataNotarii() {
        return dataNotarii;
    }

    public void setDataNotarii(String dataNotarii) {
        this.dataNotarii = dataNotarii;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
