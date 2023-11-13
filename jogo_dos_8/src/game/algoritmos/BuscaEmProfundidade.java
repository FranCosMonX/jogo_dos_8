package game.algoritmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import game.Tabuleiro;
import game.Movimento;

public class BuscaEmProfundidade {
	private Stack<Tabuleiro> fronteira = new Stack<>();
	private List<Tabuleiro> visitado = new ArrayList<Tabuleiro>();
	
	public BuscaEmProfundidade(Tabuleiro jogo) {
		fronteira.push(jogo);
		execute();
	}
	
	private boolean execute() {
		int contador_Seguro = 0;
		while(true) {
			Tabuleiro t = fronteira.pop();
			visitado.add(t);
			if(t.isSolucao())
				return true;
			
			int[] p = Tabuleiro.getLocalizacaoDoVazio(t);
			int x = p[1];
			int y = p[0];
			System.out.println("x: " + x + " y: " + y);
			System.out.println(t);
			
			if(Movimento.podeMoverCima(p)) {
				Tabuleiro novo = Movimento.moverParaCima(t, p);
				if(!visitado.contains(novo)) {
					fronteira.push(novo);
					visitado.add(novo);
				}
			}
			if(Movimento.podeMoverEsq(p)) {
				Tabuleiro novo = new Tabuleiro();
				novo = Movimento.moverParaEsquerda(t, p);
				if(!visitado.contains(novo)) {
					fronteira.push(novo);
					visitado.add(novo);
				}
			}
			if(Movimento.podeMoverDir(p, t.getSize())) {
				Tabuleiro novo = new Tabuleiro();
				novo = Movimento.moverParaDireita(t, p);
				if(!visitado.contains(novo)) {
					fronteira.push(novo);
					visitado.add(novo);
				}
			}
			if(Movimento.podeMoverBaixo(p, t.getSize())) {
				Tabuleiro novo = Movimento.moverParaBaixo(t, p);
				if(!visitado.contains(novo)) {
					fronteira.push(novo);
					visitado.add(novo);
				}
			}
			
			if(contador_Seguro > 1000) {
				break;
			}
			contador_Seguro++;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
//		Tabuleiro a = new Tabuleiro(3);
		//System.out.println(a);
		
		int [][] sol = {{1,2,3},{0,4,5},{7,8,6}};
		Tabuleiro solucionavel = new Tabuleiro(sol);
		new BuscaEmProfundidade(solucionavel);
	}
}
