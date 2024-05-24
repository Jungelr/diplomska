pipeline {
    agent any
    stages {
        stage('Clone repostiroy') {
            steps {
                checkout scm
            }
        }
        stage('MVN Package'){
            tools {
                jdk "jdk-22.0.1"
            }
            steps {
                sh "mvn clean package"
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t jungelr/diplomska:0.0.1 . --platform=linux/arm64'
            }
        }
    }
}