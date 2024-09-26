all:
	build

build:
	docker build -t my-nginx:1.25.5 .

run:
	docker run -d -p 80:80 --name nginx-1 \
  -v /Users/jijunhyuk/JunyProjects/load_balancer/nginx/html:/home/html \
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
