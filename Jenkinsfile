pipeline {
    agent any
    stages {
        stage('Clone repostiroy') {
            steps {
                checkout scm
            }
        }
        stage('MVN Package'){
            agent {
                docker {
                    image 'maven:3.9-eclipse-temurin-22-alpine --platform=linux/arm64/v8'
                    args '-v $HOME/.m2:/root/.m2'
                }
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