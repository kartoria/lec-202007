import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np
import matplotlib.pyplot as plt
import random

# make 3d axes
fig = plt.figure()
ax = fig.gca(projection='3d')

# test data
x1 = np.array([1,1,1,1])
x2 = np.array([2,2,2,2])
x3 = np.array([3,3,3,3])
y = np.array([1,2,3,4])
z1 = [2,4,6,8]
z2 = [3,6,9,12]
z3 = [-1,-2,-3,8]

# plot test data
ax.plot(x1, y, z1)
ax.plot(x2, y, z2)
ax.plot(x3, y, z3)

# make labels
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')

plt.show()
