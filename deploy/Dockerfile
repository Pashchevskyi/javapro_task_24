RUN echo export PGPASSWORD='pgpasswd'; docker exec -it deploy_postgres_service_1 \
    psql -h localhost -U postgres -f /docker-entrypoint-initdb.d/init.sql