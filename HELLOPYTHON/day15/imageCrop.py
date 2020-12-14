import cv2

img = cv2.imread('boris2.jpg', cv2.IMREAD_COLOR)
imgCrop = img[000:250, 230:500]

cv2.imshow('image',img)
cv2.imshow('image2',imgCrop)

cv2.waitKey(0)
cv2.destroyAllWindows()