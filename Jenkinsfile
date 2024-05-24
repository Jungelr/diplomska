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
                sh "docker compose -e KEY_STORE_PATH=$KEY_STORE_PATH -e KEY_STORE_PASSWORD=$KEY_STORE_PASSWORD up -d"
            }
        }
    }
}