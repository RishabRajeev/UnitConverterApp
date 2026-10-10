pipeline {
    agent any

    tools {
        maven 'Maven-3.8.7'
        jdk 'JDK-17'      
    }

    environment {
        IMAGE_NAME = 'rishabrajeev/unit-converter'
        DOCKER_CREDS = 'dockerhub-credentials'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test & Package') {
            steps {
                echo 'Running unit tests and building JAR...'
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh "docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
                sh "docker tag ${IMAGE_NAME}:${BUILD_NUMBER} ${IMAGE_NAME}:latest"
            }
        }

        stage('Docker Push') {
            steps {
                echo 'Pushing to Docker Hub...'
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKER_CREDS}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh "docker push ${IMAGE_NAME}:${BUILD_NUMBER}"
                    sh "docker push ${IMAGE_NAME}:latest"
                }
            }
        }

        stage('Deploy & Smoke Test') {
            steps {
                echo 'Deploying locally and validating...'
                sh '''
                    docker stop unit-converter || true
                    docker rm unit-converter || true
                    docker run -d --name unit-converter -p 8080:8080 ${IMAGE_NAME}:latest
                    
                    # Wait for container startup
                    sleep 5
                    
                    # Smoke test the endpoint
                    curl -f "http://localhost:8080/api/convert/c-to-f?value=100" || exit 1
                '''
            }
        }
    }

    post {
        always {
            sh 'docker logout || true'
        }
        success {
            echo "Pipeline succeeded! Converter API is live on port 8080."
        }
        failure {
            echo "Pipeline failed. Check stage logs for details."
        }
    }
}