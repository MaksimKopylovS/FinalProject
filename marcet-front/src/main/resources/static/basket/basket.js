angular.module('app').controller('basketController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8701/zuul/';

    $scope.getBasket = function () {
        $http.get(contextPath + 'service/basket/get-basket')
            .then(function (response) {
                $scope.basketProduct = response.data;
                $scope.sumCost();
            });
    };

    $scope.delProductOfBasket = function (p) {
        $http.post(contextPath + 'service/basket/del', p)
            .then(function (response){
                $scope.basketProduct = response.data;
                $scope.sumCost();
            })
    }

    $scope.sumCost = function(){
        var sum = 0;
        for(var i=0; i < $scope.basketProduct.length; i++){
            sum += $scope.basketProduct[i].cost;
        }
        $scope.totalCost = sum;
        console.log($scope.totalCost);
    }

    $scope.createOrder = function(adres){
        let data = new Array();
        data[0] = adres;
        data[1] = $localStorage.currentUser.username;
        $http.post(contextPath + 'service/order/create', data)
            .then(function (response){

            })
    }

    $scope.getBasket();
});