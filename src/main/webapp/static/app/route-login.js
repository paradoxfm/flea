appLog.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/");
    $stateProvider
        .state('user_login', {
            url: "/",
            views: {
                "logreg": {
                    templateUrl: "/t/account/login"
                }
            }
            /*controller : "CategoryController as ctgController",
             resolve: {
             async: ['ItemService', function(ItemService) {
             return ItemService.fetchCategoryList();
             }]
             }*/
        })
        .state('user_register', {
            url: "/user/register",
            controller: 'UserController',
            views: {
                "logreg": {
                    templateUrl: "/t/account/register"
                }
            }
        })
        .state('user_restore', {
            url: "/user/restore",
            views: {
                "logreg": {
                    templateUrl: "/t/account/restore"
                }
            }
        });

});
