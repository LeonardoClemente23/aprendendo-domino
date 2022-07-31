public class App {
    public static void main(String[] args) {
        long contador = 0;
        // Jogo jogo = new Jogo(true);
        // jogo.getJogadores().stream().forEach((jogador) ->
        // System.out.println(jogador.olhaMao()));
        // System.out.println(jogo.getTabuleiro().getPecasNaMesa());
        long inicio = System.currentTimeMillis();
        while (true) {
            Jogo jogo = new Jogo(true);
            jogo.rolandoJogo();
            contador++;
            if (contador == 10000) {
                break;
            }
        }
        long finall = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (finall - inicio) + " milissegundos.");
    }
}
