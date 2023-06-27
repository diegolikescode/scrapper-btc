import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from bs4 import BeautifulSoup as bs
from bs4.element import Tag
from pyvirtualdisplay import Display
from data_aggregation import aggregate
from email_sender import send_email


def main():
    while(True):
        display = Display(visible=0, size=(800, 600))
        display.start()

        driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
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
                    debt_value = tr.find(
                        'span', {'class': 'text-error'}).contents[0]
                    all_values.append(debt_value)
                else:
                    credit_value = transaction_value.contents[0]
                    all_values.append(credit_value)

            except Exception:
                pass

        report_dict, should_send_email = aggregate(all_dates, all_values, len(all_tr))

        sleep_time = 5 * 60
        if should_send_email:
            send_email(report_dict)
            sleep_time = 30 * 60

        driver.quit()
        display.stop()
        time.sleep(sleep_time)

main()
