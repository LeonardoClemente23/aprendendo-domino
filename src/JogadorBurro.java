import java.util.ArrayList;
import java.util.List;

public class JogadorBurro implements Jogador {
    private int id;

    private List<List<Integer>> pecasJogador = new ArrayList<>();
    private boolean primeiroJogador;

    public JogadorBurro(int id) {
        this.primeiroJogador = false;
        this.id = id;

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<Integer> escolhePeca(int posicao) {
        return pecasJogador.get(posicao);
    }

    @Override
    public void pegaPeca(List<Integer> peca) {
        pecasJogador.add(peca);

    }

    @Override
    public List<List<Integer>> olhaMao() {
        return pecasJogador;
    }

    @Override
    public void tiraPeca(List<Integer> peca) {
        pecasJogador.remove(peca);
    }

    public void setPrimeiroJogador(boolean primeiroJogador) {
        this.primeiroJogador = primeiroJogador;
    }

    @Override
    public boolean getPrimeiroJogador() {
        return primeiroJogador;
    }

    @Override
    public String toString() {
        return "Jogador " + this.id;
    }
}
