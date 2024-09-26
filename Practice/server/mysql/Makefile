all:
	build

build:
	docker build -t my-mysql:8.4 .

run:
	docker run -d -p 3306:3306 --name mysql-1 \
	-v /home/ubuntu/mysql/mycnf-dev/my.cnf:/etc/mysql/my.cnf \
	-v /home/ubuntu/mysql/dev:/var/lib/mysql \
       	--env-file ./.env \
	my-mysql:8.4

restart:
	make rm
	make rmi
	make build
	make run

rm:
	docker rm -f mysql-1

rmi:
	docker rmi my-mysql:8.4

.phony: all build run restart rm rmi
