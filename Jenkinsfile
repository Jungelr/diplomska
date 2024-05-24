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
                    image 'sha256:d7fb9d2e3fa41bb7a679e41f20cf04fd122aeb8ba3093ec720443be31d4eafae'
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