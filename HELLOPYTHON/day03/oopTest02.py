class Animal:
    def __init__(self):
        self.age = 0
        print("constructor")
    def getOld(self):
        self.age += 1

class Protoss:
    def __init__(self):
        self.mindcontrol = 1
    def thought(self):
        self.mindcontrol += 1
    
    
class Human(Animal, Protoss):
    def __init__(self):
        Animal.__init__(self)
        Protoss.__init__(self)
        self.name = "김철수"
    def changeName(self, name):
        self.name = name 
        

if __name__ == '__main__':
    human = Human()
    print("human.age : ", human.age)
    print("human.name : ", human.name)
    print("human.mindcon" , human.mindcontrol)
    human.getOld()
    human.changeName("김또깡")
    human.thought()
    print("바뀐.age : ", human.age)
    print("바뀐.name : ", human.name)
    print("바뀐.mindcon" , human.mindcontrol)
    