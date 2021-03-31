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
        url: 
        data,
        dataType: "text",
        success: function (data) {
            window.location.replace('./produto.html');
        },
        error: function () {

            console.log('login ou senha invalida');

        },
        
    });

}

