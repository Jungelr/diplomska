pipeline {
    agent any
    stages {
        stage('Clone repostiroy') {
            steps {
                checkout scm
            }
        }
        stage('MVN Package'){
            steps {
                def mvnHome = tool name: 'maven-3', type: 'maven'
                sh "${mvnHome}/bin/mvn clean package"
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t jungelr/diplomska:0.0.1 .'
            }
        }
    }
}