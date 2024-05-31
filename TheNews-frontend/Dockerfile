FROM node:latest

WORKDIR /TheNews/TheNews-frontend

COPY . .

RUN npm install

RUN npm run build

CMD ["npm", "run", "serve"]