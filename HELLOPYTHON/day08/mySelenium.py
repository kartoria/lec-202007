from selenium import webdriver

path = "chromedriver.exe"
driver = webdriver.Chrome(path)
driver.implicitly_wait(3)
driver.get('https://finance.naver.com/item/sise.nhn?code=005930')
search = driver.find_element_by_id("_nowVal")
print(search.text)