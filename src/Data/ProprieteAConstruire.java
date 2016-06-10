package Data;

import Jeu.Message;
import java.util.HashSet;

public class ProprieteAConstruire extends Propriete {

    private Joueur proprietaire;
    private Groupe groupe;
    private int nbMaison;
    private int nbHotel;
    private int prixMaison;
    private int loyer1Maison;
    private int loyer2Maison;
    private int loyer3Maison;
    private int loyer4Maison;
    private int loyerHotel;
    
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

    public ProprieteAConstruire(int numero, String nomCarreau, int loyer, int prixPropriete, Groupe groupe, int prixMaison, int loyer1M, int loyer2M, int loyer3M, int loyer4M, int loyerH) {
        super(numero, nomCarreau, loyer, prixPropriete);
        this.groupe = groupe;
        this.prixMaison=prixMaison;
        nbMaison=0;
        nbHotel=0;
        this.loyer1Maison=loyer1M;
        this.loyer2Maison=loyer2M;
        this.loyer3Maison=loyer3M;
        this.loyer4Maison=loyer4M;
        this.loyerHotel=loyerH;
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
        boolean jproprio = true;
        for (ProprieteAConstruire p : groupe.getPropriete()) {
            if (p.getProprietaire() != this.proprietaire) {
                jproprio=false;
            }
        }
        if (jproprio) {
            if(this.getNbHotel()>0){
                return loyerHotel;
            }else if (this.getNbMaison()==1){
                return loyer1Maison;
            }else if (this.getNbMaison()==2){
                return loyer2Maison;
            }else if (this.getNbMaison()==3){
                return loyer3Maison;
            }else if (this.getNbMaison()==4){
                return loyer4Maison;
            }else{
                return getLoyer() * 2;
            }    
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
