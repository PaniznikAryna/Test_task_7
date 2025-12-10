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
                        withCredentials([
                            string(credentialsId: 'VALID_LOGIN', variable: 'VALID_LOGIN'),
                            string(credentialsId: 'INVALID_LOGIN', variable: 'INVALID_LOGIN'),
                            string(credentialsId: 'PASSWORD', variable: 'PASSWORD'),
                            string(credentialsId: 'BASE_URL', variable: 'BASE_URL'),
                            string(credentialsId: 'FIRST_NAME', variable: 'FIRST_NAME'),
                            string(credentialsId: 'LAST_NAME', variable: 'LAST_NAME'),
                            string(credentialsId: 'POSTAL_CODE', variable: 'POSTAL_CODE')
                        ]) {
                            powershell '''
$envContent = @"
VALID_LOGIN=$env:VALID_LOGIN
INVALID_LOGIN=$env:INVALID_LOGIN
PASSWORD=$env:PASSWORD
BASE_URL=$env:BASE_URL
FIRST_NAME=$env:FIRST_NAME
LAST_NAME=$env:LAST_NAME
POSTAL_CODE=$env:POSTAL_CODE
"@
$envContent | Out-File -Encoding UTF8 .env

mvn clean test -Dtest="SortAssertions.*,AuthorizationPageTest.*,CartPageTest.*,CatalogPageTest.*,CheckoutPageTest.*,BaseTest.*"
'''
                        }
                    }
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                powershell 'cmd /c "C:\\Users\\arish\\scoop\\shims\\allure.cmd generate target\\allure-results -o target\\allure-report --clean"'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure results: [[path: 'target/allure-results']]
            }
        }
    }
}