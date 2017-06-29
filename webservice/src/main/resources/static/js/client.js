angular.module('client', ['ui.bootstrap'])
    .controller('runner', function ($scope, $http, $timeout, $interval) {
        var self = this;
        var reportRefreshTimeout = 900000;
        var reportRefreshDelay = 5000;
        self.messages = [];
        self.tests = [];
        self.isCollapsed = true;
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

        self.pollForStatus = function (test) {
            test.data.status = 'UPDATING';
            pollForStatus(test, reportRefreshDelay, reportRefreshTimeout);
        };

        self.abort = function (test) {
            self.messages = [];
            $http.delete('/atom/tests/' + test.data.id)
                .then(function (r) {
                    self.messages.push("Abort command sent");
                }, function () {
                    self.messages.push("Failed to abort test");
                })
        };

        function runTests(properties, suites, tempJar) {
            var fd = new FormData();
            fd.append('properties', properties);
            suites.forEach(function (s) {
                fd.append('suites[]', s);
            });
            fd.append('tempJar', tempJar);
            $http.post('/atom/upload/test', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.messages.push('Upload successful');
                var tests = response.data.filter(x => x).map(testFormatter).map(function (test) {
                    return {'data': test}
                });
                tests.forEach(function (test) {
                    pollForStatus(test, reportRefreshDelay, reportRefreshTimeout);
                });
                Array.prototype.push.apply(self.tests, tests);
            }, function (response) {
                self.messages.push(response.body);
            });
        }

        function testFormatter(test) {
            if (!!test.suites) {
                test.suites = test.suites.map(function (suite) {
                    return suite.substr(suite.lastIndexOf('/') + 1);
                });
            }
            if (!!test.time) {
                test.date = [
                    withLeadingZero(test.time.dayOfMonth),
                    withLeadingZero(test.time.monthValue),
                    test.time.year
                ].join('/');
                test.time = [
                    withLeadingZero(test.time.hour),
                    withLeadingZero(test.time.minute),
                    withLeadingZero(test.time.second)
                ].join(':');
            }
            return test;
        }

        function withLeadingZero(input) {
            return ("0" + input).slice(-2);
        }

        function pollForStatus(test, delay, timeoutDuration) {
            var interval = $interval(
                function (timeoutPromise) {
                    return function () {
                        $http.get('/atom/status/' + test.data.id)
                            .then(function (r) {
                                if (r.data === '') {
                                    return;
                                }
                                test.data = testFormatter(r.data);
                                $timeout.cancel(timeoutPromise);
                                $interval.cancel(interval);
                            })
                    }
                }($timeout(function () {
                        $interval.cancel(interval);
                        test.data.status = 'TIMED OUT';
                    },
                    timeoutDuration)),
                delay);
        }
    })
    .controller('jarUploader', function ($scope, $http) {
        var self = this;
        self.message = '';
        function uploadJar(jar) {
            var fd = new FormData();
            fd.append('jar', jar);
            $http.post('/atom/upload/library', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.message = 'Upload successful';
            }, function (response) {
                self.message = response.body;
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
    .controller('fileUploader', function ($scope, $http) {
        var self = this;
        self.message = '';
        function uploadJar(expectedCfdAssets) {
            var fd = new FormData();
            fd.append('expectedCfdAssets', expectedCfdAssets);
            $http.post('/atom/upload/expectedCfdAssets', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                self.message = 'Upload successful';
            }, function (response) {
                self.message = response.body;
            });
        }

        self.uploadExpectedCfdAssets = function () {
            self.message = '';
            var badInput = false;
            if (angular.isUndefined($scope.expectedCfdAssets) || !$scope.expectedCfdAssets) {
                self.message = 'No file selected!';
                badInput = true;
            }
            if (badInput) return;
            uploadJar($scope.expectedCfdAssets[0]);
        };
    })
    .controller('scheduler', function ($scope, $http) {
        var self = this;
        self.messages = [];
        self.tests = [];
        self.cronEnabled = false;
        function scheduleTests(name, emailAddress, properties, suites, cron) {
            var fd = new FormData();
            fd.append('name', name);
            fd.append('emailAddress', emailAddress);
            fd.append('properties', properties);
            suites.forEach(function (suite) {
                fd.append('suites[]', suite);
            });
            if (!angular.isUndefined($scope.cron)) {
                fd.append('cronExpression', cron);
            }
            $http.post('/atom/upload/test/scheduled', fd, {
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
            scheduleTests($scope.name, $scope.emailAddress, $scope.properties[0], Array.from($scope.suites), $scope.cron)
        };

        self.stop = function (test) {
            self.messages = [];
            $http.delete('/atom/scheduled/' + test.id + '/stop').then(function (response) {
                self.messages.push('Stopped job successfully');
            }, function (response) {
                self.messages.push(response.body);
            });
        };
        self.abort = function (test) {
            self.messages = [];
            $http.delete('/atom/scheduled/' + test.id + '/abort').then(function (r) {
                self.messages.push("Abort command sent");
            }, function () {
                self.messages.push("Failed to abort test");
            })
        };
        self.getScheduledTests = function () {
            self.messages = [];
            self.tests = [];
            $http.get('/atom/scheduled').then(function (response) {
                Array.prototype.push.apply(self.tests, response.data.filter(x => x).map(testFormatter));
            })
        };
        function testFormatter(test) {
            if (!!test.suites) {
                test.suites = test.suites.map(function (suite) {
                    return suite.substr(suite.lastIndexOf('/') + 1);
                });
            }
            if (!!test.time) {
                test.date = [
                    withLeadingZero(test.time.dayOfMonth),
                    withLeadingZero(test.time.monthValue),
                    test.time.year
                ].join('/');
                test.time = [
                    withLeadingZero(test.time.hour),
                    withLeadingZero(test.time.minute),
                    withLeadingZero(test.time.second)
                ].join(':');
            }
            return test;
        }

        function withLeadingZero(input) {
            return ("0" + input).slice(-2);
        }

    })
    .controller('version', function ($http, $interval) {
        var self = this;
        var versionRefreshDelay = 5000;
        self.core = null;
        self.tests = null;
        function getCoreVersion() {
            $http.get('/atom/version/core')
                .then(function (r) {
                    self.core = r.data['core.version'];
                })
        }

        function getTestsVersion() {
            $interval(
                function () {
                    $http.get('/atom/version/testslibrary')
                        .then(function (r) {
                            self.tests = r.data['testslibrary.version'];
                        })
                }, versionRefreshDelay);
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