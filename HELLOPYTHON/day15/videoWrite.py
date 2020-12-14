import cv2

fname = 'CasinoRoyale.mp4'
cap = cv2.VideoCapture(fname)

width = cap.get(cv2.CAP_PROP_FRAME_WIDTH)
height = cap.get(cv2.CAP_PROP_FRAME_HEIGHT)

out = cv2.VideoWriter('GrayTo'+fname, -1, 30.0, (int(width),int(height)))

cap.open(fname)
while True:
    if(cap.get(cv2.CAP_PROP_POS_FRAMES) == cap.get(cv2.CAP_PROP_FRAME_COUNT)):
        break
    ret, frame = cap.read()
    
    if ret == False:
        break;
    
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    gray = cv2.flip(gray, 0)
    cv2.imshow(fname, gray)
    out.write(gray)
    
    if cv2.waitKey(33) > 0 : break;

cap.release()
cv2.destroyAllWindows()