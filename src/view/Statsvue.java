package view;

import DAO.StatscommandeDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Statsvue extends JFrame {

    private StatscommandeDAO statDAO;

    public Statsvue() {
        super("Statistiques des ventes");
        statDAO = new StatscommandeDAO();

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
        double totalVentes = statDAO.calculerVentesTotales();

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
        Map<String, Integer> stats = statDAO.getQuantitesParArticle();

        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            dataset.addValue(entry.getValue(), "Articles", entry.getKey());
        }

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
        Map<String, Double> stats = statDAO.getVentesParClient();

        for (Map.Entry<String, Double> entry : stats.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

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
