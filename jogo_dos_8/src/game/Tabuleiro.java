package game;

import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabuleiro {
	private int tamanho = 0;
	private int[][] matriz;
	private int[][] solucao;
	
	/**
	 * construtores da classe.
	 */
	public Tabuleiro() {}
	public Tabuleiro(int tamanho) {
		this.tamanho = tamanho;
		matriz = new int[tamanho][tamanho];
		matriz = gerarTabuleiro(tamanho).getMatriz();
		solucao = new int[tamanho][tamanho];
		solucao = gerarTabuleiroSolucao(tamanho).getMatriz();
	}
	public Tabuleiro(int[][] estadoInicial) {
		this.tamanho = estadoInicial.length;
		matriz = new int[tamanho][tamanho];
		matriz = estadoInicial;
		solucao = new int[tamanho][tamanho];
		solucao = gerarTabuleiroSolucao(tamanho).getMatriz();
	}
	
	/**
	 * metodo exclusivo para implementação de funcoes auxiliares. Perigoso já que nao cria uma solução para o tabuleiro
	 * @param tamanho
	 * @param privado
	 */
//	private Tabuleiro(int tamanho, boolean privado) {
//		this.tamanho = tamanho;
//		matriz = new int[tamanho][tamanho];
//		matriz = gerarTabuleiro(tamanho).getMatriz();
//	}
	/**
	 * metodo exclusivo para implementação de funcoes auxiliares. Perigoso já que nao cria uma solução para o tabuleiro
	 * @param estadoInicial
	 * @param privado
	 */
	private Tabuleiro(int[][] estadoInicial,boolean privado) {
		this.tamanho = estadoInicial.length;
		matriz = new int[tamanho][tamanho];
		matriz = estadoInicial;
	}
	/**
	 * verifica se é solucao. É feita a comparacaode cada elemento da matriz.
	 * @return
	 */
	public boolean isSolucao() {
		return this.equals(new Tabuleiro(solucao,true));
	}
	/**
	 * obter o tamanho ao quadrado do tabuleiro
	 * @return
	 */
	public int getSize() {
		return tamanho;
	}
	/**
	 * obter a matriz do tabuleiro
	 * @return
	 */
	public int[][] getMatriz(){
		return this.matriz;
	}
	/**
	 * obter a matriz solucao
	 * @return
	 */
	public int[][] getSolucao(){
		return solucao;
	}
	/**
	 * Gerar uma tabuleiro inicial
	 * @param tamanho
	 * @return
	 */
	public static Tabuleiro gerarTabuleiro(int tamanho){
		int[][] a = new int[tamanho][tamanho];
		
		List<Integer> numeros = new ArrayList<>();
		for (int i = 0; i < tamanho * tamanho; i++) {
			numeros.add(i);
		}
		// Embaralhar a lista para garantir que os números sejam aleatórios
		Collections.shuffle(numeros);
		
		for(int i = 0 ; i < tamanho ; i ++) {
			for(int j = 0 ; j < tamanho ; j++) {
				a[i][j] = numeros.get(0);
				numeros.remove(0);
			}
		}
		return new Tabuleiro(a,true);
	}
	/**
	 * gerar uma solucao simples para o tabuleiro
	 * @param tamanho
	 * @return
	 */
	public static Tabuleiro gerarTabuleiroSolucao(int tamanho){
		int[][] a = new int[tamanho][tamanho];
		
		int numeros = 1;
		
		for(int i = 0 ; i < tamanho ; i ++) {
			for(int j = 0 ; j < tamanho ; j++) {
				a[i][j] = numeros == tamanho * tamanho ? 0 : numeros;
				numeros++;
			}
		}
		return new Tabuleiro(a,true);
	}
	
	public static Tabuleiro copy(Tabuleiro t) {
		int[][] a = t.getMatriz();
		int tamanho = a.length;
		
		int[][] nova = new int[tamanho][tamanho];
		for(int i = 0 ; i < tamanho ; i++) {
			for(int j = 0 ; j < tamanho ; j++) {
				nova[i][j] = a[i][j];
			}
		}
		return new Tabuleiro(nova);
	}
	/**
	 * localizar a peça em branco
	 * @param t
	 * @return
	 */
	public static int[] getLocalizacaoDoVazio(Tabuleiro t) {
		int[] posicao = new int[2];
		//System.err.println(t);
		int tamanho = t.getSize();
		int[][] m = t.getMatriz();
		for(int i = 0 ; i < tamanho ; i++) {
			for(int j = 0 ; j < tamanho ; j++) {
				if(m[i][j] == 0) {
					posicao[0] = i; //y
					posicao[1] = j; //x
					return posicao;
				}
			}
		}
		
		throw new InaccessibleObjectException();
	}
	
	@Override
	public String toString() {
		String str = "";
		
		for(int i = 0 ; i < tamanho ; i ++) {
			for(int j = 0 ; j < tamanho ; j++) {
				str += j == tamanho - 1 ? "" + this.matriz[i][j]: this.matriz[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}
	
	@Override
	public boolean equals(Object obj) {
		Tabuleiro t = new Tabuleiro();
		try {
			t = (Tabuleiro) obj;
		} catch (Exception e) {
			System.err.println("O argumento nao é um Tabuleiro: " + e);
			return false;
		}
		int[][] a = new int[this.tamanho][this.tamanho];
		a = t.getMatriz();
		
		for(int i = 0 ; i < this.tamanho ; i++) {
			for(int j = 0 ; j < this.tamanho ;j++) {
				if(this.matriz[i][j] != a[i][j])
					return false;
			}
		}
		
		return true;
	}
	
//	public static void main(String[] args) {
//		Tabuleiro a = new Tabuleiro(3);
//		Tabuleiro b = new Tabuleiro(a.getMatriz());
//		System.out.println(a + " " + b);
//		//System.out.println(a.equals(b));
//		System.out.println(Tabuleiro.gerarTabuleiroSolucao(3));
//		
//		System.out.println("Altera genetic");
//		Tabuleiro c = Tabuleiro.copy(b);
//		System.out.println("c: \n" + c);
//		b.getMatriz()[1][0] = 999;
//		System.out.println("b modificado = \n" + b);
//		System.out.println("C apos a mudança no b: \n" + c);
//		
//		System.out.println("verifica se e solucao (false): " + a.isSolucao());
//		int [][] sol = {{1,2,3},{4,5,6},{7,8,0}};
//		Tabuleiro solucionavel = new Tabuleiro(sol);
//		System.out.println("Verifica se e solucao (True): " + solucionavel.isSolucao());
//		System.out.println(solucionavel);
//		System.out.println(new Tabuleiro(solucionavel.getSolucao()));
//		
//		System.out.println(Tabuleiro.getLocalizacaoDoVazio(a)[0] + " " + Tabuleiro.getLocalizacaoDoVazio(a)[1]);
//	}
}
