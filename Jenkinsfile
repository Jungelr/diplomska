node {
    environment {
        JAVA_HOME = '/usr/lib/jvm/jdk-22.0.1/bin/java'
    }
    stage('Clone repostiroy') {
        checkout scm
    }
    stage('MVN Package'){
        sh "mvn clean package"
    }
    stage('Build Docker Image') {
        sh 'docker build -t jungelr/diplomska:0.0.1 .'
    }
}