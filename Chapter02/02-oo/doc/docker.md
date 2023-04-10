# Docker

## Creating an image with Spring Boot
Traditionally we create a docker image from a docker file. This yields the most control on things like the base image.
However, here we are presented with a Spring Boot maven plugin to directly build the image and storing it in our local 
repository, without a Dockerfile as input.

```bash
# Make sure a JDK 17 is used in the current terminal 
$ sdk env

Using java version 17.0.6-tem in this shell.

$ mvn spring-boot:build-image

# see what image was build:  catalog-service:0.0.1-SNAPSHOT
$ docker image ls
REPOSITORY                        TAG              IMAGE ID       CREATED         SIZE
...
paketobuildpacks/builder          base             ae737b9d787f   43 years ago    1.16GB
catalog-service                   0.0.1-SNAPSHOT   95fa030bf990   43 years ago    274MB

# To discard older versions of images
$ docker image prune
``` 

## Running a container instance based on the catalog-service image

```bash
$ docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT
```
- `docker run` Runs a container from an image
- `--rm` Remove the container after its execution completes
- `--name catalog-service` Name of the container
- `-p 8080:8080` Exposes service outside the container through port 8080
- `catalog-service:0.0.1-SNAPSHOT` Name and version of the image to run as container instance.

## Creating and running a docker-compose.yml file from the above command
In the simple example above, where we run one container from one image with simple configuration (basically just port 
forwarding) than a docker command works just fine.
However, if the configuration becomes more complex, with multiple containers from multiple images, where some volumes
and networking needs to be configured as well the `docker run` command will become rather complex.
In such cases, you can store the configuration in a docker-compose.yml file, which can be stored in our version control 
system and will yield repeatable and consistent results.

At [https://www.composerize.com/](https://www.composerize.com/) we can convert a docker run command in a 
`docker-compose.yml` file as a starting point to use docker compose.

We can find the result in [../docker-compose.yml](/Chapter02/02-oo/catalog-service/docker-compose.yml).

## Running with docker compose
```bash
# make sure you are in the directory with the docker-compose.yml
# -d makes sure you are in detached mode and the command returns without logging evrything from the running containers
# use -f to specify a docker compose file when the docker-compose.yml has another name or is in another location
$ docker compose up -d

# To stop the execution of the container(s) run
$ docker compose down
```

## Other useful docker commands
```bash
# To see all running containers
$ docker ps
# To see both running and stopped containers
$ docker ps -a

# a list of volumes
$ docker volume list 
$ docker volume ls

# remove dangling volumes 
$ docker volume prune

# remove specified volumes
$ docker volume rm $(docker volume ls -q --filter dangling=true)

# open an interactive bash session in a running container specified by name (i.e. here it's catalog-service)
$ docker exec -it catalog-service bash
```