var fleApp = angular.module('fleApp', ['ngAnimate', 'ui.router', 'angular-loading-bar'/*, 'cardControllers'*/]);

fleApp.config(['$locationProvider', 'cfpLoadingBarProvider', function ($locationProvider, cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = false;

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false,
        rewriteLinks: false
    });
    //$locationProvider.hashPrefix('!');
}]);

fleApp.run(['$state', function($state) {
    //$state.go('card_list_contact');
}]);
