package jogo.app;

//import java.util.Random;

// Tendencia do jogador que começa, ganhar.

public class App {
    public static void main(String[] args) {
        long contador = 0;
        int primeiroJogador = 0;
        long inicio = System.currentTimeMillis();
        Estatisticas estatisticas = new Estatisticas();
        while (true) {
            Jogo jogo = new Jogo(true, primeiroJogador);
            jogo.rolandoJogo();
            estatisticas.contagemPlacar(jogo.isVitoriaMaoVazia(), jogo.getVencedor().getId());
            contador++;
            if (contador == 1000000) {
                break;
            }
            // primeiroJogador = new Random().nextInt(4);
            // primeiroJogador = (jogo.getVencedor().getId() + 1) % 4;
            // primeiroJogador = (primeiroJogador + 1) % 4;
        }
        long finall = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (finall - inicio) + " milissegundos.");
        estatisticas.mostraPlacarFull();
    }

}
