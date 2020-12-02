import pandas as pd
raw_data = {    'col0' : [1, 2, 3, 4],
                'col1' : [10, 20, 30, 40],
                'col2' : [100, 200, 300, 400]} #리스트 자료형으로 생성
raw_data = pd.DataFrame(raw_data) #데이터 프레임으로 전환
raw_data.to_excel(excel_writer='sample.xlsx') #엑셀로 저장