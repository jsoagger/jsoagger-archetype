pipeline {
   
   agent {label 'slave1'}
   
   environment {
       PROJECT_NAME="jsoagger-web-archetype"
   }
   
   options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '3'))
   		timeout(time: 5, unit: 'MINUTES')
   }
   
   stages {
   		
   	   stage ('Validate Current User') {
			steps {
				script {
                      AUTHOR = sh(script: "git show -s --pretty=%an", returnStdout: true).trim()
                }
			}
		}

        stage('Build') {
        	when {
	            branch 'master'
	            not{ equals expected: "jenkins2", actual: "${AUTHOR}" }
	        }
          
            steps {
            	ansiColor('xterm') {
					sh "mvn -version"
					sh "mvn -Dmaven.test.failure.ignore=true -DskipTests=false -Dmaven.javadoc.skip=true clean install"
				}
            }
        }
        
        
      	stage('Release confirmation') {
      		when {
	            branch 'master'
	            not{ equals expected: "jenkins2", actual: "${AUTHOR}" }
	        }
	        
        	steps {
        		ansiColor('xterm') {
	        		timeout(time: 5, unit: 'MINUTES'){
	        			script {
		                    def perfomRelease = input(
	 							id: 'perfomRelease', message: 'Do you want to release?', ok:'Yes, release this build' 
							)
	                	}	
	        		}
	        	}
        	}
      	}
      
      	stage('Perform release') {
      		when {
	            branch 'master'
	            not{ equals expected: "jenkins2", actual: "${AUTHOR}" }
	        }
	        
         	steps {
         		ansiColor('xterm') {
		         	withCredentials([usernamePassword(credentialsId: 'jenkins_github_credentials', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]){
		         		sh 'git config --global user.email "jenkins@nexitia.com"'
		         		sh 'git config --global user.name "jenkins2"'
		         		
		        		sh 'mvn --settings .maven.xml -DENV_GIT_USERNAME=$GIT_USERNAME -DENV_GIT_PASSWORD=$GIT_PASSWORD -Dresume=false -DdryRun=true -Dmaven.test.failure.ignore=true -DskipTests=true -Darguments=\"-Dmaven.javadoc.skip=true\" release:prepare -B -V -Prelease'
				        sh 'mvn --settings .maven.xml -DENV_GIT_USERNAME=$GIT_USERNAME -DENV_GIT_PASSWORD=$GIT_PASSWORD -Dresume=false -Dmaven.test.failure.ignore=true -DskipTests=true -Darguments=\"-Dmaven.javadoc.skip=true\" -B -V release:prepare release:perform -Prelease'
		         	}
		        }
         	}
         	
         	post {  
				 success {
					 emailext   to: "${env.DEV_MAILING_LIST}",
					 			subject: "$PROJECT_NAME, released",
					 			body: "$PROJECT_NAME released.<br/> The project $PROJECT_NAME have been released.<br/><br/>Jenkins", 
								from: "${env.JOB_EMAIL_SENDER}", 
								attachLog: false;
				 }  
				 failure {
					emailext    to: "${env.DEV_MAILING_LIST}",
								subject: "$PROJECT_NAME, RELEASE Failed",
								body: "$PROJECT_NAME, RELEASE failed. <br/> Build logs are avalaible here: $BUILD_URL.<br/><br/>Jenkins", 
								from: "${env.JOB_EMAIL_SENDER}", 
								attachLog: true;
				}  
			}
      	}
    }
    
    post {  
     	failure {
        	emailext	to: "${env.DEV_MAILING_LIST}",    
        				subject: "$PROJECT_NAME - Build Failed",
        				body: "$PROJECT_NAME, build failed. <br/> You can check jenkins console output at $BUILD_URL to view full the results.<br/><br/>Jenkins", 
                    	from: "${env.JOB_EMAIL_SENDER}", 
                    	attachLog: true;
     	}  
	}
}
