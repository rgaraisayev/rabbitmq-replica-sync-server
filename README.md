# rabbitmq-replica-sync-server


This project developed to replicate client databases' data to master. 


Used: rabbitmq, docker, jenkins, postgres, spring boot, jdbc


# How it works

1. Client app collects syncable data from tables periodically and publishes to message queue (rabbitmq) [rabbitmq-replica-sync-client](https://github.com/rgaraisayev/rabbitmq-replica-sync-client)
2. Server app listens to queue, gets messages and inserts, updates, and deletes based on coming message type
