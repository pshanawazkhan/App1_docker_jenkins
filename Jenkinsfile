pipeline {
    agent {
        docker {
            image 'maven:3.8.4-openjdk-17' // A Maven image with Docker
            args '-v /var/run/docker.sock:/var/run/docker.sock' // Mount Docker socket
        }
    }
    environment {
        DOCKER_IMAGE = 'pshanawaz/App1'
        DOCKER_TAG = '4.0'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'arha', url: 'https://github.com/pshanawazkhan/App1_docker_jenkins.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }

        stage('Deploy docker image') {
            steps {
                sh "docker run -d -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }
    }
    post {
        success {
            echo 'Docker image built and deployed successfully'
        }
        failure {
            echo 'Build failed. Docker image not deployed.....'
        }
    }
}
