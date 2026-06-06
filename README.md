# 🏦 Devsu Bank — Full Stack Deployment

Stack completo: **Angular 22** · **Spring Boot** · **SQL Server 2022**

---

## 📁 Estructura del proyecto

```
devsu-bank/
├── docker-compose.yml
├── .env
│
├── devsu-web-bank/
│   ├── Dockerfile
│   ├── nginx.conf
│   ├── package.json
│   ├── bun.lock
│   └── src/
│       └── environments/
│           └── environment.ts
│
├── devsu-api-bank/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
│       └── main/resources/
│           └── application.properties
│
└── sql/
    ├── entrypoint.sh
    └── init.sql
```

## 🚀 Inicio rápido

```bash
# 1. Levantar todos los servicios
docker compose up --build -d
```

---

## 🌐 URLs de acceso

| Servicio       | URL                                          |
|----------------|----------------------------------------------|
| Frontend       | http://localhost                             |
| API REST       | http://localhost/devsu-api-bank/v1/api/      |
| SQL Server     | localhost:1433                               |

> El frontend llama a la API a través del proxy de Nginx — el puerto 8080 no se expone al navegador, eliminando problemas de CORS.
