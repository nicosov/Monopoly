package Data;

import Jeu.Message;
import java.util.HashSet;

public class Compagnie extends Propriete {

    
    public Compagnie(int numero, String nomCarreau, int loyer, int prixPropriete) {
        super(numero, nomCarreau, loyer, prixPropriete);
        
    }

    public int getPrixPropriete() {
        return prixPropriete;
    }

    public void setPrixPropriete(int prixPropriete) {
        this.prixPropriete = prixPropriete;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }


    public int calculLoyer() {
         return ((3+proprietaire.getNbComp())*proprietaire.getNbComp());
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
        aJ.addCompagnie(this);
    }

    @Override
    public void notifier(Message msg) {
    msg.type = Message.Types.COMPAGNIE; //To change body of generated methods, choose Tools | Templates.
    }

   

}
