pipeline {
    agent any

    environment {
        IMAGE_NAME = 'leave-service:2024sl70047'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Code Inspection') {
            steps {
                withCredentials([
                    string(credentialsId: 'sonarqube-token',
                           variable: 'SONAR_TOKEN')
                ]) {
                    sh '''
                    mvn org.sonarsource.scanner.maven:sonar-maven-plugin:5.0.0.4389:sonar \
                    -Dsonar.projectKey=leave-service-2024sl70047 \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.token=$SONAR_TOKEN \
                    -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Deploy to Staging') {
            steps {
                sh '''
                docker rm -f leave-service-staging || true

                docker run -d \
                --name leave-service-staging \
                -p 8085:8080 \
                -e APP_ENV=staging \
                $IMAGE_NAME
                '''
            }
        }

        stage('Verify Staging') {
            steps {
                sh '''
                sleep 5
                curl --fail http://localhost:8085
                '''
            }
        }

        stage('Manual Approval') {
            steps {
                input message: 'Staging verified. Approve production deployment?',
                      ok: 'Approve Production'
            }
        }

        stage('Deploy to Production') {
            steps {
                sh '''
                docker rm -f leave-service-production || true

                docker run -d \
                --name leave-service-production \
                -p 8086:8080 \
                -e APP_ENV=production \
                $IMAGE_NAME
                '''
            }
        }

        stage('Verify Production') {
            steps {
                sh '''
                sleep 5
                curl --fail http://localhost:8086
                '''
            }
        }
    }
}
