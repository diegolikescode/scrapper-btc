function limpar() {
    $("#username").val("");
    $("#password").val("");
}
function cadastrar() {
    window.location.replace('./Registrar.html');
}
function logar() {

    let username = $('#username').val();
    let password = $('#password').val();

    $.ajax({
        type: "POST",
        url: 'http://localhost:3000/usuario',
        data: {
            usuario: username,
            senha: password
        },

        success: function (data) {
            window.location.replace('./produto.html');
        },

        error: function (error) {

            console.log(error);

        },

    });

}

