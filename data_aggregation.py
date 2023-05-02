from datetime import datetime
from datetime import timedelta
import time

# a cada 5 minutos pegar a ultima meia hora e somar os valores;
# avisar se houver 500 BTC transferidos no total
# informar relatório de débito e crédito da ultima meia hora
# se eu avisar uma vez o script precisa dormir 30 minutos, se não ele dorme somente 5


def aggregate(all_dates, all_values, rows_qnt):
    full_html_report = ''
    today = str(datetime.now()).split(' ')[0]
    transactions_grouped = {}
    limit_to_get = datetime.now() - timedelta(minutes=120)

    for idx in range(rows_qnt):
        struct_time = time.strptime(all_dates[idx], '%Y-%m-%d %H:%M:%S')
        transaction_time = datetime(*struct_time[:6])
        print(type(transaction_time), type(limit_to_get))
        if transaction_time < limit_to_get:
            break

        transaction_value = all_values[idx].replace('+', '').replace(',', '')

        label_for_dict = 'debit' if float(transaction_value) < 0 else 'credit'

        if label_for_dict not in transactions_grouped.keys():
            transactions_grouped[label_for_dict] = float(transaction_value)
        else:
            transactions_grouped[label_for_dict] += float(transaction_value)

        full_html_report += f'<li>data/hora: {all_dates[idx]} | valor: {transaction_value}</li>'

    print(transactions_grouped)
