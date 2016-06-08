package Data;

import Jeu.Controleur;
import Jeu.Monopoly;
import java.util.ArrayList;

public class Joueur {

    private String nomJoueur;
    private int cash = 1500;
    public ArrayList<ProprieteAConstruire> proprieteAConstruires = new ArrayList<ProprieteAConstruire>();
    public ArrayList<Gare> gares = new ArrayList<Gare>();
    public ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();

    private Carreau positionCourante;
    private int TotalDes;


    public boolean enVie(){
        if (this.cash >= 0 ){
            return true;
        } else return false;
    }
    public int getTotalDes() {
        return TotalDes;
    }

    public void setTotalDes(int TotalDes) {
        this.TotalDes = TotalDes;
    }

    public Joueur(String nomJoueur, Carreau c) {
        this.nomJoueur = nomJoueur;
        setPositionCourante(c);
    }

    public void payerLoyer(int aL) {
        cash -= aL;
    }

    public void recevoirLoyer(int aL) {
        cash +=aL;
    }

    public Carreau getPositionCourante() {
        return this.positionCourante;
    }

    public void setPositionCourante(Carreau aC) {
        positionCourante = aC;
    }

    public String getNom() {
        return nomJoueur;
    }

    public String getNomCarreau() {
        throw new UnsupportedOperationException();
    }

    public int getCash() {
        return this.cash;
    }

    public int getGares() {
        return gares.size();
    }


    public void addGare(Gare gare) {
        gares.add(gare);
    }

    public void addProprieteAConstruire(ProprieteAConstruire proprieteAConstruire) {
        proprieteAConstruires.add(proprieteAConstruire);
    }

    public void addCompagnie(Compagnie compagnie) {
        compagnies.add(compagnie);
    }

    public int getNbComp() {
        return compagnies.size();
    }

    public int getNbPropriete() {
        return proprieteAConstruires.size();
    }
    
  
}
