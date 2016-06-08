/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carte;

import Data.Carte;
import Data.Joueur;
import Data.TypeCarte;

/**
 *
 * @author girina
 */
public class CarteDeplacementAbsolut extends Carte{
    private String paye;
    private int numCarreau;
    
    public CarteDeplacementAbsolut(TypeCarte typeCarte, String description, int numCarreau, String paye) {
        super(typeCarte, description);
        this.numCarreau=numCarreau;
        this.paye=paye;
    }

    @Override
    public void actionCarte(Joueur aJ) {
        if(paye.compareTo("oui")==0){
            if(aJ.getPositionCourante().getNumero()>numCarreau){
                aJ.recevoirLoyer(200);
            }
        }
         aJ.DeplacementPositionNumeroCarreau(numCarreau);
       
    }
    
}
