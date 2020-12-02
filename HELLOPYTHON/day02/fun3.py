#파이썬은 멀티 리턴이 가능하다.
def sumavg(a,b):
    return a+b, (a+b)/2


#멀티리턴함수를 호출할 때 변수를 리턴개수만큼 적어준다
a, b = sumavg(100,200)
print("sum : ", a, ", ", "avg : ", b)
