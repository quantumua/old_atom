angular.module('client', [])
    .controller('runner', function ($scope, $http, $timeout, $interval) {
        var self = this;
        self.messages = [];
        self.reports = [];
        function runTests(properties, suites, tempJar) {
            var fd = new FormData();
            fd.append('properties', properties);
            suites.forEach(function (s) {
                fd.append('suites[]', s);
            });
            fd.append('tempJar', tempJar);
            $http.post('/af/upload/suite', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.messages.push('Upload successful');
                var reports = response.data.map(function (p) {
                    return {path: p, status: "N/A"}
                });
                reports.forEach(function (r) {
                    pollForStatus(r, 5000, 600000);
                });
                Array.prototype.push.apply(self.reports, reports);
            }, function (response) {
                self.messages.push(response.body);
            });
        }

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
            if (angular.isUndefined($scope.tempJar) || !$scope.tempJar.length) {
                $scope.tempJar = [null];
            }
            if (badInput) return;
            runTests($scope.properties[0], Array.from($scope.suites), $scope.tempJar[0]);
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
        function uploadJar(jar, callback) {
            var fd = new FormData();
            fd.append('jar', jar);
            $http.post('/af/upload/library', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.message = 'Upload successful';
                callback && callback();
            }, function (response) {
                self.message = response.body;
                callback && callback();
            });
        }

        self.upload = function () {
            self.message = '';
            var badInput = false;
            if (angular.isUndefined($scope.jar) || !$scope.jar.length) {
                self.message = 'No JAR file selected!';
                badInput = true;
            }
            if (badInput) return;
            uploadJar($scope.jar[0]);
        };
    })
    .controller('scheduler', function ($scope, $http) {
        var self = this;
        self.messages = [];
        self.tests = [];
        function scheduleCronTests(name, emailAddress, properties, suites, cron) {
            var fd = new FormData();
            fd.append('name', name);
            fd.append('emailAddress', emailAddress);
            fd.append('properties', properties);
            suites.forEach(function (s) {
                fd.append('suites[]', s);
            });
            fd.append('cronExpression', cron);
            $http.post('/af/upload/suite/scheduled', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.messages.push('Scheduled job successfully');
            }, function (response) {
                self.messages.push(response.body);
            });
        }

        function scheduleRepeatingTests(name, emailAddress, properties, suites) {
            var fd = new FormData();
            fd.append('name', name);
            fd.append('emailAddress', emailAddress);
            fd.append('properties', properties);
            suites.forEach(function (s) {
                fd.append('suites[]', s);
            });
            $http.post('/af/upload/suite/repeating', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.messages.push('Scheduled job successfully');
            }, function (response) {
                self.messages.push(response.body);
            });
        }

        self.schedule = function () {
            self.messages = [];
            var badInput = false;
            if (angular.isUndefined($scope.name) || !$scope.name.length) {
                self.messages.push('No name specified!');
                badInput = true;
            }
            if (angular.isUndefined($scope.properties) || !$scope.properties.length) {
                self.messages.push('No property file selected!');
                badInput = true;
            }
            if (angular.isUndefined($scope.suites) || !$scope.suites.length) {
                self.messages.push('No suite XML files selected!');
                badInput = true;
            }
            if ((angular.isUndefined($scope.isRepeating) || !$scope.isRepeating) && angular.isUndefined($scope.cron)) {
                self.messages.push('No scheduling behavior specified!');
                badInput = true;
            }
            if (badInput) return;
            if ($scope.isRepeating) {
                scheduleRepeatingTests($scope.name, $scope.emailAddress, $scope.properties[0], Array.from($scope.suites));
            } else {
                scheduleCronTests($scope.name, $scope.emailAddress, $scope.properties[0], Array.from($scope.suites), $scope.cron)
            }
        };
        self.stop = function (name) {
            self.messages = [];
            $http.delete('/af/scheduled/' + name + '/stop').then(function (response) {
                self.messages.push('Stopped job successfully');
            }, function (response) {
                self.messages.push(response.body);
            });
        };
        self.getScheduledTests = function () {
            self.messages = [];
            self.tests = [];
            $http.get('/af/scheduled').then(function (response) {
                Array.prototype.push.apply(self.tests, response.data);
            })
        }
    })
    .controller('version', function ($http, $interval) {
        var self = this;
        self.core = null;
        self.tests = null;
        function getCoreVersion() {
            $http.get('/af/version/core')
                .then(function (r) {
                    self.core = r.data['core.version'];
                })
        }

        function getTestsVersion() {
            $interval(
                function () {
                    $http.get('/af/version/testslibrary')
                        .then(function (r) {
                            self.tests = r.data['testslibrary.version'];
                        })
                }, 5000);
        }

        getCoreVersion();
        getTestsVersion();

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