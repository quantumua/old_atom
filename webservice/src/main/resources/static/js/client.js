angular.module('client', [])
    .controller('runner', function ($scope, $http, $timeout, $interval) {
        var self = this;
        self.messages = [];
        self.reports = [];
        var runTests = function (properties, suites, callback) {
            var fd = new FormData();
            fd.append('properties', properties);
            suites.forEach(function (s) {
                fd.append('suites[]', s);
            });
            fd.append('dataSources[]', []);
            $http.post('/af/upload/suite', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.messages.push('Upload successful');
                var reports = response.data.map(function (p) {
                    return {path: p, status: "N/A"}
                });
                reports.forEach(function (r) {
                    pollForStatus(r, 5000, 600000)
                });
                Array.prototype.push.apply(self.reports, reports);
                callback && callback();
            }, function (response) {
                self.messages.push(response.body);
                callback && callback();
            })
        };
        self.run = function () {
            self.messages = [];
            var badInput = false;
            if (angular.isUndefined($scope.properties) || !$scope.properties.length) {
                self.messages.push('No property file selected!');
                badInput = true;
            }
            if (angular.isUndefined($scope.suites) || !$scope.suites.length) {
                self.messages.push('No suite XML files selected!');
                badInput = true;
            }
            if (badInput) return;
            runTests($scope.properties[0], Array.from($scope.suites), null)
        };

        function pollForStatus(report, delay, timeout) {
            var interval = $interval(
                function () {
                    $http.post('/af/exists', report.path)
                        .then(function (r) {
                            if (r.data === true) {
                                report.status = 'DONE';
                                $interval.cancel(interval);
                            }
                        })
                }, delay);
            $timeout(function () {
                $interval.cancel(interval);
            }, timeout);
        }

    })
    .controller('jarUploader', function ($scope, $http) {
        var self = this;
        self.message = '';
        var uploadJar = function (jar, callback) {
            var fd = new FormData();
            fd.append('jar', jar);
            $http.post('/af/upload/tests', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.message = 'Upload successful';
                callback && callback();
            }, function (response) {
                self.message = response.body;
                callback && callback();
            })
        };
        self.upload = function () {
            self.message = '';
            var badInput = false;
            if (angular.isUndefined($scope.jar) || !$scope.jar.length) {
                self.message = 'No JAR file selected!';
                badInput = true;
            }
            if (badInput) return;
            uploadJar($scope.jar[0], null)
        }
    })
    .directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;
                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files);
                    });
                });
            }
        };
    }]);