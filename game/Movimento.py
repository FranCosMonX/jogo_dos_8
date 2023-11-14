from game.Tabuleiro import Tabuleiro


class Movimento:
    @staticmethod
    def podeMoverEsquerda(x:int):
        return x > 0

    @staticmethod
    def podeMoverDireita(x:int, tamanho):
        return x < tamanho - 1

    @staticmethod
    def podeMoverCima(y:int):
        return y > 0

    @staticmethod
    def podeMoverBaixo(y:int, tamanho):
        return y < tamanho - 1

    @staticmethod
    def moverParaEsquerda(tabuleiro: Tabuleiro, x, y):
        tab = tabuleiro.__copy__()

        tab.matriz[y][x - 1] = tabuleiro.getMatriz()[y][x]
        tab.matriz[y][x] = tabuleiro.getMatriz()[y][x - 1]

        return tab

    @staticmethod
    def moverParaDireita(tabuleiro: Tabuleiro, x, y):
        tab = tabuleiro.__copy__()

        tab.matriz[y][x + 1] = tabuleiro.getMatriz()[y][x]
        tab.matriz[y][x] = tabuleiro.getMatriz()[y][x + 1]

        return tab

    @staticmethod
    def moverParaCima(tabuleiro: Tabuleiro, x, y):
        tab = tabuleiro.__copy__()
        #print(f'{tabuleiro}\nx: {x} y: {y}')
        tab.matriz[y - 1][x] = tabuleiro.getMatriz()[y][x]
        tab.matriz[y][x] = tabuleiro.getMatriz()[y - 1][x]

        return tab

    @staticmethod
    def moverParaBaixo(tabuleiro: Tabuleiro, x: int, y: int) -> Tabuleiro:
        tab = tabuleiro.__copy__()

        tab.matriz[y + 1][x] = tabuleiro.getMatriz()[y][x]
        tab.matriz[y][x] = tabuleiro.getMatriz()[y + 1][x]

        return tab

"""
def main():
    t = Tabuleiro()
    print(t)
    x, y = Tabuleiro.getEspacoEmBranco(t)
    print(f"tamanho {t.getTamanho()}")
    t = Movimento.moverParaBaixo(t,x,y)
    print(t)


main()
"""