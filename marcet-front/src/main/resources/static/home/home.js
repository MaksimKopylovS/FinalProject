angular.module('app').controller('homeController', function($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8701/zuul/';

    $scope.foon = function () {
        $http.get(contextPath + 'service/get-products-all', {
            transformResponse: function (response) {
                return (response);
            }
        })
            .then(function (response) {
                $scope.productsH = response.data;
                console.log($http.defaults.headers.common.Authorization);
            });
    }
});