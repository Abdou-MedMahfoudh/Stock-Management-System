package metier;

import java.time.LocalDate;
import java.util.Date;

public class Suivre {

    // Foreign key values
    private String matricule; // Student's matricule (ID)
    private String code;      // Module's code

    // Attributes specific to Suivre
    private String semester;
    private  Date date;
    private int heures;
    private double note;

    // Default constructor
    public Suivre() {
    }

    // Parameterized constructor
    public Suivre(String matricule, String code, String semester, Date date, int heures, double note) {
        this.matricule = matricule;
        this.code = code;
        this.semester = semester;
        this.date = date;
        this.heures = heures;
        this.note = note;
    }

    // Getters and Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    // toString for debugging
    @Override
    public String toString() {
        return "Suivre{" +
                "matricule='" + matricule + '\'' +
                ", code='" + code + '\'' +
                ", semester='" + semester + '\'' +
                ", date=" + date +
                ", heures=" + heures +
                ", note=" + note +
                '}';
    }
}