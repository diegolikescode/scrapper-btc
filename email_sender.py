from smtplib import SMTP
from email.message import EmailMessage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

from .email_templates import html_more_than


def smtp_builder():
    smt = SMTP('smtp.gmail.com', 587)
    smt.ehlo()
    smt.starttls()
    smt.login('diegoprestesdesousa@gmail.com', 'ujhhzunnuftnxmjv')

    return smt


def email_msg_builder(subject: str, msg_type: str, full_html_report: str, sender: str, receiver: str):
    msg = MIMEMultipart('alternative')
    msg['Subject'] = subject
    msg['From'] = sender
    msg['To'] = receiver
    email_msg = MIMEText(html_more_than('', '', full_html_report), 'html')
    msg.attach(email_msg)

    return msg


def send_email():
    sender = 'diegoprestesdesousa@gmail.com'
    receiver = 'diegosousaflo@hotmail.com'
    smt = smtp_builder()
    my_msg = email_msg_builder(
        'impressive, really nice', 'nice type', sender, receiver)
    smt.sendmail(sender, receiver, my_msg.as_string())

    smt.quit()
