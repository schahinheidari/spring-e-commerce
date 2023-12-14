package fr.tln.univ.entities;

import java.util.Date;

public class Commande {

    private int numero;
    private Date date;

    public Commande(int numero, Date date) {
        this.numero = numero;
        this.date = date;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
