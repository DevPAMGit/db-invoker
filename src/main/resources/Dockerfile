FROM postgres:13

ENV POSTGRES_DB dummy
ENV POSTGRES_USER dummy
ENV POSTGRES_PASSWORD dummy

COPY db-invoker-test.sql /docker-entrypoint-initdb.d/
