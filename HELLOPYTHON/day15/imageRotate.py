import cv2

img = cv2.imread('boris2.jpg', cv2.IMREAD_COLOR)

rows, cols = img.shape[:2]

rotate = cv2.getRotationMatrix2D((cols/2, rows/2), 180, 1)

img2 = cv2.warpAffine(img, rotate, (cols, rows)) 
cv2.imshow('image',img)
cv2.imshow('image2',img2)

cv2.waitKey(0)
cv2.destroyAllWindows()