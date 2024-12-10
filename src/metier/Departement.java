package metier;

public class Departement {
    private String codeDpt;
    private String titre;

    public Departement(String codeDpt, String titre) {
        this.codeDpt = codeDpt;
        this.titre = titre;
    }

    public Departement() {}

    public String getCodeDpt() {
        return codeDpt;
    }

    public void setCodeDpt(String codeDpt) {
        this.codeDpt = codeDpt;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    @Override
    public String toString() {
        return "Departement [codeDpt=" + codeDpt + ", titre=" + titre + "]";
    }
}