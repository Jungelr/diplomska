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
                sh "mvn clean package -Pprod"
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t jungelr/diplomska .'
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([file(credentialsId: 'KEY_STORE', variable: 'KEYSTORE')]) {
                    sh 'docker compose --env-file $KEYSTORE up -d'
                }
            }
        }
    }
}