(function() {
	//IIFE
	var app = angular.module('productApp', []);

	app.service('Serv',['$http',
        function ($http) {
            this.getProducts = function() {
                return $http({
                    url: '/products',
                    method: 'GET'
                });
            };

            this.deleteProduct = function(productId, token) {
                return $http({
                    url: '/product/' + productId,
                    method: 'DELETE',
                    headers : {
                      'Content-Type' : 'application/json',
                      Authorization: 'Bearer ' + token
                    }
                });
            };

            this.getProduct = function(productId, token) {
                return $http({
                    url: '/product/' + productId,
                    method: 'GET',
                    headers : {
                      'Content-Type' : 'application/json',
                      Authorization: 'Bearer ' + token
                    }
                });
            };

            this.updateProduct = function(product, token) {
                return $http({
                    url: '/product/update', method: 'PUT',
                    data: product,
                    headers : {
                      'Content-Type' : 'application/json',
                      Authorization: 'Bearer ' + token
                    }
                });
            };

            this.createProduct = function(product, token) {
                return $http({
                    url: '/product/new', method: 'POST',
                    data: product,
                    headers : {
                      'Content-Type' : 'application/json',
                      Authorization: 'Bearer ' + token
                    }
                });
            };
        }
    ]);

	app.controller('productController', ['$scope', 'Serv',
		function($scope, Serv) {
			$scope.initialize = function() {
				$scope.getProducts();
			};

			$scope.getProducts = function () {
				Serv.getProducts()
					.then(function(response) {
						$scope.products = response.data;
					}, function(xhr, status, error) {
						if(status === 401 || status === 403 || status !== 200) {
							console.log('something went wrong');
						}
					});
			};

			$scope.saveProduct = function() {
			    $scope.newProduct.id = parseInt($scope.newProduct.id);
			    var jws = $scope.createAuthorizationToken($scope.newProduct);
			    Serv.createProduct($scope.newProduct, jws)
			        .then(function(response) {
                        $scope.getProducts();
                        alert('new Product added');
                    }, function(xhr, status, error) {
                        if(status === 401 || status === 403 || status !== 200) {
                            console.log('something went wrong');
                        }
                    });
			};

			$scope.updateProduct = function() {
                var jws = $scope.createAuthorizationToken($scope.editProduct);
                Serv.updateProduct($scope.editProduct, jws)
                    .then(function(response) {
                        $scope.editProduct = {};
                        $scope.getProducts();
                        alert('Product Edited');
                    }, function(xhr, status, error) {
                        if(status === 401 || status === 403 || status !== 200) {
                           console.log('something went wrong');
                        }
                   });
            };

			$scope.edit = function(id) {
                var jws = $scope.createAuthorizationToken({id});
			    Serv.getProduct(id, jws)
			    .then(function(response) {
                    $scope.editProduct = response.data;
                    window.scrollTo(0, 0);
                }, function(xhr, status, error) {
                    if(status === 401 || status === 403 || status !== 200) {
                        console.log('something went wrong');
                    }
                });
			};

			$scope.delete = function(id) {
			    var jws = $scope.createAuthorizationToken({id});
                Serv.deleteProduct(id, jws)
                .then(function(response) {
                    $scope.getProducts();
                    alert('Product deleted');
                }, function(xhr, status, error) {
                    if(status === 401 || status === 403 || status !== 200) {
                        console.log('something went wrong');
                    }
                });
			};

			$scope.createAuthorizationToken = function(payload) {
			    var header = {alg: "HS256"};
                return KJUR.jws.JWS.sign("HS256", header, payload, {utf8: "springMyBatis"});
			}
		}
	]);
})();