# DimDim

#Codigo Pipeline

```
pipeline {
    agent any

    tools {
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
                git 'https://github.com/guhdalla/DimDim.git/'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                    sh 'java -jar /var/lib/jenkins/workspace/DimDimPipeline/target/DimDim-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }
}
```
