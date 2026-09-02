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
                bat '''
                    if not exist "C:\\DevOpsArtifacts\\Hello-Java\\%BUILD_NUMBER%" mkdir "C:\\DevOpsArtifacts\\Hello-Java\\%BUILD_NUMBER%"
                    copy "Hello.jar" "C:\\DevOpsArtifacts\\Hello-Java\\%BUILD_NUMBER%\\Hello.jar" /Y
                '''
            }
        }

        stage('Publish to HCL Deploy') {
            steps {
                step([
                    $class: 'UCDeployPublisher',
                    siteName: 'local',

                    component: [
                        $class: 'com.urbancode.jenkins.plugins.ucdeploy.VersionHelper$VersionBlock',
                        componentName: 'Hello-Java-Component',

                        delivery: [
                            $class: 'com.urbancode.jenkins.plugins.ucdeploy.DeliveryHelper$Pull',

                            pullProperties: "FileSystemImportProperties/name=${BUILD_NUMBER}\nFileSystemImportProperties/description=Published from Jenkins build ${BUILD_NUMBER}",

                            pullSourceType: 'File System',

                            pullSourceProperties: 'FileSystemComponentProperties/basePath=C:\\DevOpsArtifacts\\Hello-Java',

                            pullIncremental: false
                        ]
                    ]
                ])
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'Hello.jar', fingerprint: true
        }
    }
}