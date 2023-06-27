from smtplib import SMTP
from email.message import EmailMessage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

import email_templates


def smtp_builder():
    smt = SMTP('smtp.gmail.com', 587)
    smt.ehlo()
    smt.starttls()
    smt.login('diegoprestesdesousa@gmail.com', 'ujhhzunnuftnxmjv')

    return smt


def email_msg_builder(subject: str, report_dict: dict, sender: str, receiver: str):
    msg = MIMEMultipart('alternative')
    msg['Subject'] = subject
    msg['From'] = sender
    msg['To'] = receiver
    email_msg = MIMEText(email_templates.html_more_than(
        selled=report_dict['btc_sold'], bought=report_dict['btc_bought'], report=report_dict['full_html_report']), 'html')
    msg.attach(email_msg)

    return msg


def send_email(report_dict):
    sender = 'diegoprestesdesousa@gmail.com'
    receiver = 'diegosousaflo@hotmail.com'
    smt = smtp_builder()

    my_msg = email_msg_builder(
        subject='relatório BTC', report_dict=report_dict, sender=sender, receiver=receiver)
    smt.sendmail(sender, receiver, my_msg.as_string())

    smt.quit()
