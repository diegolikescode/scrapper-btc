from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import pandas as pd
import pathlib
from bs4 import BeautifulSoup as bs
from bs4.element import Tag
import datetime
import time
from smtplib import SMTP
from email.message import EmailMessage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pyvirtualdisplay import Display

display = Display(visible=0, size=(800, 600))
display.start()

driver_path = str(pathlib.Path(__file__).parent.resolve()) + '/chromedriver'
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
# driver = webdriver.Chrome(driver_path)

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

def html_more_than(datetime, custom_msg, report):
	email_html = f'''
	<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>report BTC</title>

		<style>
			*,
			body {{
				background-color: #010008;
				color: white;
			}}
			li {{
				line-height: 140%;
				font-size: large;
			}}
		</style>
	</head>

	<body>
		<h1>HOJE {datetime}</h1>
		<strong>Foram trocados mais de 1000BTC no dia de hoje</strong>
		<p>segue o relatório completo das ultimas 300 transações:</p>
		<ul>
			{report}
		</ul>
	</body>

	</html>
	'''
	return email_html

#
#

today = str(datetime.datetime.now()).split(' ')[0]

full_html_report = ''
transactions_by_day = {}
for idx in range(len(all_tr)):
	new_str_value = str(all_values[idx].encode('utf-8'))
	split_new_str = new_str_value[2:len(new_str_value) - 1]
	print(idx, ' DATE:', all_dates[idx], ' | ', 'VALUE:', all_values[idx].replace('+', ''))
	# print(float(all_values[idx].replace('+', '').replace(',', '')))

	# agrupar as tranações por dia
	# somar crédito e débito de cada dia no total
	# if transactions_by_day[all_dates[idx]]
	print(time.strptime(all_dates[idx], '%Y-%m-%d %H:%M:%S'))

	full_html_report += f'<li>data/hora: {all_dates[idx]} | valor: {all_values[idx]}</li>'

def smtp_builder():
	smt = SMTP('smtp.gmail.com', 587)
	smt.ehlo()
	smt.starttls()
	smt.login('diegoprestesdesousa@gmail.com', 'ujhhzunnuftnxmjv')

	return smt


def email_msg_builder(subject: str, msg_type: str, sender: str, receiver: str):
	msg = MIMEMultipart('alternative')
	msg['Subject'] = subject
	msg['From'] = sender
	msg['To'] = receiver
	email_msg = MIMEText(html_more_than('', '', full_html_report), 'html')
	msg.attach(email_msg)

	return msg

# sender = 'diegoprestesdesousa@gmail.com'
# receiver = 'diegosousaflo@hotmail.com'
# smt = smtp_builder()
# my_msg = email_msg_builder('impressive, really nice', 'nice type', sender, receiver)
# smt.sendmail(sender, receiver, my_msg.as_string())


# smt.quit()
driver.quit()
display.stop()
