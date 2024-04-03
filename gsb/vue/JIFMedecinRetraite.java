package gsb.vue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import gsb.modele.dao.RetraiteDao;

public class JIFMedecinRetraite extends JInternalFrame implements ActionListener 
{
    protected JPanel p;
    protected JLabel titre;
    protected JComboBox<String> JCMedecins;
    protected JButton bouton;

    public JIFMedecinRetraite()
    {
        p = new JPanel();
        titre = new JLabel("Médecins actuellement en activité :");

        JCMedecins = new JComboBox<String>();

        ArrayList<String> options = RetraiteDao.getListeMedecinsActifs();
        for (String option : options)
        {
            JCMedecins.addItem(option);
        }
        bouton = new JButton("Mettre à la retraite");
        bouton.addActionListener(this);

        p.add(titre);
        p.add(JCMedecins);
        p.add(bouton);
        Container contentPane = getContentPane();
        contentPane.add(p);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Mettre un médecin en retraite");
        // ajout d'un écouteur d'évènement pour la fermeture de la fenêtre
        addInternalFrameListener(new InternalFrameAdapter(){
            public void  internalFrameClosing(InternalFrameEvent e) {
                          //le code que tu veux exécuter à la fermeture de la JInternalFrame
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        int i = JCMedecins.getSelectedIndex();
        String codeMed = JCMedecins.getSelectedItem().toString().split(" ")[0];
        RetraiteDao.setRetraite(codeMed);
        JCMedecins.removeItemAt(i);
    }
}
