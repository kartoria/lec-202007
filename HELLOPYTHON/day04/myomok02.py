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
        for i in range(0,100):
            col = (i*40) % 400
            raw = math.floor(i / 10) * 40
            btn = QPushButton("", self)
            btn.setIcon(QIcon("0.png"))
            btn.setIconSize(QSize(40, 40))
            btn.move(col, raw)
            btn.resize(40,40)
            btn.clicked.connect(self.btn_clicked)
    
#     def btn_clicked(self):
#         idx_image = self.idx % 3
#         self.sender().setIcon(QIcon(str(idx_image) + ".png"))
#         self.idx += 1

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()