# DimDim

#Codigo Pipeline

```
pipeline {
    agent any

    tools {
        maven "apache-maven"
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/guhdalla/DimDim.git/'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Deliver') { 
            steps {
                sh '/tmp/dimdimlog/script.sh'
            }
        }
    }
}

```
