var relatorio = [
    {
        id: 1,
        usuario: 'usuario 1',
        dataSolici: '01/02/2019',
        produto: 'Papel',
        quantidade: 2,
        valorTotal: 51.0
    },
    {
        id: 2,
        usuario: 'usuario 2',
        dataSolici: '05/02/2019',
        produto: 'Papel',
        quantidade: 3,
        valorTotal: 76.50
    }
]
$(document).ready(function () {
    let total = 0;
    for (let dado of relatorio) {
        $('#relatorio').append(getProduto(dado))
        total += dado.valorTotal;
    }
    $('#total').html(`Valor total= ${total}`)


})
function getProduto(dado) {
    return `<tr id= ${dado.id}">
            ${getLinhaProduto(dado)}
    </tr>`
}
function getLinhaProduto(dado) {
    return ` 
<td> ${dado.usuario}</td>
<td> ${dado.dataSolici}</td>
<td> ${dado.produto}</td>
<td> ${dado.quantidade}</td>
<td> R$ ${dado.valorTotal}</td>
`
}