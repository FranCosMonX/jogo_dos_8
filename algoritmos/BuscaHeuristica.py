from game.Movimento import Movimento
from game.Tabuleiro import Tabuleiro
from queue import Queue
import numpy as np
import random


class BuscaHeuristica:
    def __init__(self):
        self.fronteira = Queue()
        self.quantidadeDeTabuleirosVistos = 0
        self.estadosVisitados = []

    def execute(self, tabuleiro: Tabuleiro):
        contador_seguro = 0

        self.fronteira.put(tabuleiro)
        while True:
            t = self.fronteira.get()
            self.estadosVisitados.append(t)
            self.quantidadeDeTabuleirosVistos += 1

            if t.eSolucao(): return "Solucao encontrada"

            x, y = Tabuleiro.getEspacoEmBranco(t)
            self.adicionarMovimentosAleatorios(t, x, y)
            self.ordenar()

            if contador_seguro > 10000:
                print("FINAL")
                for i in list(self.fronteira.queue):
                    print(i)
                return "mais de 10.000 tabuleiros verificados"
            contador_seguro += 1

    def adicionarMovimentosAleatorios(self, t: Tabuleiro, x, y):
        ordem = np.arange(4)
        random.shuffle(ordem)

        for i in ordem:
            if i == 0 and Movimento.podeMoverCima(y) and not self.estadosVisitados.__contains__(i):
                novo = Movimento.moverParaCima(t, x, y)
                self.fronteira.put(novo)
                self.estadosVisitados.append(novo)
            elif i == 1 and Movimento.podeMoverBaixo(y, t.tamanhoQuadrado) and not self.estadosVisitados.__contains__(i):
                novo = Movimento.moverParaBaixo(t, x, y)
                self.fronteira.put(novo)
                self.estadosVisitados.append(novo)
            elif i == 2 and Movimento.podeMoverEsquerda(x) and not self.estadosVisitados.__contains__(i):
                novo = Movimento.moverParaEsquerda(t, x, y)
                self.fronteira.put(novo)
                self.estadosVisitados.append(novo)
            elif i == 3 and Movimento.podeMoverDireita(x, t.tamanhoQuadrado) and not self.estadosVisitados.__contains__(i):
                novo = Movimento.moverParaDireita(t, x, y)
                self.fronteira.put(novo)
                self.estadosVisitados.append(novo)

    def ordenar(self):
        l = list(self.fronteira.queue)
        l.sort(key=lambda t: t.getValor(), reverse=True)
        self.fronteira = Queue()
        for item in l:
            self.fronteira.put(item)
        return self.fronteira

def main():
    t = Tabuleiro([[1, 2, 3], [7, 4, 6], [0, 5, 8]])
    x, y = Tabuleiro.getEspacoEmBranco(t)
    b = BuscaHeuristica()
    Movimento.moverParaCima(t,x,y)

    print(b.execute(t))
    print(f'Nos visitados = {b.quantidadeDeTabuleirosVistos}')


main()