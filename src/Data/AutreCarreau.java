package Data;
import Jeu.Observateur;

import Jeu.Message;
import java.util.HashSet;

public class AutreCarreau extends Carreau {

    private Obsertvateur observateur;
    public AutreCarreau(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }



   @Override
    public boolean etudeAchatPropriete(Joueur aJ ) {
    return false;
    }

    @Override
    public void acheterPropriete(Joueur aJ) {
       
    }

    /**
     *
     * @param aJ
     * @return
     */
    @Override
    public Message action(Joueur aJ) {
        Message msg = new Message();
        msg.type=Message.Types.AUTRE_CARREAU;
        msg.joueur=aJ;
        return msg;
    }

    @Override
    public int calculLoyer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}