package gsb.vue;

import javax.swing.*;
//import java.awt.Dimension;
import java.awt.Toolkit;

public class Accueil extends JFrame
{
    public Accueil()
    {
        // Définir le titre de la fenêtre
        setTitle("Accueil");

        // Définir la taille de la fenêtre (largeur, hauteur)
        setSize(800, 600);

        // Définir le logo de la fenêtre
        setIconImage(Toolkit.getDefaultToolkit().getImage(Accueil.class.getResource("/gsb/ressources/gsb_log.png")));

        // Définir l'opération par défaut lorsque vous cliquez sur le bouton de fermeture de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Rendre la fenêtre visible
        setVisible(true);
    }
}