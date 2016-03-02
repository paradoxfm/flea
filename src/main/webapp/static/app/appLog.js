var appLog = angular.module('appLog', ['ngAnimate', 'ui.router', 'angular-loading-bar']);

appLog.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = false;
}]);