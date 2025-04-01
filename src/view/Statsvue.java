package view;

import DAO.StatistiqueDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class Statsvue extends JFrame {

    private StatistiqueDAO statDAO;

    public Statsvue() {
        super("Statistiques des ventes");
        statDAO = new StatistiqueDAO();

        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Onglets
        JTabbedPane onglets = new JTabbedPane();
        onglets.addTab("Ventes totales", creerGraphiqueVentesTotales());
        onglets.addTab("Produits populaires", creerGraphiqueProduitsVendus());
        onglets.addTab("Par client", creerGraphiqueParClient());

        add(onglets);
    }

    private JPanel creerGraphiqueVentesTotales() {
        double totalVentes = 12547.80; // 💡 À remplacer plus tard par une vraie requête SQL

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(totalVentes, "Total", "Ventes");

        JFreeChart chart = ChartFactory.createBarChart(
                "Total des ventes",
                "",
                "Montant en €",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return new ChartPanel(chart);
    }

    private JPanel creerGraphiqueProduitsVendus() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // 🔧 Données fictives (remplacer plus tard par une vraie jointure SQL)
        dataset.addValue(120, "Bic", "Briquet");
        dataset.addValue(85, "Bic", "Stylo");
        dataset.addValue(40, "Papier", "Cahier");

        JFreeChart chart = ChartFactory.createBarChart(
                "Produits les plus vendus",
                "Article",
                "Quantité",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel creerGraphiqueParClient() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // 🔧 Données fictives (remplacer plus tard par des agrégations SQL)
        dataset.setValue("Alice", 2300);
        dataset.setValue("Bob", 1800);
        dataset.setValue("Chloe", 900);

        JFreeChart chart = ChartFactory.createPieChart(
                "Répartition des ventes par client",
                dataset,
                true, true, false);

        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Statsvue().setVisible(true));
    }
}
