cardApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

    //$urlRouterProvider.otherwise("/cards/contacts");
    //$urlRouterProvider.when('/', '/cards/contacts');
    //$urlRouterProvider.when('', '/cards/contacts');
    $stateProvider
        .state('card_list_contact', {
            url: "/cards/contacts",
            sticky: true,

            views: {
                "content": {
                    templateUrl: "/t/card/contacts"
                }
            }
        })
        .state('card_list_owner', {
            url: "/cards/my",
            views: {
                "content": {
                    templateUrl: "/t/card/my"
                }
            }
        })
        .state('create_new_card', {
            url: "/cards/create",
            resolve: {
                newCard: function ($http) {
                    console.log("route resolve");
                }
            },
            views: {
                "content": {
                    templateUrl: "/t/card/editor"
                }
            }
        });

}]);

