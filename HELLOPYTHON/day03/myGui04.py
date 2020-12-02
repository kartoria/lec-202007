import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("myGui04.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        self.pb.clicked.connect(self.button1Function)

    def button1Function(self) :
        self.li3.setText(str(int(self.li1.text()) + int(self.li2.text()))) 
        
if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()