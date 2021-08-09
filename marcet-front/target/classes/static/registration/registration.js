angular.module('app').controller('registrationController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8701/zuul/';

    $scope.toRegistration = function () {
        console.log($scope.user)
        $http.post(contextPath + 'service/registration', $scope.user)
            .then(function successCalback(response) {
                $scope.regBool = true
                $scope.regName = response.data.userName;
            }, function errorCallback(response) {
                $scope.regBool = false
                window.alert("Учётная запись уже существует");
            });
    }
});