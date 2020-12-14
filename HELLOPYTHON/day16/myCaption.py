import cv2
import imutils
import numpy as np
from PIL import ImageFont, ImageDraw, Image

img = cv2.imread('lena.jpg', cv2.IMREAD_COLOR)

cv2_im_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
pil_im = Image.fromarray(cv2_im_rgb)

draw = ImageDraw.Draw(pil_im)

font = ImageFont.truetype("FORTE.ttf", 50)

# Draw the text
draw.text((0, 0), "Your Text Here", font=font)

cv2_im_processed = cv2.cvtColor(np.array(pil_im), cv2.COLOR_RGB2BGR)
cv2.imwrite("result.png", cv2_im_processed)
cv2.imshow('image',img)
cv2.waitKey(0)
cv2.destroyAllWindows()
