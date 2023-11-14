
from game.Movimento import Movimento
from game.Tabuleiro import Tabuleiro
from queue import Queue
import numpy as np
import random


class BuscaEmLargura:
    def __init__(self):
        self.fronteira = Queue()

    def execute(self, tabuleiro: Tabuleiro):
        contador_seguro = 0

        self.fronteira.put(tabuleiro)
        while True:
            t = self.fronteira.get()
            print(t)
            if t.eSolucao(): return "Solucao encontrada"

            x, y = Tabuleiro.getEspacoEmBranco(t)

            self.adicionarMovimentosAleatorios(t, x, y)

            if contador_seguro > 3:
                print("FINAL")
                for i in list(self.fronteira.queue):
                    print(i)
                return "mais de 10.000 tabuleiros verificados"
            contador_seguro+=1;

    def adicionarMovimentosAleatorios(self, t: Tabuleiro, x, y):
        ordem = np.arange(4)
        random.shuffle(ordem)

        for i in ordem:
            if i == 0 and Movimento.podeMoverCima(y):
                novo = Movimento.moverParaCima(t, x, y)
                self.fronteira.put(novo)
            elif i == 1 and Movimento.podeMoverBaixo(y, t.tamanhoQuadrado):
                novo = Movimento.moverParaBaixo(t, x, y)
                self.fronteira.put(novo)
            elif i == 2 and Movimento.podeMoverEsquerda(x):
                novo = Movimento.moverParaEsquerda(t, x, y)
                self.fronteira.put(novo)
            elif i == 3 and Movimento.podeMoverDireita(x, t.tamanhoQuadrado):
                novo = Movimento.moverParaDireita(t, x, y)
                self.fronteira.put(novo)



def main():
    t = Tabuleiro([[1, 2, 3], [4, 5, 6], [7, 0, 8]])
    x, y = Tabuleiro.getEspacoEmBranco(t)
    b = BuscaEmLargura()
    Movimento.moverParaCima(t,x,y)

    print(b.execute(t))


main()
