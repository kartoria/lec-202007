from matplotlib import pyplot as plt
import numpy as np

x = np.arange(1,4)
y = x*3
z = x*5
print(x)
print(y)

plt.plot(x,y)
plt.plot(x,z)
plt.show()