import cv2
import imutils

img = cv2.imread('boris2.jpg', cv2.IMREAD_COLOR)
img2 = imutils.translate(img, 50, 50)
img3 = imutils.rotate(img, 180)
img4 = imutils.resize(img, 4, cv2.INTER_AREA)

cv2.imshow('image',img)
cv2.imshow('image2',img2)
cv2.imshow('image3',img3)
cv2.imshow('image4',img4)

cv2.waitKey(0)
cv2.destroyAllWindows()