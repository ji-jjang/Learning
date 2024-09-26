all:
	build

build:
	docker build -t my-nginx:1.25.5 .

run:
	docker run -d -p 80:80 -p 443:443 --name nginx-1 \
       	-v /etc/letsencrypt:/etc/letsencrypt \
	--network juny \
	my-nginx:1.25.5

restart:
	make rm
	make rmi
	make build
	make run

rm:
	docker rm -f nginx-1

rmi:
	docker rmi my-nginx:1.25.5

.phony: all build run rm rmi
