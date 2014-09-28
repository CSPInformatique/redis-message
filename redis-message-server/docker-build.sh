mvn clean install

unzip target/docker.zip -o -d target

docker build -t cspinformatique/redis-message-server target/docker