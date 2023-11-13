package game;

public class Movimento {
	/**
	 * tem peça a esquerda do quadrado vazio
	 * @param f
	 * @return
	 */
	public static boolean podeMoverEsq(int[] f) {
		return f[1] > 0;
	}
	/**
	 * tem peça a direita do quadrado vazio
	 * @param f
	 * @param tamanho
	 * @return
	 */
	public static boolean podeMoverDir(int[] f, int tamanho) {
		return f[1] < tamanho - 1;
	}
	/**
	 * tem peça em cima do quadrado vazio
	 * @param f
	 * @return
	 */
	public static boolean podeMoverCima(int[] f) {
		return f[0] > 0;
	}
	/**
	 * tem peça em baixo do quadrado vazio
	 * @param f
	 * @param tamanho
	 * @return
	 */
	public static boolean podeMoverBaixo(int[] f, int tamanho) {
		return f[0] < tamanho - 1;
	}
	/**
	 * Pega a peça a esquerda do quadrado vazio e troca a posição entre eles
	 * @param t
	 * @param pos
	 * @return
	 */
	public static Tabuleiro moverParaEsquerda(Tabuleiro t, int[] pos) {
		Tabuleiro novo = Tabuleiro.copy(t);
		int y = pos[0];
		int x = pos[1];
		
		novo.getMatriz()[y][x-1] = t.getMatriz()[y][x];
		novo.getMatriz()[y][x] = t.getMatriz()[y][x-1];
		
//		System.out.println("original \n" + t);
//		System.out.println("copia \n" + novo);
		
		return novo;
	}
	/**
	 * Pega a peça a direita do quadrado vazio e troca a posição entre eles
	 * @param t
	 * @param pos
	 * @return
	 */
	public static Tabuleiro moverParaDireita(Tabuleiro t, int[] pos) {
		Tabuleiro novo = Tabuleiro.copy(t);
		int y = pos[0];
		int x = pos[1];
		
		novo.getMatriz()[y][x+1] = t.getMatriz()[y][x];
		novo.getMatriz()[y][x] = t.getMatriz()[y][x+1];
		
//		System.out.println("original \n" + t);
//		System.out.println("copia \n" + novo);
		
		return novo;
	}
	/**
	 * Pega a peça de cima do quadrado vazio e troca a posição entre eles
	 * @param t
	 * @param pos
	 * @return
	 */
	public static Tabuleiro moverParaCima(Tabuleiro t, int[] pos) {
		Tabuleiro novo = Tabuleiro.copy(t);
		int y = pos[0];
		int x = pos[1];
		
		novo.getMatriz()[y-1][x] = t.getMatriz()[y][x];
		novo.getMatriz()[y][x] = t.getMatriz()[y-1][x];
		
//		System.out.println("original \n" + t);
//		System.out.println("copia \n" + novo);
		
		return novo;
	}
	/**
	 * Pega a peça de baixo do quadrado vazio e troca a posição entre eles
	 * @param t
	 * @param pos
	 * @return
	 */
	public static Tabuleiro moverParaBaixo(Tabuleiro t, int[] pos) {
		Tabuleiro novo = Tabuleiro.copy(t);
		int y = pos[0];
		int x = pos[1];
		
		novo.getMatriz()[y+1][x] = t.getMatriz()[y][x];
		novo.getMatriz()[y][x] = t.getMatriz()[y+1][x];
		
//		System.out.println("original \n" + t);
//		System.out.println("copia \n" + novo);
		
		return novo;
	}
	
//	public static void main(String[] args) {
//		int[] a = {1,1};
//		Movimento.moverParaBaixo(new Tabuleiro(3), a);
//	}
}
