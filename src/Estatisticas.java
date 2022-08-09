import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estatisticas {
    // Contabiliza quantidade de vitorias de cada jogador e o tipo de vitoria,
    // por mão vazia ou por pontos.

    // ((Jogador),(Vitorias MV, vitórias Pontos)).
    private Map<Jogador, Map<String, Integer>> placar = new HashMap<>();
    private Map<String, Integer> tiposVitorias = new HashMap<>();

    public Estatisticas(List<Jogador> jogadores) {
        tiposVitorias.put("Mão vazia", 0);
        tiposVitorias.put("Pontos", 0);
        for (Jogador jogador : jogadores) {
            placar.put(jogador, tiposVitorias);

        }
    }

}
