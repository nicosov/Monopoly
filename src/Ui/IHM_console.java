package Ui;

import Data.Carreau;
import Data.Joueur;
import Data.ProprieteAConstruire;
import Jeu.Controleur;
import Jeu.Message;
import Jeu.Monopoly;
import Jeu.Observateur;
import java.util.ArrayList;
import java.util.Scanner;

public class IHM_console  implements Observateur{

    public static void afficherChezlui() {
        System.out.println("Vous etes sur votre propriete.");
    }

    private Controleur controleur;

    public IHM_console(Controleur c) {
        controleur = c;
        controleur.setObservateur(this);
    }

    public int afficherMenu() {
        System.out.println("\u001B[34m -------------- MENU -------------- \u001B[0m");
        System.out.println("1 - Inscrire les joueurs");
        System.out.println("2 - Commencer le jeu");
        System.out.println("3 - Quitter");
        System.out.println("\u001B[34m ---------------------------------- \u001B[0m");

        Scanner sc = new Scanner(System.in);

        boolean choix = true;
        int i = sc.nextInt();
        while (choix) {

            if (i < 1 || i > 3) {
                System.out.println("\u001B[31m Veuillez choisir entre 1 et 3. Recommencez : \u001B[0m");
                i = sc.nextInt();
            } else {
                choix = false;
            }
        }
        return i;
    }

    public int afficherDemandeNbJoueurs() {

        System.out.println("Combien de joueurs ? (de 2 à 6 joueurs)");
        Scanner sc = new Scanner(System.in);
        boolean choix = true;
        int i = sc.nextInt();
        while (choix) {

            if (i < 2 || i > 6) {
                System.out.println("\u001B[31m Veuillez choisir entre 2 et 6. Recommencez : \n");
                i = sc.nextInt();
            } else {
                choix = false;
            }
        }
        return i;
    }

    public String afficherInsJoueur() {

        System.out.println("Entrer le nom du joueur : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();

    }

    public void commencerLeJeu() {
        System.out.println("\u001B[34m ----------------------- JEU ----------------------");

        afficherMenuJeu_p();
    }

    public int afficherMenuJeu_p() {
        System.out.println("\u001B[34m --------------------- MENU -----------------------");
        System.out.println("1 - Joueur suivant");
        System.out.println("2 - Afficher les informations");
        System.out.println("3 - Afficher vos informations");
        System.out.println("4 - Construire");
        System.out.println("5 - Finir la partie");
        System.out.println("\u001B[34m --------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        int rep = 0;
        boolean choix = true;
        while (choix) {
            rep = sc.nextInt();
            switch (rep) {

                case 1:

                    choix = false;
                    break;
                case 2:
                    System.out.println("\u001B[34m --- RECAPITULATIF DES JOUEURS EN VIE ---");
                    for (Joueur j : controleur.getJoueursEnVie()) {
                        System.out.println("Joueur : " + j.getNom());
                        System.out.println(" - Argent : " + j.getCash());
                        System.out.println(" - Position : " + j.getPositionCourante().getNomCarreau());
                        System.out.println(" - Gares possédées : " + j.getNbGares());
                        System.out.println(" - Compagnie possédées : " + j.getNbComp());
                        System.out.println(" - Propriété possédées : " + j.getNbPropriete());
                    }
                    afficherMenuJeu_p();
                    break;
                case 3:
                    System.out.println("Vos informations :");
                    controleur.jCourant();
                    afficherMenuJeu_p();
                    break;
                
                case 4:
                    System.out.println(" Proprietes constructibles :");
                    ArrayList<ProprieteAConstruire> propConstructibles = controleur.proprietesConstructibles();
                    if(propConstructibles.size()>0){
                        int i = 0;
                        for(ProprieteAConstruire p : propConstructibles){
                            System.out.println(i + " " + p.getNomCarreau() + " du groupe " + p.getGroupe().getCouleur());
                            i++;
                        }
                            System.out.println("Choisissez le numero de la propriete a construire : ");
                            Scanner sc2 = new Scanner(System.in);
                            int numC = 0;    
                            numC = sc2.nextInt();
                            controleur.monopolyConstruire(propConstructibles.get(numC));
                            System.out.println("Construction effectuée !");
                    }else{
                        System.out.println("Aucune propriete constructible.");
                    }
                    afficherMenuJeu_p();
                    break;
                    
                case 5:

                    System.out.println("\u001B[34m ----- FIN DE PARTIE ----- \u001B[0m");
                    for (Joueur j : controleur.getJoueursEnVie()) {
                        System.out.println("\u001B[32m Joueur " + j.getNom() + "\u001B[0m - Argent : " + j.getCash());

                    }
                    controleur.estFini();
                    System.exit(0);
                    choix = false;
                    break;
                default:
                    System.out.println("\u001B[31m Choisir entre 1 et 3. \u001B[0m");
            }
        }

        return rep;
    }

    public int afficherMenuAchat(int prix) {
        System.out.println("\u001B[34m -------------- MENU ACHAT PROPRIETE --------------");
        System.out.println(" Voulez-vous acheter la propriete au prix de " + prix +  "?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        System.out.println(" \u001B[34m --------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();

    }

    public void afficherMort() {
        System.out.println("Le joueur est mort...");
    }

    public void afficherFin(String nomGagnant) {
        System.out.println("\u001B[31m " + nomGagnant + "\u001B[35m a gagné la partie ! Félicitation à lui !");
    }

    public void afficherInfoJoueurCourant(Joueur j) {

        System.out.println("Votre argent : " +j.getCash());
    }
    public void afficherDouble(Joueur j){
         System.out.println("le Joueur " + j.getNom() + " viens de faire un double, il rejouera...");
    }
    
        public void notifier(Message msg) 
        {
            switch(msg.type) 
            {
                case GARE:

                    System.out.println("GARE");
                    break;

                case COMPAGNIE:

                    System.out.println("COMPAGNIE");
                    break;
                case PROPRIETE_A_CONSTRUIRE:
                    System.out.println("PROPRIETE_A_CONSTRUIRE");
                    break;
                case AUTRE_CARREAU:

                    System.out.println("AUTRE CARREAU");
                    break;

                case CHANCE:

                    System.out.println("CHANCE");
                    break;
                case COMMUNAUTE:
                    System.out.println("COMMUNAUTE");
                    break;    
        }
            
    }

    public void afficherPayerCompagnie(Joueur proprietaire, int montant) {
        System.out.println("Vous etes sur la compagnie de " + proprietaire.getNom() + ". Vous payez " + montant + "." );
        }

    public void afficherPayerGare(Joueur proprietaire, int montant) {
        System.out.println("Vous etes sur la gare de " + proprietaire.getNom() + ". Vous payez " + montant + "." );
    }

    public void afficherPayerProprieteAConstruire(Joueur proprietaire, int montant) {
        System.out.println("Vous etes sur la propriete de " + proprietaire.getNom() + ". Vous payez " + montant + "." );
    }

    public void afficherTaxes(Joueur aJ, int taxe) {
                System.out.println("Vous payez " + taxe + "." );
    }
    
}
