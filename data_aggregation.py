from datetime import datetime
from datetime import timedelta
import time

def aggregate(all_dates, all_values, rows_qnt) -> tuple [dict, bool]:
    full_html_report = ''
    transactions_grouped = {}
    limit_to_get = datetime.now() - timedelta(minutes=30)

    for idx in range(rows_qnt):
        struct_time = time.strptime(all_dates[idx], '%Y-%m-%d %H:%M:%S')
        transaction_time = datetime(*struct_time[:6])

        if transaction_time < limit_to_get:
            break

        transaction_value = all_values[idx].replace('+', '').replace(',', '')

        label_for_dict = 'debit' if float(transaction_value) < 0 else 'credit'

        if label_for_dict not in transactions_grouped.keys():
            transactions_grouped[label_for_dict] = float(transaction_value)
        else:
            transactions_grouped[label_for_dict] += float(transaction_value)

        full_html_report += f'<li>data/hora: {all_dates[idx]} | valor: {transaction_value}</li>'

    if 'debit' not in transactions_grouped.keys():
        transactions_grouped['debit'] = 0

    if 'credit' not in transactions_grouped.keys():
        transactions_grouped['credit'] = 0

    should_send_email = False
    if transactions_grouped['credit'] >= 1000 or transactions_grouped['debit']:
        should_send_email = True

    report_dict = {
        'full_html_report': full_html_report,
        'btc_sold': transactions_grouped['debit'],
        'btc_bought': transactions_grouped['credit']}

    return report_dict, should_send_email
