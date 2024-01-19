# Use Node Image based on Alpine Linux (due to its small size)
FROM node:20.11.0-alpine3.19

# Set the working directory in the container
WORKDIR /app

# Copy the package.json and package-lock.json files from the host to the container
COPY package*.json .

# Install the dependencies
RUN npm install

# Copy the rest of the files from the host to the container
COPY . .

# Expose the port that the ReactJS application will run on
EXPOSE 5173

# Command to run the ReactJS application
CMD [ "npm", "run", "global" ]
