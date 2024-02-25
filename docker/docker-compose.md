postgres connection:
```yml
version: '3.8'

services:
psql:
	image: postgres:latest
	environment:
	POSTGRES_DB: ${DB_DATABASE}
	POSTGRES_USER: ${DB_USERNAME}
	POSTGRES_PASSWORD: ${DB_PASSWORD}
	ports:
	- "${DB_PORT}:5432"
	volumes:
	- psql_volume:/var/lib/postgresql/data

volumes:
psql_volume:
```

sql server
```yml
version: '3.8'

services:
  sql_server:
    image: mcr.microsoft.com/mssql/server:latest
    environment:
      SA_PASSWORD: ${SQL_SERVER_SA_PASSWORD}
      ACCEPT_EULA: Y
    ports:
      - "${SQL_SERVER_PORT}:1433"
    volumes:
      - sql_server_volume:/var/opt/mssql

volumes:
  sql_server_volume:
```