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

        stage('Publish to HCL Deploy') {
            steps {
                step([
                    $class: 'UCDeployPublisher',

                    siteName: 'DevOps-Deploy',

                    component: [
                        $class: 'com.urbancode.jenkins.plugins.ucdeploy.VersionHelper$VersionBlock',

                        componentName: 'Jenkins-Hello-Component',

                        createComponent: [
                            $class: 'com.urbancode.jenkins.plugins.ucdeploy.ComponentHelper$CreateComponentBlock',

                            componentTemplate: '',

                            componentApplication: 'Jenkins-Hello-Application'
                        ],

                        delivery: [
                            $class: 'com.urbancode.jenkins.plugins.ucdeploy.DeliveryHelper$Push',

                            pushVersion: "${BUILD_NUMBER}",

                            baseDir: "${WORKSPACE}",

                            fileIncludePatterns: 'Hello.jar',

                            fileExcludePatterns: '',

                            pushDescription: "Pushed from Jenkins build ${BUILD_NUMBER}"
                        ]
                    ]
                ])
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'Hello.jar',
                             fingerprint: true

            echo "Build ${BUILD_NUMBER} completed successfully."
            echo "Version ${BUILD_NUMBER} published to HCL Deploy."
        }

        failure {
            echo "Build ${BUILD_NUMBER} failed. Check Console Output."
        }
    }
}