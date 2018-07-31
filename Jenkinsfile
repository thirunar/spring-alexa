pipeline {
    agent {
        docker {
            image 'maven:3.5.0-jdk-8-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
