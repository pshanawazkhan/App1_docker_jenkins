pipeline {
    agent any

    tools {
        maven 'maven' // Name defined in the Global Tool Configuration
        jdk 'jdk17'   // JDK tool
    }

    environment {
        MAVEN_OPTS = '-Xms256m -Xmx512m'
        DOCKER_IMAGE = 'pshanawaz/App1'               // Docker image name
        DOCKER_TAG = '4.0'                                     // Tag for the Docker image
        DOCKER_CREDENTIALS_ID = 'dockerhub_credentials'    // Credentials ID which we configure ->Id:- dockerhub_credentials
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'arha', url: 'https://github.com/pshanawazkhan/App1_docker_jenkins.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'maven'
                    // Build the project using Maven
                    bat "${mvnHome}\\bin\\mvn clean install"
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Build Docker image
                    bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        //deploying docker image in docker container
        
        stage('Deploy docker image') {
    steps {
        script {
            // Run Docker container
            bat "docker run -d -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
        }
    }
}
        
        
    }

    post {
        success {
            echo 'Docker image build and deployed  sucessfully'
        }
        failure {
            echo 'Build failed. Docker image not depoyed.....'
        }
    }
}