pipeline {
    agent any

    parameters {
        choice(
            name: 'TEST_TYPE',
            choices: ['API', 'UI'],
            description: 'Select the test type to run'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    if (params.TEST_TYPE == 'API') {
                        powershell 'mvn clean test -Dtest="PetTest.*,BaseTest.*"'
                    } else if (params.TEST_TYPE == 'UI') {
                        powershell 'mvn clean test -Dtest="SortAssertions.*,AuthorizationPageTest.*,CartPageTest.*,CatalogPageTest.*,CheckoutPageTest.*,BaseTest.*"'
                    }
                }
            }
        }

       stage('Generate Allure Report') {
           steps {
               powershell 'cmd /c "C:\\allure\\bin\\allure.bat generate target\\allure-results -o target\\allure-report --clean"'
           }
       }

        stage('Publish Allure Report') {
            steps {
                allure results: [[path: 'target/allure-results']]
            }
        }
    }
}