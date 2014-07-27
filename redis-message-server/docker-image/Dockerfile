FROM tifayuki/java:java7

MAINTAINER Daniel Lavoie <dlavoie@cspinformatiqe.com>

ADD redis-message-server.jar /redis-message-server/redis-message-server.jar
ADD run.sh /run.sh

RUN chmod +x /*.sh

EXPOSE 8080

CMD ["/run.sh"]
