Build service1 and service2 docker images (from folders containing Dockerfile):
===============================================================================

docker build -t sjd300671/service1 .

docker build -t sjd300671/service2 .

Create swarm and overlay network:
===================================

docker swarm leave —force

docker swarm init

docker network create --driver overlay overlaynetwork

Deploy services:
=================

docker service ls

docker service create --replicas 2 --network overlaynetwork --name my_service1 --publish 10000:10000 sjd300671/service1

docker service create --replicas 2 --network overlaynetwork --name my_service2 --publish 10001:10001 sjd300671/service2

docker service ls

From outside the swarm:
========================= 

curl http://localhost:10000/api/hostname
curl http://localhost:10001/api/hostname

Access services inside a container (using DNS):
==================================================

docker ps

CONTAINER ID        IMAGE                       COMMAND                  CREATED             STATUS              PORTS               NAMES
9b4d018a1c53        sjd300671/service2:latest   "sh -c 'java $JAVA..."   3 minutes ago       Up 3 minutes        10001/tcp           my_service2.2.mj64tdwpfftaow8s0ju3zas71
b576929283cc        sjd300671/service2:latest   "sh -c 'java $JAVA..."   3 minutes ago       Up 3 minutes        10001/tcp           my_service2.1.5goalzalldr9l4f9568goyayi
2eb5a95397a0        sjd300671/service1:latest   "sh -c 'java $JAVA..."   4 minutes ago       Up 4 minutes        10000/tcp           my_service1.2.pohfdffmlombsdi46mtmmrit4
255251f918d1        sjd300671/service1:latest   "sh -c 'java $JAVA..."   4 minutes ago       Up 4 minutes        10000/tcp           my_service1.1.ubrixdr1j6bgveg35mqbok6h8

docker exec -it 2eb5a95397a0 bash

curl http://my_service1:10000/api/hostname
curl http://my_service2:10001/api/hostname

Remove services from outside swarm:
===================================

docker service rm my_service1

docker service rm my_service2
