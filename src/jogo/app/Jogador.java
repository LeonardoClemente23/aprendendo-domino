package jogo.app;

import java.util.List;

public interface Jogador {

    public int getId();

    public List<Integer> escolhePeca(int posicao);

    public void pegaPeca(List<Integer> peca);

    public void tiraPeca(List<Integer> peca);

    public List<List<Integer>> olhaMao();

    public void setPrimeiroJogador(boolean primeirojogador);

    public boolean getPrimeiroJogador();

}
