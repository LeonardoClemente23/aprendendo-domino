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
            for (int i = 1; i <= 4; i++) {
                Jogador jogador = new JogadorBurro(i);
                jogadores.add(jogador);
            }
        }
        geraPecas();
        distribuiPecas();
    }

    public Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    public List<Jogador> getJogadores() {
        return this.jogadores;
    }

    public Jogador getVencedor() {
        return this.vencedor;
    }

    private void geraPecas() {
        for (int i = 0; i < 7; i++) {
            for (int j = 6; j >= i; j--) {
                List<Integer> peca = Arrays.asList(i, j);
                this.pecas.add(peca);

            }
        }
    }

    private void distribuiPecas() {
        Collections.shuffle(this.pecas);
        for (int i = 0; i < this.pecas.size(); i++) {
            this.jogadores.get(i % 4).pegaPeca(this.pecas.get(i));
        }
    }

    private List<Integer> pecaJogada(Jogador jogador) {
        for (List<Integer> possivelPeca : jogador.olhaMao()) {
            if (Regras.validaPeca(possivelPeca, tabuleiro)) {
                return possivelPeca;
            }
        }
        return null;
    }

    private void colocaPecaTabuleiro(Jogador jogador, List<Integer> peca) {
        this.tabuleiro.adicionaPeca(peca);
        jogador.tiraPeca(peca);

    }

    private void jogada(Jogador jogador) {
        List<Integer> peca = pecaJogada(jogador);
        if (peca != null) {
            colocaPecaTabuleiro(jogador, peca);
        }

    }

    private void primeiraJogada() {
        Jogador primeiroJogador = this.jogadores.get(0);
        List<Integer> primeiraPeca = Regras.primeiraPecaJogada(primeiroJogador);

        colocaPecaTabuleiro(primeiroJogador, primeiraPeca);
        primeiroJogador.setPrimeiroJogador(true);
    }

    private Jogador rodada() {
        for (Jogador jogador : this.jogadores) {
            if (jogador.getPrimeiroJogador() && rodada == 1) {
                continue;
            }
            if (Regras.vencedorMaoVazia(jogador)) {
                this.fimJogo = true;
                return jogador;
            }

            if (Regras.temPecaJogavel(jogador, this.tabuleiro)) {
                jogada(jogador);
            }
        }
        this.rodada++;
        return null;
    }

    private void verificaFimJogo() {
        this.fimJogo = true;
        if (Regras.rodadaPossivel(tabuleiro, jogadores)) {
            this.fimJogo = false;
            return;
        }
    }

    public void rolandoJogo() {
        primeiraJogada();

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

}
