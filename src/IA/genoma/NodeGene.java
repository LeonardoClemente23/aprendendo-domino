package IA.genoma;

public class NodeGene extends Gene {
    private double x, y;

    public NodeGene(int innovationNumber) {
        setInnovationNumber(innovationNumber);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NodeGene)) {
            return false;
        }
        return innovationNumber == ((NodeGene) o).getInnovationNumber();
    }

    @Override
    public int hashCode() {
        return innovationNumber;
    }
}
