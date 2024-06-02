FROM node:latest

WORKDIR /TheNews-frontend
#WORKDIR /TheNews/TheNews-frontend


COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 8081

CMD ["npm", "run", "serve", "--host=0.0.0.0"]