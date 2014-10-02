docker run --name wevan-redis-message-server -p 8070:8080 --link wevan-redis:redis -e redis.hostname=redis -e redis.port=6379 -d cspinformatique/redis-message-server
