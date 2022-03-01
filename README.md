## Microservices with Spring boot

### This repo focuses more on creating a working microservices with event queues

***Note that I'm following a course on how to create a microservices with Spring, I'm also adding some more service of
my own on top of the course***

**Whatever im building is not finalized yet btw**

**List of Service created**

- User Service
- Fraud-check service
- Eureka service discovery service
- Notification service (Sends notification/emails to confirm~~)

**Processes**

- user will want to register themselves to the system
- the fraud service will check whether they are fraudulent
- There will be Fraud Record service that will store information of individuals who have been deemed fraud
- ID will be internal, UUID will be exposed to client (Query happened only with ID)

**TODO**

- [ ] Add authentication for users and admin with proper roles
    -  [ ] Add an authentication service
- [ ] Proper UUID and Internal id for each entity in the database (UUID being public, and ID is internal)
- [ ] Proper logic for checking fraud


