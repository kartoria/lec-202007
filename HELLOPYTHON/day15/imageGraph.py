import cv2
import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np
import matplotlib.pyplot as plt
import random

img = cv2.imread('sans.png', cv2.IMREAD_GRAYSCALE)
imgX = img.shape[0] # 이미지 행길이
imgY = img.shape[1] # 이미지 열길이
print("imgX, imgY : ", imgX, imgY)

for i in img:
    print(i)
cv2.waitKey(0)
cv2.destroyAllWindows()

fig = plt.figure()
ax = fig.gca(projection='3d')
 
for j in range(imgX):
    tmpX = []
    for i in range(imgY):
        tmpX.append(j)
    ax.plot(np.array(tmpX), np.array(range(imgY)), img[j])
 
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')
 
plt.show()
