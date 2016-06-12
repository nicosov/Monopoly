package Data;

import Jeu.Message;
import Jeu.Monopoly;
import java.util.HashSet;

public abstract class Carreau {

    private int numero;
    private String nomCarreau;
    private Joueur proprietaire;
    private int prixPropriete;
    
    public Carreau(int numero, String nomCarreau) {
        this.numero = numero;
        this.nomCarreau = nomCarreau;
        
    }

    public int getPrixPropriete() {
        return prixPropriete;
    }

    public void setPrixPropriete(int prixPropriete) {
        this.prixPropriete = prixPropriete;
    }

    public int getNumero() {
        return this.numero;
    }
    
    public abstract int calculLoyer();

    public abstract Message action(Joueur aJ);

    public String getNomCarreau() {
        return this.nomCarreau;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }
    
    public abstract boolean etudeAchatPropriete(Joueur aJ);

    public abstract void acheterPropriete(Joueur aJ);
    
}
