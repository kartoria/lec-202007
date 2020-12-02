import sys
from PyQt5 import uic
from PyQt5.QtWidgets import *
from PyQt5.QtGui import QPixmap
from PyQt5.Qt import QIcon, QSize, QRect
from PyQt5.QtCore import Qt
import pymssql

class WindowClass(QMainWindow) :
    def __init__(self) :
        super().__init__()
        self.setWindowTitle("오목")
        self.setGeometry(600, 300, 500, 400)
        
        returnBtn = QPushButton("무르기",self)
        returnBtn.setGeometry(QRect(410, 125, 80, 50))
        returnBtn.clicked.connect(self.returnBtn)
        
        resetBtn = QPushButton("다시하기",self)
        resetBtn.setGeometry(QRect(410, 225, 80, 50))
        resetBtn.clicked.connect(self.resetBtn)
        
        self.label = QLabel("백돌 차례", self)
        self.label.setAlignment(Qt.AlignCenter)
        self.label.setGeometry(QRect(410, 30, 80, 50))
        
        
        self.arr2D = [ # 돌이 놓여있는 좌표 상황
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0]
                    ]
        self.arr_gibo = []
        self.memory = [-1,-1] #방금 턴에 놓은 돌 위치
        self.WB = False # 흑돌차례인지 백돌차례인지
        self.WorB = 0
        self.end = False # 게임이 끝났는지 아닌지
        self.btn2D = [] # 버튼객체가 들어간 이차원 배열
        self.createLabel();
        self.conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
        self.cursor = self.conn.cursor()
        
    def createLabel(self):
        for i in range(0,10):
            line = []
            for j in range(0,10):
                btn = QPushButton("",self)
                btn.setIcon(QIcon("0.png"))
                btn.setWhatsThis(str(i)+","+str(j))
                btn.setGeometry(QRect(j*40, i*40, 40, 40))
                btn.setIconSize(QSize(40,40))
                btn.clicked.connect(self.btn_clicked)
                line.append(btn)
            self.btn2D.append(line)
            
            
    def btn_clicked(self):
        if self.end:
            return
        str_ij = self.sender().whatsThis()
        arr_ij = str_ij.split(",")
        str_i = int(arr_ij[0])
        str_j = int(arr_ij[1])
        
        
        if self.arr2D[str_i][str_j] != 0:
            return
        
        
        if self.arr2D[str_i][str_j] == 0 :
            if not self.WB :
                self.WB = True
                self.label.setText("흑돌 차례")
                self.arr2D[str_i][str_j] = 1
                self.WorB = 1
            else :
                self.WB = False
                self.label.setText("백돌 차례")
                self.arr2D[str_i][str_j] = 2
                self.WorB = 2
                
        self.showArr2D()
        self.myrender()
        
        self.memory[0] = str_i
        self.memory[1] = str_j
        self.setGibo()
        
        cntUD = self.getUp(str_i,str_j) + self.getDown(str_i,str_j)    # 수직체크
        cntRL = self.getRight(str_i,str_j) + self.getLeft(str_i,str_j) # 수평체크
        cntULDR = self.getUL(str_i,str_j) + self.getDR(str_i,str_j)    # 왼쪽대각선
        cntURDL = self.getUR(str_i,str_j) + self.getDL(str_i,str_j)    # 오른쪽대각선
        
        
        
        if cntUD == 4 or cntRL == 4 or cntULDR == 4 or cntURDL == 4:
            self.label.setText("게임 종료")
            if self.WB:
                QMessageBox.about(self, "게임 끝!", "백돌 승리")
            else:
                QMessageBox.about(self, "게임 끝!", "흑돌 승리")
            self.insertArr2DB()
            self.end = True;
                
        
        
    def getUp(self,str_i,str_j):
        cnt = 0
        try :
            while True:
                str_i -= 1
                if str_i < 0:
                    return cnt
                if self.arr2D[str_i][str_j] == self.WorB :
                    cnt += 1
                else :
                    return cnt
        except:
            return cnt
    def getDown(self,str_i,str_j):
        cnt = 0
        try :
            while True:
                str_i += 1
                if str_i > 9:
                    return cnt
                if self.arr2D[str_i][str_j] == self.WorB :
                    cnt += 1
                else :
                    return cnt
        except:
            return cnt
            
    def getRight(self,str_i,str_j):
        cnt = 0
        try:
            while True:
                str_j += 1
                if str_j > 9:
                    return cnt
                if self.arr2D[str_i][str_j] == self.WorB :
                    cnt += 1
                else :
                    return cnt
        except:
            return cnt
    def getLeft(self,str_i,str_j):
        cnt = 0
        try:
            while True:
                str_j -= 1
                if str_j < 0:
                    return cnt
                if self.arr2D[str_i][str_j] == self.WorB :
                    cnt += 1
                else :
                    return cnt
        except:
            return cnt
        
    def getUR(self,str_i,str_j):
        cnt = 0
        try:
            while True:
                str_j -= 1
                str_i += 1
                if str_j < 0 or str_i > 9:
                    return cnt
                if self.arr2D[str_i][str_j] == self.WorB :
                    cnt += 1
                else :
                    return cnt
        except:
            return cnt
    def getDL(self,str_i,str_j):
        cnt = 0
        try:
            while True:
                str_j += 1
                str_i -= 1
                if str_j > 9 or str_i < 0:
                    return cnt
                if self.arr2D[str_i][str_j] == self.WorB :
                    cnt += 1
                else :
                    return cnt
        except:
            return cnt
        
    def getUL(self,str_i,str_j):
        cnt = 0
        try:
            while True:
                str_j -= 1
                str_i -= 1
                if str_j < 0 or str_i < 0:
                    return cnt
                if self.arr2D[str_i][str_j] == self.WorB :
                    cnt += 1
                else :
                    return cnt
        except:
            return cnt
    def getDR(self,str_i,str_j):
        cnt = 0
        try:
            while True:
                str_j += 1
                str_i += 1
                if str_j > 9 or str_i > 9:
                    return cnt
                if self.arr2D[str_i][str_j] == self.WorB :
                    cnt += 1
                else :
                    return cnt
        except:
            return cnt
    
        
    def showArr2D(self):
        for i in self.arr2D:
            print(i)
        print("==============================")
        
    def myrender(self):
        for i in range(0,10):
            for j in range(0,10):
                if self.arr2D[i][j] == 0:
                    self.btn2D[i][j].setIcon(QIcon("0.png"))
                elif self.arr2D[i][j] == 1:
                    self.btn2D[i][j].setIcon(QIcon("1.png"))
                elif self.arr2D[i][j] == 2:
                    self.btn2D[i][j].setIcon(QIcon("2.png"))

    def returnBtn(self): # 무르기 버튼
        if(self.end):
            return
        if(self.memory[0] == -1 or self.memory[1] == -1):
            return
        self.arr2D[self.memory[0]][self.memory[1]] = 0
        self.memory[0] = -1;
        self.memory[1] = -1;
        if(self.WB):
            self.WB = False
            self.label.setText("백돌 차례")
        else:
            self.WB = True
            self.label.setText("흑돌 차례")
        self.myrender()
    def resetBtn(self): # 리셋 버튼
        for i in range(0, 10):
            for j in range(0, 10):
                self.arr2D[i][j] = 0
        self.arr_gibo.clear()
        self.memory = [-1,-1] #방금 턴에 놓은 돌 위치
        self.WB = False # 흑돌차례인지 백돌차례인지
        self.WorB = 0
        self.end = False # 게임이 끝났는지 아닌지
        self.label.setText("백돌 차례")
        self.myrender()
    
    def setGibo(self):
        txt_gibo = ""
        for i in range(10):
            for j in range(10):
                txt_gibo += str(self.arr2D[i][j])
        self.arr_gibo.append(txt_gibo)
        
    
    def insertArr2DB(self):
        self.cursor.execute("select ISNULL(max(pan)+1, '1') mymax from omokgibo")
        row = self.cursor.fetchone()
        pan = row[0]
        print("row : ", pan)
        seq = 1
        win = 0
        for gibo in self.arr_gibo:
            if seq % 2 != 0:
                win = 1
            else:
                win = 2
            self.cursor.execute("INSERT INTO omokGibo (pan,seq,gibo,win) VALUES ("+str(pan)+","+str(seq)+",'"+str(gibo)+"',"+str(win)+")")
            self.conn.commit()
            seq+=1
        self.conn.close()
    
    
if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()
    