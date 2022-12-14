package jogo.app;

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
    private Jogador primeiroJogador = null;

    private boolean vitoriaMaoVazia;

    public Jogo(boolean dummyGame, int idPrimeiroJogador) {
        this.tabuleiro = new Tabuleiro();
        if (dummyGame) {
            for (int i = 0; i < 4; i++) {
                Jogador jogador = new JogadorBurro(i);
                jogadores.add(jogador);
            }
        }
        this.primeiroJogador = jogadores.get(idPrimeiroJogador);
        this.primeiroJogador.setPrimeiroJogador(true);
        geraPecas();
        distribuiPecas();
    }

    public boolean isVitoriaMaoVazia() {
        return vitoriaMaoVazia;
    }

    public Jogador isPrimeiroJogador() {
        return this.primeiroJogador;
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
            if (Regras.validaPeca(possivelPeca, this.tabuleiro)) {
                return possivelPeca;
            }
        }
        return null;
    }

    private void colocaPecaTabuleiro(Jogador jogador, List<Integer> peca) {
        this.tabuleiro.adicionaPeca(peca);
        this.tabuleiro.setUltimaPeca(peca);
        jogador.tiraPeca(peca);

    }

    private void jogada(Jogador jogador) {
        List<Integer> peca = pecaJogada(jogador);
        if (peca != null) {
            colocaPecaTabuleiro(jogador, peca);
        }

    }

    private void primeiraJogada() {
        List<Integer> primeiraPeca = Regras.primeiraPecaJogada(this.primeiroJogador);
        colocaPecaTabuleiro(primeiroJogador, primeiraPeca);

    }

    private Jogador rodada() {
        for (int i = 0; i < 4; i++) {
            int idJogador = (i + primeiroJogador.getId()) % 4;
            Jogador jogador = this.jogadores.get(idJogador);

            if (jogador.isPrimeiroJogador() && rodada == 1) {
                primeiraJogada();
                continue;
            }

            if (Regras.temPecaJogavel(jogador, this.tabuleiro)) {
                jogada(jogador);
            }

            if (Regras.vencedorMaoVazia(jogador)) {
                this.fimJogo = true;
                return jogador;
            }
        }
        this.rodada++;
        return null;
    }

    private boolean verificaFimJogo() {
        if (Regras.rodadaPossivel(this.tabuleiro, jogadores)) {
            return false;
        }
        if (Regras.possivelAbrirJogo(this.tabuleiro)) {
            this.tabuleiro.abreJogo();
            if (Regras.rodadaPossivel(this.tabuleiro, jogadores)) {
                return false;
            }
        }
        return true;
    }

    public void rolandoJogo() {

        while (!this.fimJogo) {
            vencedor = rodada();
            if (vencedor != null) {
                vitoriaMaoVazia = true;
                break;
            }
            this.fimJogo = verificaFimJogo();
        }
        if (vencedor == null) {
            vitoriaMaoVazia = false;
            vencedor = Regras.vencedorPorPontos(jogadores);
        }

    }

}
