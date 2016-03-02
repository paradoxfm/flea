appLog.controller('LoginController', function ($scope, $location, $http, $state) {

    $scope.userCred = {
        username: '',
        password: '',
        rememberMe: false
    };

    $scope.login = function () {
        $http({
            method: 'POST',
            url: '/auth/login_check',
            data: $.param($scope.userCred),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function (data) {
            window.location = '/';
        });
    };

    $scope.regInfo = {};

    $scope.register = function () {
        $http.post('/user/register', $scope.regInfo).then(function (data) {
            $state.go('user_login');
        }, function(data) {
            data.toString();
        });
    };

    $scope.restoreInfo = {};

    $scope.restore = function() {
        $http.post('/user/restore', $scope.restoreInfo).then(function (data) {
            $state.go('user_login');
        });
    };
});