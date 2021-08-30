angular.module('app').controller('productController', function($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8701/zuul/';
    $scope.filter = {};

    $scope.getProducts = function (pageIndex = 1) {
        console.log($http.defaults.headers.common.Authorization + "    qqqqqqqq")

        $http({
            url: contextPath + 'service/products/get-all',
            method: 'GET',
            params: {
                id: $scope.filter ? $scope.filter.id : null,
                id_category: $scope.filter ? $scope.filter.id_category : null,
                title: $scope.filter ? $scope.filter.title : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                page: pageIndex
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.productsList = response.data;
            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }
            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.productsList.totalPages) {
                maxPageIndex = $scope.productsList.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);

        });

        // console.log($http.defaults.headers.common.Authorization)
        // $http.get(contextPath + 'apps/get-products')
        //     .then(function (response) {
        //         $scope.productsList = response.data;
        //         console.log($scope.productsList)
        //
        //     });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        };
        $scope.filter.id_category = null;
        console.log($scope.filter.id_category)
        return arr
    };

    $scope.addBasket = function (product) {
        console.log(product)
        console.log($http.defaults.headers.common.Authorization)
        $http.post(contextPath + 'service/basket/add', product )
            .then(function successCalback(response){
                $scope.basketProduct = response.data
                console.log(response.data)
                // var s = response.data, sum = 0;
                // for(var i=0; i < s.length; i ++){
                //     sum += s[i].sumCost;
                // }
                // $scope.totalCost = sum;
                // console.log(response.data)
            });
    };

    $scope.showDerevo = function(){
        $scope.filter.id_category = 1;
        $scope.getProducts();
    }

    $scope.showIron = function(){
        $scope.filter.id_category = 2;
        $scope.getProducts();
    }

    $scope.showInstrument = function(){
        $scope.filter.id_category = 3;
        $scope.getProducts();
    }

    $scope.showSmesi = function(){
        $scope.filter.id_category = 4;
        $scope.getProducts();

    }

    $scope.getProducts();

});