all:
	install

install:
	sudo apt-get update -y
	sudo apt-get install ca-certificates curl -y
	sudo install -m 0755 -d /etc/apt/keyrings
	sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc -y
	sudo chmod a+r /etc/apt/keyrings/docker.asc
	VERSION_STRING=5:26.1.3-1~ubuntu.20.04~focal
	sudo apt-get install docker-ce=$VERSION_STRING docker-ce-cli=$VERSION_STRING containerd.io docker-buildx-plugin docker-compose-plugin
