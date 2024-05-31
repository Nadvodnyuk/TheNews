FROM node:latest

WORKDIR /TheNews-frontend

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 8081

CMD ["npm", "run", "serve"]