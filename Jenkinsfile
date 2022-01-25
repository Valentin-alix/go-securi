#!/usr/bien/env groovy
node {
    stage('Checkout'){
        checkout scm
        def pom = readMavenPom file : 'pom.xml'
        if (pom) {
            echo "Building version ${pom.version}"
        }
        sh "chmod +x ./mvnw"
    }
    stage('Build'){
        withEnv(["PATH+jdk=${tool 'JAVA 17'}/bin"]){
            sh "./mvnw compile"
        }
    }
    stage('Unit-Tests') {
        withEnv(["PATH+jdk=${tool 'JAVA 17'}/bin"]){
            if (env.SKIP_TESTS){
                sh "./mvnw test"
            }
        }
    }
}
