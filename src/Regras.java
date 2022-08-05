import java.util.ArrayList;
import java.util.List;

public class Regras {

    public static List<Integer> primeiraJogada(Jogador jogador) {
        int maiorSomaPeca = 0;
        List<Integer> maiorPeca = new ArrayList<>();
        for (List<Integer> peca : jogador.olhaMao()) {
            if (peca.get(0).equals(peca.get(1))) {
                return peca;
            }
            int somaPeca = peca.stream().mapToInt(Integer::intValue).sum();
            if (somaPeca > maiorSomaPeca) {
                maiorSomaPeca = somaPeca;
                maiorPeca = peca;
            }
        }
        return maiorPeca;
    }

    public static boolean validaPeca(List<Integer> peca, Tabuleiro tabuleiro) {
        Integer cabeca = tabuleiro.getCabeca();
        Integer rabo = tabuleiro.getRabo();

        for (Integer numero : peca) {
            if (numero.equals(cabeca) || numero.equals(rabo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean temPecaJogavel(Jogador jogador, Tabuleiro tabuleiro) {
        if (!jogador.olhaMao().isEmpty()) {
            for (List<Integer> peca : jogador.olhaMao()) {
                if (validaPeca(peca, tabuleiro)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rodadaNaoPossivel(Tabuleiro tabuleiro, List<Jogador> jogadores) {
        for (Jogador jogador : jogadores) {
            if (Regras.temPecaJogavel(jogador, tabuleiro)) {
                return false;

            }
        }
        return true;
    }

}
