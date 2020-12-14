import cv2

img = cv2.imread('lena.jpg', cv2.IMREAD_GRAYSCALE)
cv2.imshow('image',img)
k = cv2.waitKey(0) & 0xFF
if k == 27: # esc key
    cv2.destroyAllWindows()
elif k == ord('s'): # 's' key
    cv2.imwrite('lenagray.png',img)
    cv2.destroyAllWindows()