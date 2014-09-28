REDIS_VOLUME_PATH=/docker-volumes/wevan-redis

if [ ! -f $REDIS_DATA_PATH]; then
	echo "Creating $REDIS_VOLUME_PATH"
    mkdir -p $REDIS_VOLUME_PATH
fi

CMD=docker run --name wevan-redis -v $REDIS_VOLUME_PATH:/data -d redis redis-server

echo "Executing docker command :"
echo "	:	$CMD"

$CMD