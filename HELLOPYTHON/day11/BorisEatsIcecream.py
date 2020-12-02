import sys
from PyQt5.Qt import QRect, Qt, QPixmap, QMainWindow, QLabel, QMessageBox,\
    QApplication
from random import randint


class WindowClass(QMainWindow) :
    def __init__(self) :
        super().__init__()
        self.setWindowTitle("Mr.Boris like IceCream")
        self.setGeometry(600, 100, 1000, 1030)
        self.boris = Boris()
        self.moveLabel = QLabel("moveInfo", self)
        self.moveLabel.setGeometry(QRect(800, 0, 200, 30))
        self.moveLabel.setText("움직인 횟수 : " + str(self.boris.move))
        self.iceLabel = QLabel("iceInfo", self)
        self.iceLabel.setGeometry(QRect(600, 0, 200, 30))
        self.eatLabel = QLabel("iceInfo", self)
        self.eatLabel.setGeometry(QRect(400, 0, 200, 30))
        self.screen2D = [
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0]
                    ]
        self.boris2D = [
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0]
                    ]
        self.cake2D = [
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0],
                        [0,0,0,0,0]
                    ]
        self.labelArr = []
        self.pm0 = QPixmap("white.png");
        self.pm1 = QPixmap("borisL.png");
        self.pm2 = QPixmap("ice.png");
        self.pm3 = QPixmap("eatL.png");
        self.pressKey = "";
        self.gameover = False
        self.ice = 0
        self.eat = 0
        self.iceLabel.setText("남은 아이스크림 : " + str(self.ice))
        self.eatLabel.setText("먹은 아이스크림 : " + str(self.ice))
        self.createLabel()
        self.setBoris2D()
        self.setCake2D()
        self.setScreen2D()
        self.myRender()
        

    def createLabel(self):
        for i in range(0,5):
            line = []
            for j in range(0,5):
                label = QLabel("label"+str(i)+str(j), self)
                label.setPixmap(self.pm0)
                label.setGeometry(QRect(j*200,i*200+30, 200, 200))
                line.append(label)
            self.labelArr.append(line)
        line.append(label)
        
            
    def printScreen(self):
        for i in self.screen2D:
            print(i)
        print("==============================")
        
    def myRender(self):
        for i in range(0, 5):
            for j in range(0, 5):
                display = self.screen2D[i][j]
                if(display == 1):
                    self.labelArr[i][j].setPixmap(self.pm1)
                elif(display == 2):
                    self.labelArr[i][j].setPixmap(self.pm2)
                elif(display == 3):
                    self.labelArr[i][j].setPixmap(self.pm3)
                else:
                    self.labelArr[i][j].setPixmap(self.pm0)
                    
    def keyPressEvent(self, e):
        if not self.gameover:
            if e.key() == Qt.Key_Left:
                if(self.boris.j > 0): 
                    self.boris.j -= 1
                    self.pressKey = "left"
                    self.pm1 = QPixmap("borisL.png");
                    self.pm3 = QPixmap("eatL.png");
                    self.turnUp()
            elif e.key() == Qt.Key_Right:
                if(self.boris.j < 4):
                    self.boris.j += 1
                    self.pressKey = "right"
                    self.pm1 = QPixmap("borisR.png");
                    self.pm3 = QPixmap("eatR.png");
                    self.turnUp()
            elif e.key() == Qt.Key_Up:
                if(self.boris.i > 0): 
                    self.boris.i -= 1
                    self.pressKey = "up"
                    self.turnUp()
            elif e.key() == Qt.Key_Down:
                if(self.boris.i < 4): 
                    self.boris.i += 1
                    self.pressKey = "down"
                    self.turnUp()
            
    def turnUp(self):
        self.boris.move += 1;
        self.moveLabel.setText("움직인 횟수 : "+str(self.boris.move))
        self.setBoris2D()
        self.setCake2D()
        self.setScreen2D()
        self.myRender()
        self.checkFinish()
    
    def checkFinish(self):
        check = True
        for i in range(0, 5):
            for j in range(0, 5):
                if(self.screen2D[i][j] == 2):
                    check = False
        if check:
            print("게임 오버")
            self.gameover = True
            QMessageBox.about(self, "게임 종료", "움직인 수 : "+str(self.boris.move)+"\n먹은 아이스크림 : "+str(self.eat))
            
    
    def setBoris2D(self):
        i = self.boris.i
        j = self.boris.j
        self.boris2D[i][j] = 1
        if self.pressKey == "left":
            self.boris2D[i][j+1] = 0
        elif self.pressKey == "right":
            self.boris2D[i][j-1] = 0
        elif self.pressKey == "up":
            self.boris2D[i+1][j] = 0
        elif self.pressKey == "down":
            self.boris2D[i-1][j] = 0
            
    def setCake2D(self):
        if self.boris.move == 0:
            while self.ice <= 9:
                ranI = randint(0,4)
                ranJ = randint(0,4)
                random = randint(0,100)
                if random >= 60 and self.boris2D[ranI][ranJ] != 1 and self.cake2D[ranI][ranJ] != 2:
                    self.cake2D[ranI][ranJ] = 2
                    self.ice += 1
                    self.iceLabel.setText("남은 아이스크림 : "+str(self.ice))
        else:
            ranI = randint(0,4)
            ranJ = randint(0,4)
            random = randint(0,100)
            if random >= 60:
                if self.boris2D[ranI][ranJ] != 1 and self.cake2D[ranI][ranJ] != 2:
                    self.cake2D[ranI][ranJ] = 2
                    self.ice += 1
                    self.iceLabel.setText("남은 아이스크림 : "+str(self.ice))
            
    def setScreen2D(self):
        for i in range(0,5):
            for j in range(0,5):
                if self.cake2D[i][j] == 2 and self.boris2D[i][j] == 1:
                    self.cake2D[i][j] = 0
                    self.screen2D[i][j] = 3
                    self.ice -= 1
                    self.iceLabel.setText("남은 아이스크림 : "+str(self.ice))
                    self.eat += 1
                    self.eatLabel.setText("먹은 아이스크림 : "+str(self.eat))
                elif self.cake2D[i][j] == 2:
                    self.screen2D[i][j] = 2
                elif self.boris2D[i][j] == 1:
                    self.screen2D[i][j] = 1
                else:
                    self.screen2D[i][j] = 0
                    
                

class Boris:
    def __init__(self):
        self.i = 2;
        self.j = 2;
        self.point = 0;
        self.move = 0;
        
    def __str__(self):
        return "i : "+str(self.i)+" j : "+str(self.j)
    
if __name__ == "__main__" :
    app = QApplication(sys.argv)
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()