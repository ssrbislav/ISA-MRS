var app = angular.module('MyApp', [])
        app.controller('MyController', function ($scope) {
            
            $scope.IsVisible = false;
            $scope.ShowHide = function () {
                
                $scope.IsVisible = $scope.IsVisible = true;
            }
        });