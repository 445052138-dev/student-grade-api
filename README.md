# Student Grade API - SE 4111 CI/CD Mini Lab

## Prerequisites

- Java 17
- Maven 3.9 or later
- Docker for image builds and local container runs
- GitHub, SonarQube Cloud, and Docker Hub accounts for the publishing workflow

## Configure SonarQube Cloud

Replace `YOUR-SONAR-ORG` and `YOUR-SONAR-PROJECT-KEY` in `pom.xml` with the values for your SonarQube Cloud project. Add `SONAR_TOKEN` as a repository Actions secret.

## Configure Docker Hub

Add `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN` as repository Actions secrets. The workflow publishes the `latest` and commit SHA tags on pushes to `main`.

## Run locally

```bash
mvn spring-boot:run
```

The API is available at `http://localhost:8080/api`. Endpoints include:

- `GET /students`
- `GET /students/{id}`
- `POST /students`
- `GET /health`

## Build and test

```bash
mvn clean verify
```

## Build and run with Docker

```bash
docker build -t student-grade-api:local .
docker run --rm -p 8080:8080 student-grade-api:local
```

Check the health endpoint at `http://localhost:8080/api/health`.

## GitHub Actions

Push to `main` or open a pull request targeting `main`. The workflow runs the Maven build and tests for both event types. Pushes also run the SonarQube Cloud scan and publish Docker images, so set all three repository secrets before enabling publishing.
