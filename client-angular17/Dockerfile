# Use the official Node.js image as the base image
FROM node:18

# Set the working directory inside the container
WORKDIR /usr/src/app

# Install Angular CLI globally
RUN npm install -g @angular/cli@17

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the rest of the application code to the working directory
COPY . .

# Expose the port the app runs on
EXPOSE 4200

# Command to run the application in development mode
CMD ["ng", "serve", "--host", "0.0.0.0"]