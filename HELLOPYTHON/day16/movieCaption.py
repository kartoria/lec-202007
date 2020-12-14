import cv2
import time
from PIL import ImageDraw, ImageFont, Image
import numpy as np

capture = cv2.VideoCapture("seoul.mp4")
prevTime = 0
b,g,r,a = 255,255,255,0
fontpath = "h2hdrm.ttf"
font = ImageFont.truetype(fontpath, 50)
fps = 0;
out = cv2.VideoWriter('caption_'+capture, -1, 30.0, (1920,1080))
capture.open(fname)
while True:
    if(capture.get(cv2.CAP_PROP_POS_FRAMES) == capture.get(cv2.CAP_PROP_FRAME_COUNT)):
        capture.open("seoul.mp4")
    ret, frame = capture.read()
    img_pil = Image.fromarray(frame)
    draw = ImageDraw.Draw(img_pil)
    x = (frame.shape[1]/3)+100
    y = frame.shape[0]-90
    if fps <= 250 :
        draw.text((x, y), "동해물과백두산이", font=font, fill=(b,g,r,a))
    elif fps <= 500 :
        draw.text((x, y), "마르고 닳도록", font=font, fill=(b,g,r,a))
    elif fps <= 750 :
        draw.text((x, y), "하느님이 보우하사", font=font, fill=(b,g,r,a))
    elif fps <= 1000 :
        draw.text((x, y), "우리나라 만세", font=font, fill=(b,g,r,a))
    elif fps <= 1250 :
        draw.text((x, y), "무궁화 삼천리 화려강산", font=font, fill=(b,g,r,a))
    else :
        draw.text((x, y), "미애", font=font, fill=(b,g,r,a))
    frame = np.array(img_pil)
    fps += 1
    cv2.imshow("VideoFrame", frame)

    if cv2.waitKey(33) > 0: break

capture.release()
cv2.destroyAllWindows()