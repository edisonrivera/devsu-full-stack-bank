#!/bin/bash
set -e

/opt/mssql/bin/sqlservr &
PID=$!

echo "Esperando SQL Server..."
for i in {1..50}; do
  /opt/mssql-tools18/bin/sqlcmd \
    -S localhost -U sa -P "$SA_PASSWORD" \
    -Q "SELECT 1" -No 2>/dev/null && echo "SQL Server listo en intento $i" && break
  echo "   intento $i/50..."
  sleep 2
done

DB_EXISTS=$(/opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U sa -P "$SA_PASSWORD" \
  -Q "SET NOCOUNT ON; SELECT COUNT(*) FROM sys.databases WHERE name='devsu_bank'" \
  -h -1 -No 2>/dev/null | tr -d ' \r\n')

if [ "$DB_EXISTS" = "0" ]; then
  echo "🗄Creando base de datos devsu_bank..."
  /opt/mssql-tools18/bin/sqlcmd \
    -S localhost -U sa -P "$SA_PASSWORD" \
    -i /scripts/init.sql -No
  echo "Base de datos inicializada correctamente."
else
  echo "Base de datos devsu_bank ya existe, omitiendo init."
fi

wait $PID