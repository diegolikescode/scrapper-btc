from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import pandas as pd
import pathlib
from bs4 import BeautifulSoup as bs
from bs4.element import Tag
import datetime
from smtplib import SMTP

# class=tbody (lista de compras)
# class=text-success (qnt de BTC compra)
# class=utc hidden-phone (data BTC compra)

driver_path = str(pathlib.Path(__file__).parent.resolve()) + '/chromedriver'
driver = webdriver.Chrome(driver_path)
driver.get(
    'https://bitinfocharts.com/bitcoin/address/bc1qm34lsc65zpw79lxes69zkqmk6ee3ewf0j77s3h')

full_html = driver.find_element(
    By.ID, 'table_maina').get_attribute("outerHTML")
soup = bs(full_html, 'html.parser')
all_tr = soup.find_all('tr')
all_tr.pop(0)
all_tr.pop(len(all_tr) - 1)

all_dates = []
all_values = []
for tr in all_tr:
    transaction_date = tr.find('td', {'class': 'hidden-phone'}).contents[0]
    all_dates.append(transaction_date)
    try:
        transaction_value = tr.find(
            'span', {'class': 'text-success'})
        if not isinstance(transaction_value, Tag):
            debt_value = tr.find('span', {'class': 'text-error'}).contents[0]
            # print('IF', debt_value)
            all_values.append(debt_value)
            # print(transaction_date, ' | ', debt_value)
        else:
            credit_value = transaction_value.contents[0]
            # print(transaction_date, ' | ', credit_value)
            # print('ELSE', credit_value)
            all_values.append(credit_value)

    except Exception as ex:
        print('EXCEPT!', type(ex).__name__, ex.args)
        pass

for idx in range(len(all_tr)):
	new_str_value = str(all_values[idx].encode('utf-8'))
	split_new_str = new_str_value[2:len(new_str_value) - 1]
	print(idx, ' DATE:', all_dates[idx], ' | ', 'VALUE:', all_values[idx])


def smtp_builder():
	smt = SMTP('smtp.gmail.com', 587)
	smt.ehlo()
	smt.starttls()
	smt.login('diegoprestesdesousa@gmail.com', 'ujhhzunnuftnxmjv')
	return smt


smt = smtp_builder()
smt.sendmail('diegoprestesdesousa@gmail.com', 'diegosousaflo@hotmail.com', 'baby drummer')



driver.quit()
