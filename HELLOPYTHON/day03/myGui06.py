import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

form_class = uic.loadUiType("myGui06.ui")[0]

class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        self.pb1.clicked.connect(self.button1Function)
        self.pb2.clicked.connect(self.button2Function)
        self.pb3.clicked.connect(self.button3Function)
        self.pb4.clicked.connect(self.button4Function)
        self.pb5.clicked.connect(self.button5Function)
        self.pb6.clicked.connect(self.button6Function)
        self.pb7.clicked.connect(self.button7Function)
        self.pb8.clicked.connect(self.button8Function)
        self.pb9.clicked.connect(self.button9Function)
        self.pb0.clicked.connect(self.button0Function)
        self.pbcall.clicked.connect(self.buttonCallFunction)

    def button1Function(self) : self.resultText.setText(self.resultText.text() + "1")
    def button2Function(self) : self.resultText.setText(self.resultText.text() + "2")
    def button3Function(self) : self.resultText.setText(self.resultText.text() + "3")
    def button4Function(self) : self.resultText.setText(self.resultText.text() + "4")
    def button5Function(self) : self.resultText.setText(self.resultText.text() + "5")
    def button6Function(self) : self.resultText.setText(self.resultText.text() + "6")
    def button7Function(self) : self.resultText.setText(self.resultText.text() + "7")
    def button8Function(self) : self.resultText.setText(self.resultText.text() + "8")
    def button9Function(self) : self.resultText.setText(self.resultText.text() + "9")
    def button0Function(self) : self.resultText.setText(self.resultText.text() + "0")
    def buttonCallFunction(self) : QMessageBox.about(self, "전화걸기", self.resultText.text() + " 번호로 전화를 거는 중입니다...")

        
        
if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()