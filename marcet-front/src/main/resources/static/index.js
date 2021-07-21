(function ($localStorage) {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'home/home.html',
                controller: 'homeController'
            })
            .when('/products', {
                templateUrl: 'products/products.html',
                controller: 'productController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/basket', {
                templateUrl: 'basket/basket.html',
                controller: 'basketController'
            })
            .when('/adminka', {
                templateUrl: 'adminka/adminka.html',
                controller: 'adminkaController'
            })
            .otherwise({
                redirectTo: '/'
            });

    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            console.log($localStorage.currentUser + " cutentIUserratr")
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
    }
})();


angular.module('app').controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8701/zuul/';

    $scope.tryToLogout = function () {
        delete $localStorage.currentUser;
        delete $localStorage.userOrder;
        $http.defaults.headers.common.Authorization = 'qqq';
        console.log($http.defaults.headers.common.Authorization);
    };

});