package Jeu;

import Carte.CarteDeplacementAbsolut;
import Carte.CarteDeplacementPrison;
import Carte.*;
import Ui.*;
import java.io.*;
import java.util.ArrayList;
import Data.Joueur;
import Data.*;
import java.util.Random;
import Main.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Controleur {

    private Observateur observateur;
    private Joueur jCourant;
    private HashSet<String> res;
    private IHM_console ihm_console;
    private IHM ihm;
    private Monopoly monopoly;
    private Propriete propriete_p;
    private boolean doubleDes = false;
    private HashMap<CouleurPropriete, Groupe> groupes = new HashMap<CouleurPropriete, Groupe>();
    private int des1;
    private int des2;

    public Controleur() {
        monopoly = new Monopoly();
        
        ihm_console = new IHM_console(this);
        
        ihm = new IHM(this);
        ihm.afficherIhmMenu();
        CreerPlateau("src/Main/data.txt");
        buildCartes("src/Main/cartes.txt");
        jeu();
    }

    public HashSet<String> getRes() {
        return res;
    }

    public void setRes(HashSet<String> res) {
        this.res = res;
    }

    public Joueur getjCourant() {
        return jCourant;
    }

    public void setjCourant(Joueur jCourant) {
        this.jCourant = jCourant;
    }

    public void jeu() {
        int nbjoueurs = 0;
        int choixMenu = ihm_console.afficherMenu();
        boolean choix = true;
        while (choix) {
            switch (choixMenu) {
                case 1:
                    nbjoueurs = ihm_console.afficherDemandeNbJoueurs();
                    for (int i = 0; i < nbjoueurs; i++) {
                        this.CreerJoueur(ihm_console.afficherInsJoueur());
                    }
                    jeu();
                    choix = false;
                    break;
                case 2:
                    monopoly.addJoueursEnVie();
                    while (monopoly.getJoueursEnVie().size() >= 2) {
                        for (Joueur j : monopoly.getJoueursMort()) {
                            monopoly.getJoueursEnVie().remove(j);

                        }

                        for (Joueur aJ : monopoly.getJoueursEnVie()) {
                            jCourant = aJ;
                            this.jouerUnCoup(aJ);

                            if (aJ.enVie() == false) {
                                monopoly.addJoueurMort(aJ);
                                ihm_console.afficherMort();
                            }
                        }
                        monopoly.removeJoueursEnVie();

                    }
                    if (monopoly.getJoueursEnVie().size() < 2) {
                        estFini();
                    }
                    choix = false;
                    break;
                case 3:
                    estFini();
                    System.exit(0);
                    choix = false;
                    break;
            }
        }

    }

    // Retourne le joueur courant
    public void jCourant() {
        ihm_console.afficherInfoJoueurCourant(jCourant);
    }

    public void estFini() {
        boolean estPremier = true;
        String nomGagnant = "";

        for (Joueur j1 : monopoly.getJoueursEnVie()) {
            for (Joueur j : monopoly.getJoueursEnVie()) {
                if (j1.getCash() < j.getCash()) {
                    estPremier = false;

                } else {
                    estPremier = true;
                }

            }
            if (estPremier) {
                nomGagnant = j1.getNom();
            }
        }
        if (estPremier) {
            ihm_console.afficherFin(nomGagnant);
        }
    }

    public void monopolyConstruire(int numC){
       
    }
    
   public ArrayList<ProprieteAConstruire> proprietesConstructibles(){
       ArrayList<ProprieteAConstruire> propConstructibles = new ArrayList();
       for(ProprieteAConstruire p : jCourant.getProprieteAConstruires()){
           if(monopoly.etudeConstruire(p)){
               propConstructibles.add(p);
           }
       }
       return propConstructibles;
   }
    
    
    public void jouerUnCoup(Joueur aJ) {
        boolean rejouer = true;
        Carreau c = lancerDesAvancer(aJ);
        int i = 0;
        c.action(aJ);
        Carte carte = null;
        
        while(rejouer){
            rejouer = false;
            Message msgCarreau = c.action(aJ);
            switch(msgCarreau.type) {
                case AUTRE_CARREAU:
                    rejouer=false;
                    break;
                case CHANCE:
                    carte=monopoly.tirerChance();
                    System.out.println(carte.getDescription());
                    Message msgCarte=carte.actionCarte(aJ);
                    switch(msgCarte.typeCarte){
                        case DeplacementAbsolut:
                            aJ.setPositionCourante(monopoly.getCarreau(msgCarte.numC));
                            c=aJ.getPositionCourante();
                            
                            rejouer = true;
                            break;
                        case DeplacementPrison:
                            break;
                        case DeplacementRelatif:
                            System.out.println(aJ.getPositionCourante().getNumero()); 
                            aJ.setPositionCourante(monopoly.getCarreau((aJ.getPositionCourante().getNumero())-4));
                            c=aJ.getPositionCourante();
                            System.out.println(aJ.getPositionCourante().getNomCarreau());
                            rejouer = true;
                            break;
                        case Entretien:
                            break;
                        case LiberePrison:
                            break;
                        case Transaction:
                            break;
                    }
                    break;
                case COMMUNAUTE:
                carte=monopoly.tirerCommunaute();
                msgCarte=carte.actionCarte(aJ);
                    switch(msgCarte.typeCarte){
                        case Anniversaire:
                            monopoly.payerAnniversaire(aJ);
                            break;
                        case DeplacementAbsolut:
                            aJ.setPositionCourante(monopoly.getCarreau(msgCarte.numC));
                            c=aJ.getPositionCourante();
                            rejouer = true;
                            break;
                        case DeplacementPrison:
                            break;
                        case LiberePrison:
                            break;
                        case Transaction:
                            break;
                  }
                   break;
                case COMPAGNIE:
                    rejouer = false;
                    if(c.getProprietaire()==null){
                        if (c.etudeAchatPropriete(aJ)) {
                            i = ihm_console.afficherMenuAchat(c.getPrixPropriete());
                            if (i == 1) {
                                c.acheterPropriete(aJ);
                            }
                        }
                    }else if(c.getProprietaire()==aJ){
                        IHM_console.afficherChezlui();
                    }else if (c.getProprietaire()!=aJ){
                        int montant = c.calculLoyer()*(des1 + des2);
                        aJ.payerLoyer(montant);
                        c.getProprietaire().recevoirLoyer(montant);
                        ihm_console.afficherPayerCompagnie(c.getProprietaire(), montant);
                    }
                  break;
                case GARE:
                    rejouer = false;
                    if(c.getProprietaire()==null){
                        if (c.etudeAchatPropriete(aJ)) {
                            i = ihm_console.afficherMenuAchat(c.getPrixPropriete());
                            if (i == 1) {
                                c.acheterPropriete(aJ);
                            }
                        }
                    }else if (c.getProprietaire()==aJ){
                        IHM_console.afficherChezlui();
                    }else if (c.getProprietaire()!=aJ){
                        int montant = c.calculLoyer();
                        c.getProprietaire().recevoirLoyer(montant);                        
                        aJ.payerLoyer(montant);
                        ihm_console.afficherPayerGare(c.getProprietaire(), montant);
                    }
                  break;
                case PROPRIETE_A_CONSTRUIRE:
                    if(c.getProprietaire()==null){
                        if (c.etudeAchatPropriete(aJ)) {
                            i = ihm_console.afficherMenuAchat(c.getPrixPropriete());
                            if (i == 1) {
                                c.acheterPropriete(aJ);
                            }
                        }
                    }else if(c.getProprietaire()==aJ){
                        IHM_console.afficherChezlui();
                    }else if (c.getProprietaire()!=aJ){
                        int montant = c.calculLoyer();
                        c.getProprietaire().recevoirLoyer(montant);                        
                        aJ.payerLoyer(montant);
                        ihm_console.afficherPayerProprieteAConstruire(c.getProprietaire(), montant);
                    }
                    rejouer = false;
                    break;
            }
        observateur.notifier(msgCarreau);
        
        }
        
        

        if (doubleDes) {
            jouerUnCoup(aJ);

        } else {
            int choix = ihm_console.afficherMenuJeu_p();
        }
        
       
        
    }

    
    
    
    private Carreau lancerDesAvancer(Joueur aJ) {

        des1 = 3;//lancerDes();// PHASE TEST: lancerDes() : normal - 0 : 1par1 - 3: gare - 3: compagnie - 2: double
        des2 = 2;//lancerDes();// PHASE TEST: lancerDes() : normal - 1 : 1par1 - 2: gare - 1: compagnie - 2: double
        monopoly.avancer(aJ, des1, des2);

        if (des1 == des2) {

            ihm_console.afficherDouble(aJ);
            doubleDes = true;
        } else {
            doubleDes = false;
        }
        
        return aJ.getPositionCourante();

    }

    public int lancerDes() {
        Random r = new Random();
        return r.nextInt(6) + 1;
    }

    public void CreerJoueur(String nomJoueur) {

        monopoly.addJoueur_p(new Joueur(nomJoueur, monopoly.getCarreau(0)));
    }

    public ArrayList<Joueur> getJoueursEnVie() {
        return monopoly.getJoueursEnVie();
    }

    public ArrayList<Carreau> getCarreaux_p() {
        return monopoly.getCarreaux_p();
    }

    public Carreau getCarreau(int c) {
        return monopoly.getCarreau(c);
    }

    public void CreerPlateau(String dataFilename) {
        buildGamePlateau(dataFilename);
    }

    private void buildGamePlateau(String dataFilename) {

        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            for (CouleurPropriete c : CouleurPropriete.values()) {
                groupes.put(c, new Groupe(c));
            }
            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    //    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    ProprieteAConstruire p = new ProprieteAConstruire(i + 1, data.get(i)[2], Integer.valueOf(data.get(i)[4]), Integer.valueOf(data.get(i)[5]), groupes.get(CouleurPropriete.valueOf(data.get(i)[3])),Integer.valueOf(data.get(i)[11]));
                    monopoly.addCarreau_p(p);
                    
                    groupes.get(CouleurPropriete.valueOf(data.get(i)[3])).addPropriete(p);
                } else if (caseType.compareTo("G") == 0) {
                    //   System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1] + "Prix : " + data.get(i)[3]);
                    monopoly.addCarreau_p(new Gare(i + 1, data.get(i)[2], Integer.valueOf(data.get(i)[3]), 0));
                } else if (caseType.compareTo("C") == 0) {
                    //   System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau_p(new Compagnie(i + 1, data.get(i)[2], Integer.valueOf(data.get(i)[3]), 0));
                } else if (caseType.compareTo("AU") == 0) {
                    //   System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau_p(new AutreCarreau(i + 1, data.get(i)[2]));
                } else if (caseType.compareTo("COM") == 0) {
                    //   System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau_p(new CarreauCommunaute(i + 1, data.get(i)[2]));
                } else if (caseType.compareTo("CH") == 0) {
                    //   System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau_p(new CarreauChance(i + 1, data.get(i)[2]));
                }else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }

    private void buildCartes(String dataFilename) {

        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
           
            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                
                String type = data.get(i)[0];
                String description = data.get(i)[2];
                String classe = data.get(i)[1]; 
                TypeCarte typeCarte;
                
                if(type.compareTo("Communaute")==0){
                    typeCarte=TypeCarte.communaute;
                }else{
                    typeCarte=TypeCarte.chance;
                }
                if (type.compareTo("Communaute") == 0) {
                    System.out.println("Communaute " + i);
                    if(classe.compareTo("T") == 0){
                        System.out.println(i);
                        monopoly.addCarteCommunaute(new CarteTransaction(typeCarte, description, Integer.valueOf(data.get(i)[3])));
                    }else if (classe.compareTo("DA")==0){
                        System.out.println(i);
                        monopoly.addCarteCommunaute(new CarteDeplacementAbsolut(typeCarte, description, Integer.valueOf(data.get(i)[3]), data.get(i)[4]));
                    }else if (classe.compareTo("DP")==0){
                             System.out.println(i);               
                        monopoly.addCarteCommunaute(new CarteDeplacementPrison(typeCarte, description));
                    }else if (classe.compareTo("DR")==0){
                            System.out.println(i);
                        monopoly.addCarteCommunaute(new CarteDeplacementRelatif(typeCarte, description));
                    }else if (classe.compareTo("L")==0){
                        System.out.println(i);
                        monopoly.addCarteCommunaute(new CarteLiberePrison(typeCarte, description));
                    }else if (classe.compareTo("E")==0){
                        System.out.println(i);
                        monopoly.addCarteCommunaute(new CarteEntretien(typeCarte, description, Integer.valueOf(data.get(i)[3]), Integer.valueOf(data.get(i)[4])));
                    }else if (classe.compareTo("A")==0){
                        System.out.println(i);
                        monopoly.addCarteCommunaute(new CarteAnniversaire(typeCarte, description));
                    }
                } else if (type.compareTo("Chance") == 0) { 
                    System.out.println("Chance " + i);

                    if(classe.compareTo("T") == 0){
                        System.out.println(i);
                        monopoly.addCarteChance(new CarteTransaction(typeCarte, description, Integer.valueOf(data.get(i)[3])));
                    }else if (classe.compareTo("DA")==0){
                        System.out.println(i);
                        monopoly.addCarteChance(new CarteDeplacementAbsolut(typeCarte, description, Integer.valueOf(data.get(i)[3]), data.get(i)[4]));
                    }else if (classe.compareTo("DP")==0){
                        System.out.println(i);
                        monopoly.addCarteChance(new CarteDeplacementPrison(typeCarte, description));
                    }else if (classe.compareTo("DR")==0){
                        System.out.println(i);
                        monopoly.addCarteChance(new CarteDeplacementRelatif(typeCarte, description));
                    }else if (classe.compareTo("L")==0){
                        System.out.println(i);
                        monopoly.addCarteChance(new CarteLiberePrison(typeCarte, description));
                    }else if (classe.compareTo("E")==0){
                        System.out.println(i);
                        monopoly.addCarteChance(new CarteEntretien(typeCarte, description, Integer.valueOf(data.get(i)[3]), Integer.valueOf(data.get(i)[4])));
                    }else if (classe.compareTo("A")==0){
                        System.out.println(i);
                        monopoly.addCarteChance(new CarteAnniversaire(typeCarte, description));
                    }
                }  else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
                
            }
            System.out.println("chances : " + monopoly.getChances());
            System.out.println("communautes : " + monopoly.getCommunautes());
           
            

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

    public void setObservateur(Observateur observateur) {
        this.observateur = observateur;
    }

}
