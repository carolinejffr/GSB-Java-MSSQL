package gsb.vue;

import javax.swing.*;

public class myFrame extends JFrame
{
    public myFrame()
    {
        // Définir le titre de la fenêtre
        setTitle("Ma Fenêtre JFrame");

        // Définir la taille de la fenêtre (largeur, hauteur)
        setSize(400, 300);

        // Définir l'opération par défaut lorsque vous cliquez sur le bouton de fermeture de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer un composant (par exemple, un label) à ajouter à la fenêtre
        JLabel label = new JLabel("Bonjour, Monde!");

        // Ajouter le composant à la fenêtre
        getContentPane().add(label);

        // Rendre la fenêtre visible
        setVisible(true);

    }

}
