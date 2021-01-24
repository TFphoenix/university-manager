package entities;

import java.util.Date;

public class Registry {
    private int id;
    private int grade;
    private Date gradingDate;
    private int idSubject;
    private int idStudent;

    public Registry() {
        id = 0;
        grade = 0;
        gradingDate = null;
        idSubject = 0;
        idStudent = 0;
    }

    public Registry(int id, int grade, Date gradingDate, int idSubject, int idStudent) {
        this.id = id;
        this.grade = grade;
        this.gradingDate = gradingDate;
        this.idSubject = idSubject;
        this.idStudent = idStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getGradingDate() {
        return gradingDate;
    }

    public void setGradingDate(Date gradingDate) {
        this.gradingDate = gradingDate;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
}
