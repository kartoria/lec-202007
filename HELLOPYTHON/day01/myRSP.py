import random

com = ""
me = input("가위 바위 보 입력 : ")
rnd = random.randint(0,2)

if rnd == 0:
    com = "가위"
elif rnd == 1:
    com = "바위"
else:
    com = "보"
    
if com == me:
    result = "둘이 비겼습니다."
elif (com=="바위" and me=="가위") or (com=="가위" and me=="보") or (com=="보" and me=="바위"):  
    result = "당신이 패배했습니다."
elif (com=="가위" and me=="바위") or (com=="보" and me=="가위") or (com=="바위" and me=="보"):
    result = "당신이 승리했습니다."
else :
    result = "잘못된 입력입니다."

print("com : ", com)
print("me : ", me)
print("result : ", result)