package IA.genoma;

import IA.NEAT.Neat;

public class ConnectionGene extends Gene {
    private NodeGene from;
    private NodeGene to;

    private double weight;
    private boolean enabled = true;

    public ConnectionGene(NodeGene from, NodeGene to) {
        this.from = from;
        this.to = to;
        setInnovationNumber(innovationNumber);
    }

    public NodeGene getFrom() {
        return from;
    }

    public void setFrom(NodeGene from) {
        this.from = from;
    }

    public NodeGene getTo() {
        return to;
    }

    public void setTo(NodeGene to) {
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ConnectionGene)) {
            return false;
        }
        ConnectionGene c = (ConnectionGene) o;

        return (from.equals(c.from)) && (to.equals(c.to));
    }

    @Override
    public int hashCode() {
        return from.getInnovationNumber() * Neat.MAX_NODES * to.getInnovationNumber();

    }

}
