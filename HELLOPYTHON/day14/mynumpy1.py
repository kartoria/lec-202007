import numpy as np

list1 = [1,2,3,4]
print(list1)
a = np.array(list1)
a = a - 1
print(a.shape)

b = np.array([[1,2,3], [4,5,6]])
print(b.shape)
print(b[0,0])