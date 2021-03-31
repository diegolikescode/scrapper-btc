var produtos = [
    {
        id: 1,
        nome: 'Papel',
        img: 'https://a-static.mlcdn.com.br/618x463/papel-off-set-120g-a3-embalagem-com-100-folhas-phandora/phandoravarejopapeiseireli/645/554007eb135f4009961f77725e403412.jpg',
        medida: '1 resma',
        preco: 25.50,
        disponivel: 15,
        solicitado: 0
    },
    {
        id: 2,
        nome: 'Salada',
        img: 'https://pngimage.net/wp-content/uploads/2018/06/salada-em-png-3.png',
        medida: '3 unidades',
        preco: 50.50,
        disponivel: 5,
        solicitado: 0
    }
] 
$(document).ready(function(){
        for(let produto of produtos){
            $('#tabela').append(getProduto(produto))
        }
})
function getProduto(produto){
    return `<tr id= ${produto.id}">
                ${getLinhaProduto(produto)}
        </tr>`
}
function getLinhaProduto(produto){
    return ` 
    <td> ${produto.nome}</td>
    <td class="img">
        <div>
            <img src="${produto.img}" class="img">
        </div>
    </td>
    <td> ${produto.medida}</td>
    <td> R$ ${produto.preco}</td>
    <td> ${produto.disponivel}</td>
    <td> <input required type="number" id="solicitado${produto.id}" placeholder="Digite a quantidade" /> </td>
    `
}
function cancelar() {
    for(let i=1;i<=produtos.length+1;i++){ 
        $(`#solicitado${i}`).val("");
}}
