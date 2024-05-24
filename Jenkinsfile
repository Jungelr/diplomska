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
                withCredentials([string(credentialsId: 'KEY_STORE_PATH', variable: 'KEY_STORE_PATH'), string(credentialsId: 'KEY_STORE_PASSWORD', variable: 'KEY_STORE_PASSWORD')]) {
                    sh "docker compose -e KEY_STORE_PATH=$KEY_STORE_PATH -e KEY_STORE_PASSWORD=$KEY_STORE_PASSWORD up -d"
                }
            }
        }
    }
}