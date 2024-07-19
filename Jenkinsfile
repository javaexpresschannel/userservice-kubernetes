pipeline {

    agent any
    
    tools {
        maven "maven"
    }

    environment {
        APP_NAME = "springboot-docker-cicd"
        DOCKER_USER = "javaexpress"
    }

    stages {
        stage("SCM checkout") {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/javaexpresschannel/01_devops_springboot_maven']])
            }
        }

        stage("Build Process") {
            steps {
                script {
                    bat 'mvn clean install'
                }
            }
        }
        stage('Set Environment Variables') {
            steps {
                script {
                    env.IMAGE_NAME = "${env.DOCKER_USER}/${env.APP_NAME}"
                }
            }
        }

        stage("Build Image") {
            steps {
                script {
                    bat 'docker build -t %IMAGE_NAME%:latest .'
                }
            }
        }

        stage("Deploy Image to Hub") {
            steps {
                withCredentials([string(credentialsId: 'dp', variable: 'dp')]) {
                    bat 'docker login -u javaexpress -p %dp%'
                    bat 'docker push %IMAGE_NAME%:latest'
                }
            }
        }
        stage('Kubernetes Deployment') {
            steps {
                script {
                    // Running the kubectl apply command
                    bat 'kubectl apply -f k8s-app.yaml'
                }
            }
        }
    }
}
