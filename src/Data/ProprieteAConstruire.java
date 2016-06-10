package Data;

import Jeu.Message;
import java.util.HashSet;

public class ProprieteAConstruire extends Propriete {

    private Joueur proprietaire;
    private Groupe groupe;
    private int nbMaison;
    private int nbHotel;
    private int prixMaison;

    public int getNbMaison() {
        return nbMaison;
    }

    public int getPrixMaison() {
        return prixMaison;
    }

    public void setPrixMaison(int prixMaison) {
        this.prixMaison = prixMaison;
    }

    public void setNbMaison(int nbMaison) {
        this.nbMaison = nbMaison;
    }

    public int getNbHotel() {
        return nbHotel;
    }

    public void setNbHotel(int nbHotel) {
        this.nbHotel = nbHotel;
    }

    public ProprieteAConstruire(int numero, String nomCarreau, int loyer, int prixPropriete, Groupe groupe, int prixMaison) {
        super(numero, nomCarreau, loyer, prixPropriete);
        this.groupe = groupe;
        this.prixMaison=prixMaison;
        nbMaison=0;
        nbHotel=0;
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

    @Override
    public Message action(Joueur aJ) {
        Message msg = new Message();
        msg.type=Message.Types.PROPRIETE_A_CONSTRUIRE;
        msg.joueur=aJ;
        msg.prixPropriete=this.prixPropriete;
        msg.proprietaire=this.proprietaire;
        return msg;
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
