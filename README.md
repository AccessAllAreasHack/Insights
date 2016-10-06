# Kick Start Project

This is a quick app which will list some mongodb instances.

# Build the project

You need the following environment variables set for the mongo instance/port:
```
export MONGO_PORT_27017_TCP_HOST=127.0.0.1
export MONGO_PORT_27017_TCP_PORT=27017
```

```
./gradlew clean build
```

# Run the project

```
./gradlew clean appRun
```