pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        // Set a default fallback; actual value will be set in stage
        DISPLAY = '192.168.3.123:0'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/icemberg/MyMavenSeleniumApp02.git'
            }
        }

        stage('Set DISPLAY Env') {
            steps {
                script {
                    def ip = sh(
                        script: "cat /etc/resolv.conf | grep nameserver | awk '{print \$2}' | head -1",
                        returnStdout: true
                    ).trim()
                    env.DISPLAY = "${ip}:0"
                    echo "DISPLAY is set to ${env.DISPLAY}"
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Kill Chrome') {
            steps {
                sh 'pkill chrome || true'
                sh 'pkill chromedriver || true'
            }
        }

        stage('Run Application') {
            steps {
                sh 'mvn exec:java -Dexec.mainClass="com.example.App"'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
