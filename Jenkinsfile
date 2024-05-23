node {
    stage('Clone repostiroy') {
        checkout scm
    }
    stage('MVN Package'){
        def mvnHome = tool name: 'maven-3', type: 'maven'
        sh "${mvnHome}/bin/mvn clean package"
    }
    stage('Build Docker Image'){
        sh 'docker build -t jungelr/diplomska:0.0.1 .'
    }
}