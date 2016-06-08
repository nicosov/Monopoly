package Data;

import Jeu.Message;
import java.util.HashSet;

public class ProprieteAConstruire extends Propriete {

    private Joueur proprietaire;
    private Groupe groupe;

    public ProprieteAConstruire(int numero, String nomCarreau, int loyer, int prixPropriete, Groupe groupe) {
        super(numero, nomCarreau, loyer, prixPropriete);
        this.groupe = groupe;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

  

    public Groupe getGroupe() {

        return this.groupe;

    }

    @Override
    public int calculLoyer() {
        int i = 0;
        for (ProprieteAConstruire p : groupe.getPropriete()) {
            if (p.getProprietaire() == this.proprietaire) {
                i++;
            }
        }
        if (i == groupe.getPropriete().size()) {
            return getLoyer() * 2;
        } else {
            return getLoyer();
        }
    }

    @Override
    public boolean etudeAchatPropriete(Joueur aJ) {
        boolean res = false;
        if (this.proprietaire == null) {
            if (aJ.getCash() >= this.prixPropriete) {
                res = true;
            } else {
                res= false;
            }

        }
        return res;
    }

    public void acheterPropriete(Joueur aJ) {
        int monnaiej = aJ.getCash();
        int prixPropriete = this.prixPropriete;
        aJ.payerLoyer(prixPropriete);
        setProprietaire(aJ);
        aJ.addProprieteAConstruire(this);
    }

    public int getLoyer() {
        return super.getLoyer();
    }

    @Override
    public void notifier(Message msg) {
      msg.type = Message.Types.PROPRIETE_A_CONSTRUIRE;
    }
}
