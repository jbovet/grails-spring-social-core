/* Copyright 2012 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
  inherits("global") {
    // uncomment to disable ehcache
    // excludes 'ehcache'
  }
  log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
  repositories {
    grailsPlugins()
    grailsHome()
    grailsCentral()

    // from public Maven repositories
    mavenLocal()
    mavenCentral()

    mavenRepo "http://maven.springframework.org/release"
    mavenRepo "http://maven.springframework.org/snapshot"
    mavenRepo "http://maven.springframework.org/milestone"

    grailsRepo "http://grails.org/plugins"
  }
  dependencies {
    def springSocialVersion = "1.0.2.RELEASE"

    compile("org.springframework.social:spring-social-core:${springSocialVersion}") { transitive = false }
    compile("org.springframework.social:spring-social-web:${springSocialVersion}") { transitive = false }

    compile("org.springframework.security:spring-security-crypto:3.1.0.RELEASE") { transitive = false }
    compile("javax.inject:javax.inject:1")

    test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
    test "org.gebish:geb-spock:0.9.0"
  }
  plugins {

    compile(":spring-security-core:1.2.7.3")

    test(":geb:0.9.0", ":functional-test-development:0.9.4", ":fixtures:1.2", ":codenarc:0.18.1", ":code-coverage:1.2.6", ":guard:1.0.7", ":hibernate:${grailsVersion}") {
      export = false
    }
    test(":spock:0.7") {
      exclude "spock-grails-support"
      export = false
    }
    build(":tomcat:${grailsVersion}", ":hibernate:${grailsVersion}", ':release:2.2.1', ':rest-client-builder:1.0.3') {
      export = false
    }
  }
}

grails.release.scm.enabled = false
grails.project.repos.default = "grailsCentral"

coverage {
  exclusions = [
    "DefaultSpringSocialConfig*",
    "SpringSocialCoreDefaultConfig*"
  ]
  enabledByDefault = true
}