import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("myGui05.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        self.pb.clicked.connect(self.button1Function)

    def button1Function(self) :
        inputNum = self.li1.text()
        guguDan = ""
        for i in range(1, 10):
            guguDan += inputNum + " * " + str(i) + " = " + str(int(inputNum)*i) + "\n"
        self.te.setText(guguDan)
        
if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()