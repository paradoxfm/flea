var cardApp = angular.module('cardApp', ['ngAnimate', 'ui.router', 'angular-loading-bar'/*, 'cardControllers'*/]);

cardApp.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = false;
}]);

cardApp.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false,
        rewriteLinks: false
    });
    //$locationProvider.hashPrefix('!');
}]);

cardApp.run(['$state', function($state) {
    $state.go('card_list_contact');
}]);
