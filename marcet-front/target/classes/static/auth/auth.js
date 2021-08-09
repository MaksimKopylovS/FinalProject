angular.module('app').controller('authController', function($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8701/zuul/';


    $scope.auth = function () {
        $http.post(contextPath + 'service/auth', $scope.user)
            .then(function successCalback(response) {
                if(response.data.token){
                    $scope.authBool = true;
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {username: $scope.user.username, token: response.data.token};
                    $scope.authBool = true;
                }
            }, function errorCallback(response) {
                $scope.authBool = false
                window.alert("Error");
            });
    }

});