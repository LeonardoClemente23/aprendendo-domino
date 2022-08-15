
// Tendencia do jogador que começa, ganhar.

public class App {
    public static void main(String[] args) {
        long contador = 0;
        long inicio = System.currentTimeMillis();
        Estatisticas estatisticas = new Estatisticas();
        while (true) {
            Jogo jogo = new Jogo(true);
            jogo.rolandoJogo();
            estatisticas.contagemPlacar(jogo.getVencedor().getId(), jogo.isVitoriaMaoVazia());
            contador++;
            if (contador == 1000000) {
                break;
            }
        }
        long finall = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (finall - inicio) + " milissegundos.");
        estatisticas.mostraPlacarFull();
    }

}
