# Chatty

Just a simple chat app

## Installing the app

### Requirements
* docker / docker-compose
* java16
* node16

### Running the app

* Generate a buildpack image
```sh
 ./gradlew bootBuildImag
```

* Start docker container. This should also build the client container.
``` sh
 docker-compose up
```

* Load app on [localhost](http://localhost:3000/)

## How does it work?

This app was built with kotlin, spring framework, and [DGS](https://netflix.github.io/dgs/) to provide a GraphQL
api to a ReactJS front end. It makes use of web sockets and GraphQL subscriptions to notify the client of a new chat
message.

### Motivation
The main motivation for doing this app is to push my skills further and challenging myself by going out of my comfort 
zone by setting as a personal goal to have the app as complete as possible in a short period of time (5 days).

### Technology choices

Learning is exciting to me so some technology choices were made as a way to make building this app a bit more 
challenging (fun).

- Spring: Well know web framework for JVM.
- GraphQL: I feel this is the modern and most efficient way for building a modern API. It establishes a clean and easy
way to communicate API contracts with the clients. Extra benefits is that there are a lot of tools that makes it simple
to any type of client to consume these endpoints (Swift/JS/Java).
- Kotlin: Curiosity on learning it with the power of all mighty JAVA.
- React: One of the most widely used UI frameworks, easier to get started by using tools like `create-react-app`.
- Typescript: I'm a huge fan of Typescript, similar to Kotlin, does the same as JS but with static type checking, which
improves the developer experience, while making it harder to introduce unexpected bugs.
- ApolloClient: Robust library for consuming GraphQL APIs. It uses a memory cache to store the state of request, but
it can also be used as local state storage, the main benefit on bigger apps is that objects can be retrieved from the 
cache making only the absolutely necessary http requests to the server.
- H2 DB: For keeping it simple. Given more time I would love to connect it with a postgres DB when running on "prod".
- Grommet: Component library that helps speeding up development, providing accessibility, modularity and responsiveness.

### Known constraints/limitations

- Missing managing user endpoints. While the app assign a unique ID to each user, it does it based on name, there is no
authentication, and there is no way for a user to manage their preferences (changing name, updating profile pic, etc).
- Add pagination, this app won't nicely if there are thousands of messages. The graphql schema defined is ready for 
pagination, but it requires a small update on the backend services/repositories. 
- Use of the GraphQL subscription is not the most efficient as it just refetches all messages. Next small optimization
would require pagination, in order to just fetch the last N. End game optimization is to just upsert and update the 
message cache with the new message.
- This app would not work on a multitenant environment, in order to do that it will require a pub/sub bus (kafka, redis)
that each app node (container) uses for notifying clients about new messages.

### Further improvements
- Pagination. Given more time, adding pagination to the current code would be the easiest win, it will allow for faster
responses, and make a better use of resources.
- Tune the design. Work on making a theme for the app with a personalized color palette.
- Infinite scroll for the chat. Requires pagination :-(
- Add authentication (user password).
- Add user profile.
- Allow multiple (chat) threads a user can join, public/private threads would be nice too.
- Friend list.
- Error messages, have a consistent way for displaying error messages, alerts, etc.
- Setup CI/CD.
