import java.util.ArrayList;
import java.util.List;

public class JogadorReal implements Jogador {
    private int id = -1;
    private List<List<Integer>> pecasJogador = new ArrayList<>();
    private boolean primeiroJogador;

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
    public int getId() {
        return id;
    }

    public List<List<Integer>> getPecasJogador() {
        return pecasJogador;
    }
}
