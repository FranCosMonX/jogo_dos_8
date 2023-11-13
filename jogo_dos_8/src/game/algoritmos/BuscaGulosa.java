package game.algoritmos;

import java.util.ArrayList;
import java.util.List;

import game.Tabuleiro;
import game.Auxiliar;
import game.Movimento;

public class BuscaGulosa {
	private List<Tabuleiro> fronteira = new ArrayList<Tabuleiro>();
	private List<Tabuleiro> visitado = new ArrayList<Tabuleiro>();
	
	public BuscaGulosa(Tabuleiro jogo) {
		fronteira.add(jogo);
		execute();
	}
	
	private boolean execute() {
		int contador_Seguro = 0;
		while(true) {
			Tabuleiro t = fronteira.get(0);
			fronteira.remove(0);
			visitado.add(t);
			if(t.isSolucao()) break;
			
			int[] p = Tabuleiro.getLocalizacaoDoVazio(t);
			int x = p[1];
			int y = p[0];
			System.out.println("x: " + x + " y: " + y);
			System.out.println(t);
			
			if(Movimento.podeMoverCima(p)) {
				Tabuleiro novo = Movimento.moverParaCima(t, p);
				if(!visitado.contains(novo)) {
					fronteira.add(novo);
					visitado.add(novo);
				}
			}
			if(Movimento.podeMoverEsq(p)) {
				Tabuleiro novo = new Tabuleiro();
				novo = Movimento.moverParaEsquerda(t, p);
				if(!visitado.contains(novo)) {
					fronteira.add(novo);
					visitado.add(novo);
				}
			}
			if(Movimento.podeMoverDir(p, t.getSize())) {
				Tabuleiro novo = new Tabuleiro();
				novo = Movimento.moverParaDireita(t, p);
				if(!visitado.contains(novo)) {
					fronteira.add(novo);
					visitado.add(novo);
				}
			}
			if(Movimento.podeMoverBaixo(p, t.getSize())) {
				Tabuleiro novo = Movimento.moverParaBaixo(t, p);
				if(!visitado.contains(novo)) {
					fronteira.add(novo);
					visitado.add(novo);
				}
			}
			fronteira = Auxiliar.sort(fronteira);
			
			if(contador_Seguro > 1000) {
				return false;
			}
			contador_Seguro++;
		}
		
		System.out.println("Verificou " + contador_Seguro + " tabuleiros até encontrar a solução");
		return true;
	}
	
	public static void main(String[] args) {
//		Tabuleiro a = new Tabuleiro(3);
		//System.out.println(a);
		
		int [][] sol = {{1,2,0},{4,5,3},{7,8,6}};
		Tabuleiro solucionavel = new Tabuleiro(sol);
		new BuscaGulosa(solucionavel);
	}
}

