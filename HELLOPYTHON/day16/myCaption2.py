import cv2

img = cv2.imread('lena.jpg', cv2.IMREAD_COLOR)

# Window name in which image is displayed 
window_name = 'lena'
  
# font 
font = cv2.FONT_HERSHEY_SIMPLEX 
  
# org 
org = (50, 50) 
  
# fontScale 
fontScale = 1
   
# Blue color in BGR 
color = (255, 255, 255) 
  
# Line thickness of 2 px 
thickness = 2
   
# Using cv2.putText() method 
image = cv2.putText(img, '한글', org, font, fontScale, color, thickness, cv2.LINE_AA) 
   
# Displaying the image 
cv2.imshow(window_name, image)

cv2.waitKey(0)
cv2.destroyAllWindows()  