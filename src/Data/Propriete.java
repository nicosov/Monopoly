package Data;

import Jeu.Message;
import Jeu.Observateur;
import java.util.HashSet;

public abstract class Propriete extends Carreau implements Observateur {

    private Observateur observateur;
    private int loyer;
    public int prixPropriete;
    public Joueur proprietaire;

    public Propriete(int numero, String nomCarreau, int prixPropriete, int loyer) {
        super(numero, nomCarreau);
        this.loyer = loyer;
        this.proprietaire = proprietaire;
        this.prixPropriete = prixPropriete;
        this.setObservateur(this);
        

    }
     public void setObservateur(Observateur observateur) {
        this.observateur = observateur;
    }

    public abstract int calculLoyer();

    public int getPrixPropriete() {
        return prixPropriete;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    private void setProprietaire(Joueur j) {
        this.proprietaire = j;
    }

    /**
     *
     * @param aJ
     */
    @Override
    public Message action(Joueur aJ) {
        Message msg = new Message();
        
        
        Joueur jProprio = getProprietaire();
        if (jProprio == null) {
            msg.prixPropriete = this.prixPropriete;
            msg.joueur = aJ;
            etudeAchatPropriete(aJ);
        } else {

            loyer = calculLoyer();
            aJ.payerLoyer(loyer);
            jProprio.recevoirLoyer(loyer);
            msg.proprietaire = jProprio;
            msg.loyer = getLoyer();

        }
        return msg;
         
    }

    public boolean etudeAchatPropriete(Joueur aJ) {
        if (aJ.getCash() >= this.prixPropriete) {
            return true;
        } else {
            return false;
        }
    }

    public void acheterPropriete(Joueur aJ) {
        int monnaiej = aJ.getCash();
        int prixPropriete = getPrixPropriete();
        aJ.payerLoyer(prixPropriete);
        setProprietaire(aJ);

    }

    public void setloyer(int loyer) {
        this.loyer = loyer;
    }

    public int getLoyer() {
        return loyer;
    }
}
