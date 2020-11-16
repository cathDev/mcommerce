package com.clientui.beans;

public class ExpeditionBean {

    private int id;
    private int idCommande;
    private int etat;

    public ExpeditionBean(){}

    public int getId() {
        return id;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public int getEtat() {
        return etat;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Expédition{" +
                "id=" + id +
                ", idCommande=" + idCommande +
                ", etat=" + etat +
                '}';
    }
}
