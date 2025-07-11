package Metier;

import java.time.LocalDateTime;

public class Commande {
    private int idCommande;
    private int idClient;
    private LocalDateTime dateCommande;
    private String statut;

    public Commande(int idCommande, int idClient, LocalDateTime dateCommande, String statut) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.dateCommande = dateCommande;
        this.statut = statut;
    }

    // Getters and Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", idClient=" + idClient +
                ", dateCommande=" + dateCommande +
                ", statut='" + statut + '\'' +
                '}';
    }
}