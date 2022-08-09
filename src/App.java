import java.util.HashMap;
import java.util.Map;

// Tendencia do jogador que começa, ganhar.

public class App {
    public static void main(String[] args) {
        long contador = 0;
        Map<Integer, Integer> placar = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            placar.put(i, 0);
        }
        long inicio = System.currentTimeMillis();

        while (true) {
            Jogo jogo = new Jogo(true);
            jogo.rolandoJogo();
            placar.computeIfPresent(jogo.getVencedor().getId(), (k, v) -> v + 1);
            contador++;
            if (contador == 1000000) {
                break;
            }
        }
        long finall = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (finall - inicio) + " milissegundos.");
        System.out.println("Placar:");
        for (Map.Entry<Integer, Integer> entry : placar.entrySet()) {
            System.out.println("Joagador " + entry.getKey() + ": " + entry.getValue() + "vitorias");
        }
    }

}
