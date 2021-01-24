package entities;

public class ScholarshipTableEntry {

    private int id;

    private String grupa;
    private int gradeScolarshipCount;
    private int specialScolarshipCount;

    public ScholarshipTableEntry() {
        this.grupa = "?";
        this.gradeScolarshipCount = 0;
        this.specialScolarshipCount = 0;
    }

    public ScholarshipTableEntry(String grupa, int gradeScolarshipCount, int specialScolarshipCount) {
        this.grupa = grupa;
        this.gradeScolarshipCount = gradeScolarshipCount;
        this.specialScolarshipCount = specialScolarshipCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public int getGradeScolarshipCount() {
        return gradeScolarshipCount;
    }

    public void setGradeScolarshipCount(int gradeScolarshipCount) {
        this.gradeScolarshipCount = gradeScolarshipCount;
    }

    public int getSpecialScolarshipCount() {
        return specialScolarshipCount;
    }

    public void setSpecialScolarshipCount(int specialScolarshipCount) {
        this.specialScolarshipCount = specialScolarshipCount;
    }
}
