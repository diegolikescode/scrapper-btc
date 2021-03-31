function right() {
    let nome = $('#nome').val();
    let sexo = $('#sexo').val();
    let dataNasc = $('#dataNasc').val();
    let idade = $('#idade').val();
    let cep = $('#cep').val();
    let cidade = $('#cidade').val();
    let bairro = $('#bairro').val();
    let logradouro = $('#logradouro').val();
    let uf = $('#uf').val();
    let numero = $('#numero').val();
    let complemento = $('#complemento').val();
    let login = $('#login').val();
    let senha = $('#senha').val();
    let confirmaSenha = $('#confirmaSenha').val();

    console.log(dataNasc)

    if (senha === confirmaSenha) {

        let data = {
            "login": login,
            "senha": senha,
            "pessoa": {
                "nome": nome,
                "idade": idade,
                "dtNasciment": [2005, 11, 11, 18, 20, 26, 711970000],
                "sexo": sexo,
                "endereco": {
                    "uf": uf,
                    "cidade": cidade,
                    "complemento": complemento,
                    "numero": numero,
                    "logradouro": logradouro,
                    "cep": cep
                }
            },
            "roles": [{ "nomeRole": "ADMIN" }]
        }
        console.log(data)

        $.ajax({

            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            url: 'http://localhost:8080/usuario',

            success: function (data) {
                console.log(data)
            },
            error: function (error) {
                alert(error.message)
            },
        })
    } else {
        alert('senhas não são iguais')
    }
}