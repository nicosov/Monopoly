package Data;

import java.util.HashSet;

public class AutreCarreau extends Carreau {
    
    public AutreCarreau(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }


    
   @Override
    public boolean etudeAchatPropriete(Joueur aJ ) {
    return false;
    }

    @Override
    public void acheterPropriete(Joueur aJ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void action(Joueur aJ) {
        System.out.println("AutreCarreau.action() - A FAIRE");
    }

}