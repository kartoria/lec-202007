import sys
from PyQt5 import uic
from PyQt5.QtWidgets import *
from PyQt5.QtGui import QPixmap
from PyQt5.Qt import QIcon

form_class = uic.loadUiType("myomok01.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.idx = 0
        self.setupUi(self)
        self.qb_1.clicked.connect(self.pb_click)


    def pb_click(self):
        print(self.idx)
        idx_image = self.idx % 3
        self.qb_1.setIcon(QIcon(str(idx_image) + ".png"))
        self.idx += 1
        
        

if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()