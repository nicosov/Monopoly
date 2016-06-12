/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Jeu.Controleur;
import Jeu.Message;
import Jeu.Observateur;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author carruggn
 */
public class IHM extends JFrame implements Observateur {

    private static JFrame window1;
    public static IhmMenu ihmMenu;
    //private IhmInscrireLesJoueurs Ftest;
    private Controleur controleur;

    public IHM(Controleur c) {
        controleur = c;
        controleur.setObservateur(this);

    }

    public static void afficherIhmMenu() {
        // Instanciation de la fenêtre 
        window1 = new JFrame("Monopoly");
        // Indique de sortir du programme lorsque la fenêtre est fermée par l'utilisateur
        window1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window1.setSize(500, 375);
        window1.setMinimumSize(new Dimension(500, 375));
        window1.setMaximumSize(new Dimension(500, 375));
        // Instanciation de l'IHM d'un contact
       // ihmMenu = new IhmMenu();
        // Affectation des attributs du contact aux champs de l'IHM

        // Ajout de l'IHM dans la fenêtre
        window1.getContentPane().setLayout(new BorderLayout());
        //window1.add(ihmMenu, BorderLayout.CENTER);

        // Ajout d'un bouton dans la fenêtre (pour tester la méthode getValues de IhmContact)
        window1.setVisible(true);
    }

    @Override
    public void notifier(Message msg) {
    }

}
