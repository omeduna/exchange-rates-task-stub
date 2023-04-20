# exchange-rates-task-stub

## Run Postgres in docker
sudo docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v ~/docker/volumes/postgres:/var/lib/postgresql/data postgres
