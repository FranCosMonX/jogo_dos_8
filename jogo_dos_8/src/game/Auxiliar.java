package game;

import java.util.ArrayList;
import java.util.List;

public class Auxiliar {
	/**
	 * ordena a lista do maior para o menor levando em consideração o valor atribuido ao tabuleiro
	 * @param lista
	 * @return
	 */
	public static List<Tabuleiro> sort(List<Tabuleiro> lista){
		List<Tabuleiro> nova = new ArrayList<Tabuleiro>();
		//System.err.println("Size lista: " + lista.size());
		System.out.println("listaIni:\n" + lista);
		for(int i = 0 ; i < lista.size()-1 ; i++) {
			Tabuleiro menor = lista.get(i);
			for(int j = 1 ; j < lista.size() - i ; j++) {
				//System.out.println("[" + i + "]["+j+"]");
				if(lista.get(j).getValorHeuristica() < menor.getValorHeuristica()) {
					menor = lista.get(j);
					lista.set(j, lista.get(i));
					lista.set(i, menor);
				}
			}
			nova.add(menor);
		}
		System.err.println("listaFim:\n" + lista);
//		for(Tabuleiro t : lista) {
//			System.out.println("valor " + t.getValorHeuristica());
//		}
		return lista;
	}
}
