node {
   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Get the latest code from Gerrit
   git url: 'http://localhost:8082/p/Demo.git'

   // Get the maven tool.
   def mvnHome = tool 'M3'

   stage 'Build'

   // Update the version in the pom to match the jenkins build number.
   sh "${mvnHome}/bin/mvn versions:set -DnewVersion=${env.BUILD_NUMBER} -DallowSnapshots=true"

   // Run the maven build
   sh "${mvnHome}/bin/mvn clean package"

   stage 'Analyze'
   sh "${mvnHome}/bin/mvn sonar:sonar"

   stage 'Archive'

   // Archive the artifacts in jenkins
   step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar,**/target/*.war', fingerprint: true])

   // Archive the test results.
   step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])

   // TODO tag the code and push into gerrit. The push requires an ssh key
   //sh "git tag -f -a ${env.BUILD_NUMBER} -m 'Jenkins auto tag'"
   //sh "git push -f origin ${env.BUILD_NUMBER}"

   // Deploy the artifacts to Nexus
   sh "${mvnHome}/bin/mvn deploy"


   stage 'Deploy to Development'
   echo 'Simulating deployment to Development Environment'
   sh "echo 'Downloading WAR file from http://localhost:8081/repository/maven-releases/org/example/cisampleapp/${env.BUILD_NUMBER}/cisampleapp-${env.BUILD_NUMBER}.war'"
   echo 'Deploying WAR file to development server(s)'
   sleep 2


   stage 'Deploy to Test'
   timeout(time: 7, unit: 'DAYS') {
     input message: 'Do you want to deploy to Test?', submitter: 'admin'
    }
   echo 'Simulating deployment to Test Environment'
   sh "echo 'Downloading WAR file from http://localhost:8081/repository/maven-releases/org/example/cisampleapp/${env.BUILD_NUMBER}/cisampleapp-${env.BUILD_NUMBER}.war'"
   echo 'Deploying WAR file to Test server(s)'
   sleep 5


   stage 'Deploy to Production'
   timeout(time: 7, unit: 'DAYS') {
     input message: 'Do you want to deploy to Production?', submitter: 'admin'
    }
   echo 'Simulating deployment to Production Environment'
   sh "echo 'Downloading WAR file from http://localhost:8081/repository/maven-releases/org/example/cisampleapp/${env.BUILD_NUMBER}/cisampleapp-${env.BUILD_NUMBER}.war'"
   echo 'Deploying WAR file to production server(s)'
   sleep 5

}
