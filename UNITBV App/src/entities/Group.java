package entities;

public class Group {

    private int id;
    private String code;
    private String specialization;

    public Group() {
        this.id = 0;
        this.code = "";
        this.specialization = "";
    }

    public Group(int id, String code, String specialization) {
        this.id = id;
        this.code = code;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
