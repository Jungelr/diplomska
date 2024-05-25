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
        stage('Deploy') {
            steps {
                withCredentials([file(credentialsId: 'KEY_STORE', variable: 'KEYSTORE')]) {
                    sh 'docker compose --env-file $KEYSTORE -i up -d'
                }
            }
        }
    }
}