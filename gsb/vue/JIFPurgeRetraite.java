package gsb.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import gsb.modele.dao.RetraiteDao;

public class JIFPurgeRetraite extends JInternalFrame implements ActionListener {
    protected JPanel p;
    protected JLabel titre;
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JButton bouton;

    public JIFPurgeRetraite() {
        p = new JPanel();
        Container contentPane = getContentPane();
        contentPane.add(p);
        titre = new JLabel("Médecins en retraite non-archivés :");
        ArrayList<String> listeRetraite = RetraiteDao.listeRetraites();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Médecin");
        for (String unRetraite : listeRetraite) {
            tableModel.addRow(new Object[] { unRetraite });
        }
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        // Ajuster la taille du JScrollPane
        scrollPane.setPreferredSize(new Dimension(300, 200));
        bouton = new JButton("Archiver ces médecins");
        bouton.addActionListener(this);
        p.setLayout(new BorderLayout()); // Utilisation d'une disposition BorderLayout
        p.add(titre, BorderLayout.NORTH); // Titre en haut
        p.add(scrollPane, BorderLayout.CENTER); // JScrollPane au centre
        p.add(bouton, BorderLayout.SOUTH); // Bouton en bas

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Purger les retraités");
        pack(); // Ajuster la taille de la fenêtre en fonction des composants
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RetraiteDao.purger();
        for (int i = 0; i < tableModel.getRowCount(); i++)
        {
            tableModel.removeRow(i);
        }
    }
}
