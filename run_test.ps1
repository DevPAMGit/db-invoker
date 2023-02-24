docker build -t postgres:13.0.0 ./src/main/resources/.
docker run -d --name db-invoker-test-container -p 5432:5432 postgres:13.0.0

mvn test

docker stop db-invoker-test-container
docker rm db-invoker-test-container
docker rmi postgres:13.0.0