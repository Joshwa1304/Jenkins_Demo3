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

        stage('Copy Artifact') {
            steps {
                bat 'copy Hello.jar C:\\DevOpsArtifacts\\Hello-Java\\Hello.jar /Y'
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'Hello.jar', fingerprint: true
        }
    }
}