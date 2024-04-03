package gsb.vue;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gsb.modele.Podium;
import gsb.modele.dao.PodiumDao;

public class JIFPodium extends JInternalFrame
{
    protected JPanel p;
    protected JPanel pTitre;
    protected JPanel pTable;
    protected JLabel titre;
    protected JTable table;
    protected DefaultTableModel tableModel;
    public JIFPodium()
    {
        p = new JPanel();
        pTitre = new JPanel();
        pTable = new JPanel();
        Container contentPane = getContentPane();
        contentPane.add(p);
        titre = new JLabel("Podium des meilleurs visiteurs pour le mois en cours :");

        //  création de la table et de son modèle
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Rang");
        tableModel.addColumn("Matricule");
        tableModel.addColumn("Nom");
        tableModel.addColumn("Prenom");
        tableModel.addColumn("Hebdomadaire");
        tableModel.addColumn("Mensuel");
        tableModel.addRow(new Object[]{"Rang", "Matricule", "Nom", "Prenom", "Hebdomadaire", "Mensuel"});

        ArrayList<Podium> lePodium = PodiumDao.getPodium();
        for (int i = 0; i < lePodium.size(); i++)
        {
            tableModel.addRow(new Object[]
            {
                lePodium.get(i).getRang(),
                lePodium.get(i).getMatricule(),
                lePodium.get(i).getNom(),
                lePodium.get(i).getPrenom(),
                lePodium.get(i).getHebdo(),
                lePodium.get(i).getMensuel()
            });
        }

        table = new JTable(tableModel);

        pTitre.add(titre);
        pTable.add(table);
        p.add(pTitre);
        p.add(pTable);

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Consultation du Podium");
    }
}
