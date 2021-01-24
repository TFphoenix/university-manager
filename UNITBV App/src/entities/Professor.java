package entities;

public class Professor {

    int id;
    String name;
    String title;
    int idUtilizator;

    public Professor() {
        this.id = 0;
        this.name = "";
        this.title = "";
        this.idUtilizator = 0;
    }

    public Professor(int id, String name, String title, int idUtilizator) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.idUtilizator = idUtilizator;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }
}
