package entities;

public class Utilizator {

    private int id;
    private String mail;
    private String parola;
    private String drepturi;

    public Utilizator() {
        id = 0;
        mail = "";
        parola = "";
        drepturi = "";
    }

    public Utilizator(String mail, String parola, String drepturi) {
        this.mail = mail;
        this.parola = parola;
        this.drepturi = drepturi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getDrepturi() {
        return drepturi;
    }

    public void setDrepturi(String drepturi) {
        this.drepturi = drepturi;
    }
}
