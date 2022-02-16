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
        withEnv(["PATH+jdk=${tool 'JAVA 11'}/bin"]){
            sh "./mvn package"
            archiveArtifacts artifacts: 'target/gosecuri-1.0-SNAPSHOT.jar', fingerprint: true
        }
    }
    stage('Unit-Tests') {
        withEnv(["PATH+jdk=${tool 'JAVA 11'}/bin"]){
            if (env.SKIP_TESTS){
                sh "./mvnw test"
            }
        }

    }
}
