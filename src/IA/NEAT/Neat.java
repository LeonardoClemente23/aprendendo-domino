package IA.NEAT;

import java.util.HashMap;

import IA.estruturasDados.RandomHashSet;
import IA.genoma.ConnectionGene;
import IA.genoma.Genome;
import IA.genoma.NodeGene;

public class Neat {

    public static final int MAX_NODES = (int) Math.pow(2, 20);

    private double c1 = 1, c2 = 1, c3 = 1;

    private HashMap<ConnectionGene, ConnectionGene> allConnections = new HashMap<>();
    private RandomHashSet<NodeGene> allNodes = new RandomHashSet<>();
    private int maxClients;
    private int inputSize;
    private int outputSize;

    public Neat(int maxClients, int inputSize, int outputSize) {
        this.reset(maxClients, inputSize, outputSize);
    }

    public double getC1() {
        return c1;
    }

    public double getC2() {
        return c2;
    }

    public double getC3() {
        return c3;
    }

    public int getInputSize() {
        return inputSize;
    }

    public int getOutputSize() {
        return outputSize;
    }

    public NodeGene getNode() {
        NodeGene node = new NodeGene(allNodes.size() + 1);
        allNodes.add(node);
        return node;
    }

    public NodeGene getNode(int id) throws Exception {
        if (id <= allNodes.size()) {
            return allNodes.getElement(id - 1);
        }
        return getNode();
    }

    public void reset(int maxClients, int inputSize, int outputSize) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.maxClients = maxClients;

        allConnections.clear();
        allNodes.clear();

        for (int i = 0; i < inputSize; i++) {
            NodeGene node = getNode();
            node.setX(0.1);
            node.setY(i + 1 / (double) (inputSize + 1));
        }

        for (int i = 0; i < outputSize; i++) {
            NodeGene node = getNode();
            node.setX(0.9);
            node.setY(i + 1 / (double) (outputSize + 1));
        }
    }

    public Genome emptyGenome() throws Exception {
        Genome genome = new Genome(this);
        for (int i = 0; i < inputSize + outputSize; i++) {
            genome.getNodes().add(getNode(i + 1));
        }
        return genome;
    }

    public ConnectionGene getConnection(NodeGene node1, NodeGene node2) {
        ConnectionGene connectionGene = new ConnectionGene(node1, node2);

        if (allConnections.containsKey(connectionGene)) {
            connectionGene.setInnovationNumber(allConnections.get(connectionGene).getInnovationNumber());
        } else {
            allConnections.put(connectionGene, connectionGene);
        }

        return connectionGene;
    }

    public ConnectionGene getConnection(ConnectionGene connection) {
        ConnectionGene connectionGene = new ConnectionGene(connection.getFrom(), connection.getTo());
        connectionGene.setWeight(connection.getWeight());
        connectionGene.setEnabled(connection.isEnabled());
        return connectionGene;
    }

    public static void main(String[] args) {
        Neat neat = new Neat(100, 3, 3);

        Genome genome;
        try {
            genome = neat.emptyGenome();
            System.out.println(genome.getNodes().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
