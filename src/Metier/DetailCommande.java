package Metier;

public class DetailCommande {
    private int idDetail;
    private int idCommande;
    private int idProduit;
    private int quantite;

    public DetailCommande(int idDetail, int idCommande, int idProduit, int quantite) {
        this.idDetail = idDetail;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

   
    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "DetailCommande{" +
                "idDetail=" + idDetail +
                ", idCommande=" + idCommande +
                ", idProduit=" + idProduit +
                ", quantite=" + quantite +
                '}';
    }
}