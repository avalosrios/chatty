FROM node:16 as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json /app/package.json
RUN yarn
COPY . /app
# Arguments
ARG REACT_APP_API_URI
ENV REACT_APP_API_URI=${REACT_APP_API_URI}

RUN yarn build

FROM nginx:1.21.3
COPY --from=build /app/build /var/www
# Copy our custom nginx config
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
