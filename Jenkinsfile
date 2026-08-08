pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'javac Hello.java'
            }
        }

        stage('Package') {
            steps {
                bat 'jar cf Hello.jar Hello.class'
            }
        }

        stage('Run') {
            steps {
                bat 'java Hello'
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'Hello.jar', fingerprint: true
        }
    }
}