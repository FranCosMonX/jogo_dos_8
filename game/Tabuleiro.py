import numpy as np
import random as rand
import copy


class Tabuleiro:
    def __init__(self, inicial=None, solucao=None, tamanho=3):
        self.matriz = []
        self.matrizSolucao = []
        self.tamanhoQuadrado = tamanho

        self.matriz = inicial if inicial is not None else self.gerarEstadoInicial()
        self.matrizSolucao = solucao if solucao is not None else self.gerarEstadoSoluciao()
        self.valor = 0
        self.calcularValorTabuleiro()
        self.gerarEstadoSoluciao()
        if tamanho == 0: self.tamanhoQuadrado = len(self.matriz)

    def gerarEstadoInicial(self):
        numeros = np.arange(9)
        rand.shuffle(numeros)  # embaralhar

        tabuleiro = [];
        i = 0
        for y in range(self.tamanhoQuadrado):
            linha = []
            for x in range(self.tamanhoQuadrado):
                linha.append(numeros[i])
                i += 1
            tabuleiro.append(linha)
        return tabuleiro

    def gerarEstadoSoluciao(self):
        numero = 1;
        tabuleiro = []
        for y in range(self.tamanhoQuadrado):
            linha = []
            for x in range(self.tamanhoQuadrado):
                linha.append(0 if numero == 9 else numero)
                numero += 1
            tabuleiro.append(linha)
        print(f'tabuleiro solucao{tabuleiro}\n\n')
        return tabuleiro

    def calcularValorTabuleiro(self):
        for i in range(self.tamanhoQuadrado):
            for j in range(self.tamanhoQuadrado):
                peca = self.matriz[i][j]
                if peca != 0:
                    valorX = (peca - 1) / self.tamanhoQuadrado
                    valorY = (peca - 1) % self.tamanhoQuadrado
                    self.valor += abs(i - valorX) + abs(j - valorY)

    def eSolucao(self):
        return self.matrizSolucao == self.matriz

    def getMatriz(self):
        return self.matriz

    def getSolucao(self):
        return self.matrizSolucao

    def getTamanho(self):
        return self.tamanhoQuadrado

    def getValor(self):
        return self.valor

    @staticmethod
    def getEspacoEmBranco(mat):
        i: int;
        j: int
        for i in range(mat.tamanhoQuadrado):
            for j in range(mat.tamanhoQuadrado):
                if mat.matriz[i][j] == 0:
                    return j, i
        return None, None

    def __copy__(self):
        return copy.deepcopy(self)

    def __str__(self):
        print(self.matriz)
        string = ""
        for i in range(self.tamanhoQuadrado):
            for j in range(self.tamanhoQuadrado):
                string += str(self.matriz[i][j]) + " " if j < self.tamanhoQuadrado - 1 else str(self.matriz[i][j]) + ""
            string += "\n"
        return string


"""
def main():
    tab = Tabuleiro()
    tab1 = Tabuleiro()
    x, y = Tabuleiro.getEspacoEmBranco(tab)
    print(tab)
    print(f'x: {x}\ny: {y}')

main()
"""
