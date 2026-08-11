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
                bat 'jar cfe Hello.jar Hello Hello.class'
            }
        }

        stage('Run') {
            steps {
                bat 'java -jar Hello.jar'
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