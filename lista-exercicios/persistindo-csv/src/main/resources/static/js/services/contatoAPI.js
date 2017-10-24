
angular.module("listaContatos").service("contatoAPI", function ($http, config) {

    this.loadAllContacts = function () {
        return $http.get(config.baseUrl + "/contacts/load");
    };
    
    this.adicionarContato = function (contato) {
        return $http.post(config.baseUrl + "/add", contato);
    };

});
