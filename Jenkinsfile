pipeline {
    agent {
        label "master"
    }
    
    environment {
	    MS_NAME = env.GIT_URL.replaceFirst(/^.*\/([^\/]+?).git$/, '$1')
        DEPLOY_ENV = "${env.GIT_BRANCH}"
        CONTAINER_NAME = "${GIT_BRANCH}-$MS_NAME"
        PORT_PROD = 2042
    }

    stages {
        stage ("Jenkins Job"){
            steps {
                script {
                    currentBuild.displayName = "${GIT_BRANCH}-${BUILD_NUMBER}"
                }
            }
        }

        stage ('Clean & Build') {
            steps {
	            sh "rm -rf build"
	            sh "chmod +x gradlew"
                sh "./gradlew bootJar -Pprofile=${DEPLOY_ENV} -PversionCode=${BUILD_NUMBER} --no-daemon"
            }
        }

        stage("Master"){
            when { expression { env.DEPLOY_ENV == "master"} }
            steps{
                sh 'docker rm -f $CONTAINER_NAME || exit 0'
                sh 'docker build --rm --build-arg DEPLOY_ENV=$DEPLOY_ENV -t $CONTAINER_NAME .'
                sh 'docker run -d -t --network host -p $PORT_PROD:$PORT_PROD -v /opt/logs/replica-sync-server/logs:/var/logs --name=$CONTAINER_NAME $CONTAINER_NAME'
            }
        }
    }
}