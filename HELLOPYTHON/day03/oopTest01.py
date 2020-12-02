class Animal:
    def __init__(self):
        self.age = 0
        print("constructor")
    def getOld(self):
        self.age += 1
    
if __name__ == '__main__':
    animal = Animal()
    print(animal.age)
    animal.getOld()
    print(animal.age)
    