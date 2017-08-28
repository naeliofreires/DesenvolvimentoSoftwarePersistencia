angular.module("listaContatos", []);

angular.module("listaContatos").controller("listaContatosController", function ($scope, contatoAPI) {

    $scope.aplicacao = "Persistencia em Arquivos - CSV";
    $scope.contatos = [];

    // Carregando Contatos
    $scope.getContatos = function () {

        contatoAPI.loadAllContacts().then(function (response) {
            $scope.contatos = response.data;
            console.log($scope.contatos);
        });
    };

    $scope.getContatos();

    // Adicionar Contatos
    $scope.addContact = function (contato) {

        contatoAPI.adicionarContato(contato).then(function (response) {
            delete $scope.contato;
            $scope.getContatos();
        }, function (response) {
            consolel.log("adicionarContato: " + response);
        });

    }
});