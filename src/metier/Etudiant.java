package metier;

public class Etudiant {
	private int matricule ;
	private String nom ;
	private String prenom;
	
	public Etudiant(int matricule, String nom, String prenom) {
		super();
		this.matricule = matricule;
		this.nom = nom;
	}

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	

	@Override
	public String toString() {
		return "Etudiant [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	

}
