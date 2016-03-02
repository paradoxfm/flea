//Created by paradoxfm on 29.01.2016.
appLog.factory('UserService', ['$http', '$q', function ($http, $q) {

    return {
        fetchAllUsers: function () {
            return $http.get('/user/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching users');
                        return $q.reject(errResponse);
                    }
                );
        },
        createUser: function (user) {
            return $http.post('/user/register', user).then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    console.error('Error while creating user service');
                    return $q.reject(errResponse);
                }
            );
        },
        updateUser: function (user, id) {
            return $http.put('/user/' + id, user)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating user');
                        return $q.reject(errResponse);
                    }
                );
        },
        deleteUser: function (id) {
            return $http.delete('/user/' + id)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting user');
                        return $q.reject(errResponse);
                    }
                );
        }
    };
}]);