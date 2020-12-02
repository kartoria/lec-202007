import random

com = ""
mine = input("choose odd or even...")
rnd = random.randint(0,1)

if rnd == 0:
    com = "odd"
else:
    com = "even"
    
if com == mine:
    result = "your winner"
else:
    result = "your loser"

print("com : ", com)
print("me : ", mine)
print("result : ", result)

