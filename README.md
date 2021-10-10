# DimDim

#Codigo Pipeline

```
pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "apache-maven"
    }

    stages {
        stage('Responsável pela aprovação') {
            steps {
                script {
                    timeout(time: 2, unit: "HOURS") {
                    def userInput = input(
                    id: 'userInput', message: 'Aprova o Deploy ?', parameters: [
                    [$class: 'TextParameterDefinition', defaultValue: 'Não', description: 'Realizar Deploy ?', name: 'Executar'] ] )
                    echo (userInput)
                    }
                }
            }
        }

        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/guhdalla/DimDim.git/'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'target/*.jar'
                    sh 'java -jar /var/lib/jenkins/workspace/DimDImPipeline/target/DimDim-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }
}

pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "apache-maven"
    }

    stages {
        stage('Responsável pela aprovação') {
            steps {
                script {
                    timeout(time: 2, unit: "HOURS") {
                    def userInput = input(
                    id: 'userInput', message: 'Aprova o Deploy ?', parameters: [
                    [$class: 'TextParameterDefinition', defaultValue: 'Não', description: 'Realizar Deploy ?', name: 'Executar'] ] )
                    echo (userInput)
                    }
                }
            }
        }

        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/guhdalla/DimDim.git/'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'target/*.jar'
                    sh 'java -jar /var/lib/jenkins/workspace/DimDImPipeline/target/DimDim-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }
}

pipeline {
    agent any
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "apache-maven"
    }
    stages {
        stage('Responsável pela aprovação') {
            steps {
                script {
                    timeout(time: 2, unit: "HOURS") {
                    def userInput = input(
                    id: 'userInput', message: 'Aprova o Deploy ?', parameters: [
                    [$class: 'TextParameterDefinition', defaultValue: 'Não', description: 'Realizar Deploy ?', name: 'Executar'] ] )
                    echo (userInput)
                    }
                }
            }
        }
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/guhdalla/DimDim.git/'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'target/*.jar'
                    sh 'java -jar /var/lib/jenkins/workspace/DimDImPipeline/target/DimDim-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }
}
```
