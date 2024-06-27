function validar() {
    var nome = document.getElementById("nome").value;
    var descricao = document.getElementById("descricao").value;
    var dataInicio = document.getElementById("dataInicio").value;

    var regexData = /^([0-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/([0-9]{4})$/;

    if (nome === "" || descricao === "" || dataInicio === "") {
        alert("Todos os campos são obrigatórios");
        return false;
    }


    if (!regexData.test(dataInicio)) {
        alert("Data de início inválida.")
        return false;
    }

    submitForm();
}

function submitForm() {
    document.getElementById("cadastro").submit();
}