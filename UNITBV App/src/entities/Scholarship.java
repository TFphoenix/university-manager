package entities;

public class Scholarship {

    private int id;
    private int grupaId;
    private int gradeSCount;
    private int specialSCount;

    public Scholarship() {
        this.id = 0;
        this.grupaId = 0;
        this.gradeSCount = 0;
        this.specialSCount = 0;
    }

    public Scholarship(int id, int grupaId, int gradeSCount, int specialSCount) {
        this.id = id;
        this.grupaId = grupaId;
        this.gradeSCount = gradeSCount;
        this.specialSCount = specialSCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrupaId() {
        return grupaId;
    }

    public void setGrupaId(int grupaId) {
        this.grupaId = grupaId;
    }

    public int getGradeSCount() {
        return gradeSCount;
    }

    public void setGradeSCount(int gradeSCount) {
        this.gradeSCount = gradeSCount;
    }

    public int getSpecialSCount() {
        return specialSCount;
    }

    public void setSpecialSCount(int specialSCount) {
        this.specialSCount = specialSCount;
    }
}
