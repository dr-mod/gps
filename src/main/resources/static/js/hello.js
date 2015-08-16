angular.module('hello', ['ngRoute', 'uiGmapgoogle-maps'])
    .config(function ($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl: 'home.html',
            controller: 'home'
        }).when('/login', {
            templateUrl: 'login.html',
            controller: 'navigation'
        }).when('/settings', {
            templateUrl: 'settings.html',
            controller: 'settings'
        }).when('/settings/addtracker', {
            templateUrl: 'addtracker.html',
            controller: 'addtracker'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    })
    .controller('home', function ($rootScope, $scope, $http) {
        var getMapData = function () {
            $http.get('/resource/1').success(function (data) {
                $scope.map = {
                    center: {latitude: data.coordinate.latitude, longitude: data.coordinate.longitude},
                    zoom: 8
                };
                $scope.marker = {
                    id: 0,
                    coords: {
                        latitude: data.coordinate.latitude,
                        longitude: data.coordinate.longitude
                    }
                };

                $scope.coordinate = data.coordinate;

                if ($rootScope.authenticated) {
                    setTimeout(getMapData, 2000);
                }
            });

        };

        getMapData();

    })
    .controller('navigation', function ($rootScope, $scope, $http, $location) {
        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {headers: headers}).success(function (data) {
                if (data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }).error(function () {
                $rootScope.authenticated = false;
                callback && callback();
            });

        }

        authenticate();
        $scope.credentials = {};
        $scope.login = function () {
            authenticate($scope.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        };

        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function () {
                $rootScope.authenticated = false;
            });
        }

    })
    .controller('settings', function ($rootScope, $scope, $http, $location) {
        var getAllTrackers = function () {
            $http.get('/tracker/').success(function (data) {
                $scope.settings = data;
            })
        };

        getAllTrackers();

        $scope.delete = function (id) {
            $http.delete('/tracker/' + id).success(getAllTrackers);
        }

    }).controller('addtracker', function ($rootScope, $scope, $http, $location) {
        $scope.add = function () {
            $http.put('/tracker/', $scope.tracker).success(function () {
                $location.path('/settings');
            });
        };

    });