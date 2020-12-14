import numpy as np
from PIL import ImageFont, ImageDraw, Image
import cv2
img = cv2.imread("lena.jpg", cv2.IMREAD_COLOR)
b,g,r,a = 255,100,255,0
fontpath = "h2hdrm.ttf"
font = ImageFont.truetype(fontpath, 20)
img_pil = Image.fromarray(img)
draw = ImageDraw.Draw(img_pil)
draw.text((10, 10),  "레나", font=font, fill=(b,g,r,a))
img = np.array(img_pil)
cv2.putText(img,  "is lena", (60,40), cv2.FONT_HERSHEY_SIMPLEX, 0.7, (b,g,r), 2, cv2.LINE_AA)
cv2.imshow("res", img)
cv2.waitKey()
cv2.destroyAllWindows()