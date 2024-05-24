node {

    stage('Clone repostiroy') {
        checkout scm
    }
    stage('MVN Package'){
        environment {
            JAVA_HOME = '/usr/lib/jvm/jdk-22.0.1'
            PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
        }
        sh "mvn clean package"
    }
    stage('Build Docker Image') {
        sh 'docker build -t jungelr/diplomska:0.0.1 . --platform=linux/arm64'
    }
}