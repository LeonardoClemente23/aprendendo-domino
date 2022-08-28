package jogo.app;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        int primeiroJogador = 0;
        long inicio = System.currentTimeMillis();
        Estatisticas estatisticas = new Estatisticas();
        SalvaDados salvaDados = new SalvaDados();
        for (int i = 1; i <= 4; i++) {
            for (int contador = 0; contador < 1000000; contador++) {
                Jogo jogo = new Jogo(true, primeiroJogador);
                jogo.rolandoJogo();
                estatisticas.contagemPlacar(jogo.isVitoriaMaoVazia(), jogo.getVencedor().getId());

                switch (i) {
                    case 1:
                        primeiroJogador = 0;
                        break;
                    case 2:
                        primeiroJogador = new Random().nextInt(4);
                        break;
                    case 3:
                        primeiroJogador = (jogo.getVencedor().getId() + 1) % 4;
                        break;
                    case 4:
                        primeiroJogador = (primeiroJogador + 1) % 4;
                        break;
                }

            }
            estatisticas.mostraPlacarFull();
            salvaDados.cabecalhoSituacao(i);
            salvaDados.gravaEstatisticas(estatisticas);
            estatisticas.getDados().clear();
        }
        long finall = System.currentTimeMillis();

        System.out.println("Tempo de execução: " + (finall - inicio) + " milissegundos.");
        salvaDados.geraRelatorio();
    }

}
