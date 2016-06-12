package Data;

import Jeu.Controleur;
import Jeu.Monopoly;
import java.util.ArrayList;

public class Joueur {

    private int nbCarteLiberePrison = 0;
    private boolean enPrison = false;
    private int nbTourPrison;
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

    public boolean isEnPrison() {
        return enPrison;
    }

    public int getNbTourPrison() {
        return nbTourPrison;
    }

    public void setNbTourPrison(int nbTourPrison) {
        this.nbTourPrison = nbTourPrison;
    }

    

    
    
    public ArrayList<ProprieteAConstruire> getProprieteAConstruires() {
        return proprieteAConstruires;
    }

    public void setProprieteAConstruires(ArrayList<ProprieteAConstruire> proprieteAConstruires) {
        this.proprieteAConstruires = proprieteAConstruires;
    }

    public int getNbCarteLiberePrison() {
        return nbCarteLiberePrison;
    }

    public void setNbCarteLiberePrison(int nbCarteLiberePrison) {
        this.nbCarteLiberePrison = nbCarteLiberePrison;
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
        this.cash=1500;
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

    public ArrayList<Gare> getGares() {
        return gares;
    }

    public int getNbGares() {
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
    
    public ArrayList<Compagnie> getCompagnie(){
        return compagnies;
    }
    
    public int getNbCompagnie(){
        return compagnies.size();
    }

    public void enPrison(){
        if (enPrison){
            enPrison=false;
            
        }else if(!enPrison){
            enPrison=true;
            nbTourPrison=0;
            
        }
    }
}
