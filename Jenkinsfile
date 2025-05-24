pipeline {
    agent any  // Use any available agent

    tools {
        maven 'Maven'  // Ensure this matches the name configured in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/icemberg/MyMavenSeleniumApp02.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'  // Run Maven build
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'  // Run unit tests
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
                // Start the JAR application
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
