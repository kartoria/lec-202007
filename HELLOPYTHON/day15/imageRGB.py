import cv2
import imutils

img = cv2.imread('pixel.png', cv2.IMREAD_COLOR)
print(img)

cv2.waitKey(0)
cv2.destroyAllWindows()