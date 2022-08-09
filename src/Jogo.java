import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Jogo {
    private boolean fimJogo;
    private final List<List<Integer>> pecas = new ArrayList<>();
    private List<Jogador> jogadores = new ArrayList<>();
    private Tabuleiro tabuleiro;
    private Jogador vencedor = null;

    private int rodada = 1;

    public Jogo(boolean dummyGame) {
        this.tabuleiro = new Tabuleiro();
        if (dummyGame) {
            for (int i = 0; i < 4; i++) {
                Jogador jogador = new JogadorBurro(i);
                jogadores.add(jogador);
            }
        }
        geraPecas();
        distribuiPecas();
    }

    public Jogador getVencedor() {
        return vencedor;
    }

    private void geraPecas() {
        for (int i = 0; i < 7; i++) {
            for (int j = 6; j >= i; j--) {
                List<Integer> peca = Arrays.asList(i, j);
                pecas.add(peca);

            }
        }
    }

    private void distribuiPecas() {
        Collections.shuffle(pecas);
        for (int i = 0; i < pecas.size(); i++) {
            jogadores.get(i % 4).pegaPeca(pecas.get(i));
        }
    }

    public void jogada(Jogador jogador) {
        for (List<Integer> possivelPeca : jogador.olhaMao()) {
            if (Regras.validaPeca(possivelPeca, tabuleiro)) {
                tabuleiro.adicionaPeca(possivelPeca);
                jogador.tiraPeca(possivelPeca);
                break;
            }
        }

    }

    public Jogador rodada() {
        for (Jogador jogador : jogadores) {
            if (jogador.getPrimeiroJogador() && rodada == 1) {
                continue;
            }
            if (Regras.vencedorMaoVazia(jogador)) {
                this.fimJogo = true;
                return jogador;
            }

            if (Regras.temPecaJogavel(jogador, tabuleiro)) {
                jogada(jogador);
            }
        }
        rodada++;
        return null;
    }

    public void rolandoJogo() {

        Jogador primeiroJogador = jogadores.get(0);
        List<Integer> primeiraPeca = Regras.primeiraJogada(primeiroJogador);
        tabuleiro.adicionaPeca(primeiraPeca);
        primeiroJogador.tiraPeca(primeiraPeca);
        primeiroJogador.setPrimeiroJogador(true);

        while (!fimJogo) {
            vencedor = rodada();
            if (vencedor != null) {
                break;
            }
            verificaFimJogo();
        }
        if (vencedor == null) {
            vencedor = Regras.vencedorPorPontos(jogadores);
        }
    }

    public void verificaFimJogo() {
        this.fimJogo = true;
        for (Jogador jogador : jogadores) {
            if (Regras.temPecaJogavel(jogador, tabuleiro)) {
                this.fimJogo = false;
                break;
            }
        }
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

}
