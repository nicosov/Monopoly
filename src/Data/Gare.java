package Data;

import Jeu.Message;
import java.util.HashSet;

public class Gare extends Propriete {

    private int loyer;

    private Joueur proprietaire;

    public Gare(int numero, String nomCarreau, int prixPropriete, int loyer) {
        super(numero, nomCarreau, prixPropriete, loyer);

    }

    @Override
    public void action(Joueur aJ) {
    
    }

    @Override
    public int calculLoyer() {
        int i = 25;
        int nbg = proprietaire.getGares();
        return i * nbg;

    }

    private void setProprietaire(Joueur aJ) {
        this.proprietaire = aJ;
    }

    private Joueur getProprietaire() {
        return this.proprietaire;
    }

    public void acheterPropriete(Joueur aJ) {
        int monnaiej = aJ.getCash();
        aJ.payerLoyer(prixPropriete);
        setProprietaire(aJ);
        aJ.addGare(this);

    }

    @Override
    public boolean etudeAchatPropriete(Joueur aJ) {
        boolean res = false;
        if (this.proprietaire == null) {
            if (aJ.getCash() >= this.prixPropriete) {
                res = true;
            } else {
                res = false;
            }

        }
        return res;
    }

    public int getLoyer() {
        return this.loyer;
    }

    @Override
    public void notifier(Message msg) {
    msg.type = Message.Types.GARE;
    }

}
