FROM node:24.15.0-alpine AS builder

WORKDIR /app

COPY package.json bun.lock ./

RUN npm install -g bun
RUN bun install --frozen-lockfile

COPY . .

RUN bunx ng build --configuration=production --output-path=dist/devsu-web-bank

FROM nginx:1.27-alpine AS production

RUN rm -rf /usr/share/nginx/html/*

COPY --from=builder /app/dist/devsu-web-bank/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget -qO- http://localhost/ || exit 1

CMD ["nginx", "-g", "daemon off;"]
