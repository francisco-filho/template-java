angular
    .module("app",[])
    .factory('DataService', DataService)
    .controller('MainController', MainController);


DataService.$inject = ['$http'];
function DataService($http){

    var getTopicos = function(){
        return $http({
            url: '/hello/get-topicos/'
        });
    }

    var saveTopicos = function(topico){
        return $http({
            url: '/hello/save-topicos/',
            method: 'POST',
            data: topico
        })
    }

    return {
        getTopicos: getTopicos,
        saveTopicos: saveTopicos
    }
}

MainController.$inject = ['DataService','$location'];
function MainController(DataService, $location){
    var model = this;

    model.topicos = DataService.getTopicos().then(function(data){
        console.log(data.data);
    });

    window.result = DataService.saveTopicos({
        titulo:     'Novo Titulo',
        descricao:  'New desc ' + new Date()
    });
}

