import sys
from PyQt5 import uic
from PyQt5.QtWidgets import *
from PyQt5.QtGui import QPixmap
from PyQt5.Qt import QIcon, QSize
import math


class WindowClass(QMainWindow) :
    def __init__(self) :
        super().__init__()
        self.setWindowTitle("오목")
        self.setGeometry(300, 300, 400, 400)
        self.idx = 0
        self.arr2D = [
                        [1,0,0,0,0,0,0,0,0,0],
                        [0,2,0,0,0,0,0,0,0,0],
                        [0,0,1,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0]
                    ]
        
        for i in range(0,100):
            raw = i % 10
            col = math.floor(i / 10)
            btn = QPushButton("", self)
            if self.arr2D[raw][col] == 1:
                btn.setIcon(QIcon("1.png"))
            elif self.arr2D[raw][col] == 2:
                btn.setIcon(QIcon("2.png"))
            else:
                btn.setIcon(QIcon("0.png"))
            btn.setIconSize(QSize(40, 40))
            btn.move(col * 40, raw * 40)
            btn.resize(40,40)
            btn.clicked.connect(self.btn_clicked)
        
        
    def btn_clicked(self):
        strXY = str(self.sender().geometry())
        print(strXY)
        strXY = strXY[19:len(strXY)-1]
        print(strXY)
        strXY = strXY.split(", ")
        print(strXY)
        strX = round(int(strXY[1]) / 40)
        strY = round(int(strXY[0]) / 40)
        if(self.arr2D[strX][strY] == 1):
            self.arr2D[strX][strY] = 2
            self.sender().setIcon(QIcon("2.png"))
        elif(self.arr2D[strX][strY] == 2):
            self.arr2D[strX][strY] = 0
            self.sender().setIcon(QIcon("0.png"))
        elif(self.arr2D[strX][strY] == 0):
            self.arr2D[strX][strY] = 1
            self.sender().setIcon(QIcon("1.png"))
        

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()