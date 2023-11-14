
from game.Movimento import Movimento
from game.Tabuleiro import Tabuleiro
import numpy as np
import random


class BuscaEmProfundidade:
    def __init__(self):
        self.fronteira = []
        self.quantidadeDeTabuleirosVistos = 0

    def execute(self, tabuleiro: Tabuleiro):
        contador_seguro = 0

        self.fronteira.append(tabuleiro)
        while True:
            t = self.fronteira.pop()
            self.quantidadeDeTabuleirosVistos +=1

            if t.eSolucao(): return "Solucao encontrada"

            x, y = Tabuleiro.getEspacoEmBranco(t)

            self.adicionarMovimentosAleatorios(t, x, y)

            if contador_seguro > 10000:
                """ print("FINAL")
                for i in self.fronteira[::-1]:
                    print(i)                            """
                return "mais de 10.000 tabuleiros verificados"
            contador_seguro+=1

    def adicionarMovimentosAleatorios(self, t: Tabuleiro, x, y):
        ordem = np.arange(4)
        random.shuffle(ordem)

        for i in ordem:
            if i == 0 and Movimento.podeMoverCima(y):
                novo = Movimento.moverParaCima(t, x, y)
                self.fronteira.append(novo)
            elif i == 1 and Movimento.podeMoverBaixo(y, t.tamanhoQuadrado):
                novo = Movimento.moverParaBaixo(t, x, y)
                self.fronteira.append(novo)
            elif i == 2 and Movimento.podeMoverEsquerda(x):
                novo = Movimento.moverParaEsquerda(t, x, y)
                self.fronteira.append(novo)
            elif i == 3 and Movimento.podeMoverDireita(x, t.tamanhoQuadrado):
                novo = Movimento.moverParaDireita(t, x, y)
                self.fronteira.append(novo)



def main():
    t = Tabuleiro([[1, 2, 3], [7, 4, 6], [0, 5, 8]])
    x, y = Tabuleiro.getEspacoEmBranco(t)
    b = BuscaEmProfundidade()
    Movimento.moverParaCima(t,x,y)

    print(b.execute(t))

    print(f'Nos visitados = {b.quantidadeDeTabuleirosVistos}')

main()
