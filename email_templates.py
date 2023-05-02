def html_more_than(date_report, custom_msg, report):
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
		<h1>HOJE {date_report}</h1>
		<strong>Foram trocados mais de 1000BTC no dia de hoje</strong>
		<p>segue o relatório completo das ultimas 300 transações:</p>
		<ul>
			{report}
		</ul>
	</body>
	</html>
	'''
    return email_html
