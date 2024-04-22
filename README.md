# Notification Service
  #### This service can works as independent component for sending message for channels like Email,Slack etc.
  #### It is async in nature and retries can be achievable as it logs the request in mongo db 

# Supported Channels 
   1. Email
   2. Slack
   3. Discord

## Architecture 

![image](https://github.com/nageshtejwani/notification-service/assets/39427545/84cad9a1-0ce2-44f1-81dc-12b939ba14ee)

## Build
##### Please set uppropriate configuration for channels ,Webhooks for Slack,Discord etc and configuration for email
docker compose build

## RUN 

docker-compose up 



