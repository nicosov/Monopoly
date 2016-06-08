package Data;

import java.util.HashSet;

public abstract class Carreau {

    private int numero;
    private String nomCarreau;

    public Carreau(int numero, String nomCarreau) {
        this.numero = numero;
        this.nomCarreau = nomCarreau;
    }

    public int getNumero() {
        return this.numero;
    }


    public abstract void action(Joueur aJ);

    public String getNomCarreau() {
        return this.nomCarreau;
    }

    public abstract boolean etudeAchatPropriete(Joueur aJ);

    public abstract void acheterPropriete(Joueur aJ);
    
    public void setNumeroCarreau(int numC){
        this.numero=numC;
    }
}
