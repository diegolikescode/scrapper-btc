def html_more_than(selled, bought, report):
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
		<h1>AVISO DA CARTEIRA DE BITCOIN</h1>
		<strong>Foi vendido {selled} e comprado {bought} BTC nos ultimos 30 minutos</strong>
		<p>segue o relatório completo das ultimas transações:</p>
		<ul>
			{report}
		</ul>
	</body>
	</html>
	'''
    return email_html
