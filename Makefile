COMMIT_ID_HASH ?= $(shell git rev-parse HEAD)
VERSION_BRANCH ?= $(shell git symbolic-ref --short HEAD)
APP     ?= notification-service
IMAGE   ?= $(APP):$(COMMIT_ID_HASH)
DB_IMAGE ?= digital-mortgage-db
DB_IMAGE_TEST ?= digital-mortgage-db-test
ZIPKIN_IMAGE ?= zipkin-server
VERSION ?= $(VERSION_BRANCH)-$(COMMIT_ID_HASH)
.DEFAULT_GOAL := list

.PHONY: list
clean:
	@echo "Clean All Container"
	- docker rm -f $($DB_IMAGE)
	- docker rm -f $(DB_IMAGE_TEST)
	- docker rm -f $(APP)
list:
	@echo "Make sure u set specific target dude !!"
	@LC_ALL=C $(MAKE) -pRrq -f $(lastword $(MAKEFILE_LIST)) : 2>/dev/null | awk -v RS= -F: '/^# File/,/^# Finished Make data base/ {if ($$1 !~ "^[#.]") {print $$1}}' | sort | egrep -v -e '^[^[:alnum:]]' -e '^$@$$'
test-all:
	@echo "Start Unit Test All Module"
	- make docker-db-test
	- mvn surefire-report:report
	- mvn site -DgenerateReports=false
	- open app/target/site/surefire-report.html
test-all-container:
	@echo "Start Unit Test All Module By Docker Container"
	- make docker-build-test
	- docker-compose -f deploy/docker-compose.test.yml down --remove-orphans
	- export VERSION=$(VERSION) && docker-compose -f deploy/docker-compose.test.yml up
maven-verify:
	- mvn clean verify -Dmaven.test.skip
build-war:
	- make compile-all
	- cd app && mvn -P release clean install -Dmaven.test.skip
compile-entrypoint:
	- cd entrypoint && mvn clean install -Dmaven.test.skip
compile-dataprovider:
	- cd dataprovider && mvn clean install -Dmaven.test.skip
compile-helper:
	- cd helper && mvn clean install -Dmaven.test.skip
compile-core:
	- cd core && mvn clean install -Dmaven.test.skip
compile-app:
	- cd app && mvn clean install -Dmaven.test.skip
run-spring-boot-start-db:
	- make docker-stop-apps
	- make docker-db-dev
	- make compile-all
	- cd app && mvn spring-boot:run
run-spring-boot-only:
	- cd app && mvn spring-boot:run
run-spring-boot:
	- make compile-all
	- cd app && mvn spring-boot:run
docker-compose-run-apps:
	- make maven-verify
	- make docker-build-embed-tomcat
	- make docker-stop-apps
	- docker-compose -f deploy/docker-compose.local.yml down
	- docker network rm digital-mortgage-network
	- docker network create digital-mortgage-network
	- export VERSION=$(VERSION) && docker-compose -f deploy/docker-compose.local.yml up -d
docker-compose-stop-apps:
	- docker-compose -f deploy/docker-compose.local.yml down
docker-db-dev:
	- docker-compose -f deploy/docker-compose.local.yml down --remove-orphans
	- docker-compose -f deploy/docker-compose.local.yml up -d $(DB_IMAGE)
docker-db-test:
	- docker-compose -f deploy/docker-compose.test.yml down --remove-orphans
	- docker-compose -f deploy/docker-compose.test.yml up -d $(DB_IMAGE_TEST)
docker-zipkin-start-dev:
	- docker-compose -f deploy/docker-compose.local.yml down --remove-orphans
	- docker-compose -f deploy/docker-compose.local.yml up -d $(DB_IMAGE)
	- docker-compose -f deploy/docker-compose.local.yml up -d $(ZIPKIN_IMAGE)
docker-zipkin-stop-dev:
	- docker-compose -f deploy/docker-compose.local.yml down --remove-orphans
docker-build-embed-tomcat:
	- docker build -f docker/Dockerfile -t $(APP):embed-$(VERSION) .