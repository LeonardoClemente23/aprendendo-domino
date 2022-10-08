package IA.genoma;

import IA.NEAT.Neat;
import IA.estruturasDados.RandomHashSet;

public class Genome {
    private RandomHashSet<ConnectionGene> connections = new RandomHashSet<>();
    private RandomHashSet<NodeGene> nodes = new RandomHashSet<>();

    private Neat neat;

    public Genome(Neat neat) {
        this.neat = neat;
    }

    public RandomHashSet<ConnectionGene> getConnections() {
        return connections;
    }

    public RandomHashSet<NodeGene> getNodes() {
        return nodes;
    }

    public Neat getNeat() {
        return neat;
    }

    public double distance(Genome g2) throws Exception {
        Genome g1 = this;

        int highestInnovationGene1 = g1.getConnections().getElement(g1.getConnections().size() - 1)
                .getInnovationNumber();
        int highestInnovationGene2 = g2.getConnections().getElement(g2.getConnections().size() - 1)
                .getInnovationNumber();

        if (highestInnovationGene1 < highestInnovationGene2) {
            Genome g = g1;
            g1 = g2;
            g2 = g;
        }

        int indexG1 = 0;
        int indexG2 = 0;

        int disjoint = 0;
        int excess = 0;
        double weightDiff = 0;
        int similar = 0;

        while (indexG1 < g1.getConnections().size() && indexG2 < g2.getConnections().size()) {
            ConnectionGene gene1 = g1.getConnections().getElement(indexG1);
            ConnectionGene gene2 = g2.getConnections().getElement(indexG2);

            int innovationNumber1 = gene1.getInnovationNumber();
            int innovationNumber2 = gene2.getInnovationNumber();

            if (innovationNumber1 == innovationNumber2) {
                // similar gene
                similar++;
                weightDiff += Math.abs(gene1.getWeight() - gene2.getWeight());
                indexG1++;
                indexG2++;

            } else if (innovationNumber1 > innovationNumber2) {
                // disjoint gene of b
                disjoint++;
                indexG2++;
            } else {
                // disjoint gene of a
                disjoint++;
                indexG1++;
            }
        }

        weightDiff /= similar;
        excess = g1.getConnections().size() - indexG1;

        double n = Math.max(g1.getConnections().size(), g2.getConnections().size());
        if (n < 20) {
            n = 1;
        }

        return (neat.getC1() * disjoint / n) + (neat.getC2() * excess / n) + (neat.getC3() * weightDiff);
    }

    public static Genome crossOver(Genome g1, Genome g2) throws Exception {
        Neat neat = g1.getNeat();

        Genome genome = neat.emptyGenome();

        int indexG1 = 0;
        int indexG2 = 0;

        while (indexG1 < g1.getConnections().size() && indexG2 < g2.getConnections().size()) {
            ConnectionGene gene1 = g1.getConnections().getElement(indexG1);
            ConnectionGene gene2 = g2.getConnections().getElement(indexG2);

            int innovationNumber1 = gene1.getInnovationNumber();
            int innovationNumber2 = gene2.getInnovationNumber();

            if (innovationNumber1 == innovationNumber2) {
                // similar gene
                // Crossover
                if (Math.random() > 0.5) {
                    genome.getConnections().add(neat.getConnection(gene1));
                } else {
                    genome.getConnections().add(neat.getConnection(gene2));
                }
                indexG1++;
                indexG2++;
            }
            if (innovationNumber1 > innovationNumber2) {
                // disjoint gene of b
                indexG2++;
            } else {
                // disjoint gene of a
                genome.getConnections().add(neat.getConnection(gene1));
                indexG1++;
            }
        }

        while (indexG1 < g1.getConnections().size()) {
            ConnectionGene gene1 = g1.getConnections().getElement(indexG1);
            genome.getConnections().add(neat.getConnection(gene1));
            indexG1++;
        }

        for (ConnectionGene connectGenes : genome.getConnections().getData()) {
            genome.getNodes().add(connectGenes.getFrom());
            genome.getNodes().add(connectGenes.getTo());
        }
        return genome;
    }

    public void mutate() {

    }

}
