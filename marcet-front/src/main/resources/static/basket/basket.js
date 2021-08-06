angular.module('app').controller('basketController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8701/zuul/';
    let userName;
    userName = $localStorage.currentUser.username;

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

    $scope.createOrder = function(){
        $http.post(contextPath + 'service/order/create', userName)
            .then(function (response){
                console.log(response.data)
                $scope.orderNumber = response.data.orderId
            })
    }

    $scope.showOrder = function(orderNumber){
        console.log(orderNumber)
        $scope.getAdressNoUserName();
        $http.post(contextPath + 'service/order/show', orderNumber)
            .then(function (response){
                console.log(response)
            })
    }

    $scope.getAdressNoUserName = function(){
        userName = $localStorage.currentUser.username;
            $http.post(contextPath + 'service/address/username', userName)
                .then(function(response){
                    console.log(response)
                })
    }


    $scope.getBasket();

});

