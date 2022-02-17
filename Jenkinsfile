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


    stage('Build')
    {
        withEnv(["PATH+jdk=${tool 'JAVA 11'}/bin"]){
            sh "npm install"
            sh "npm run build"
            sh "./mvnw package -DskipTests=true"
            archiveArtifacts artifacts: 'target/gosecuri-1.0-SNAPSHOT.jar', fingerprint: true
            sh "java -jar target/gosecuri-1.0-SNAPSHOT.jar"

        }
    }

    stage('Unit-Tests') {
        withEnv(["PATH+jdk=${tool 'JAVA 11'}/bin"]){
        sh "./mvnw test"
        }
    }
}
