# The Foodie Express

> A Food Delivery App

---

## Clone

    git clone https://github.com/fspirovski/the-foodie-express.git

    cd the-foodie-express

---

## Requirements

> To build the project these are the required versions

- Java 17
- Maven 3.8.1
- node 18.1.0
- yarn 1.22.18

---

## Build

> This would generate all the types for the frontend
> as well as the node_modules

- Command line

      mvn clean install

- Intelij
  - Just run the one maven configuration
  - In case there is none, create a new maven config and add to the run options _**`clean`**_ and _**`install`**_ the directory is root of the git project

---

## Run

---

### Database **(optional)**

> You can install postgresql locally and set it up with the provided option:

- User name: **postgres**
- Password: **postgresql**
- Database name: **the-foodie-express**
- port: **5432**

There is also the option to start it as a docker container from the file located at

> ./the-foodie-express-api/src/docker/

run in the same directory

    docker-compose up

or

    sudo docker-compose up

Alternatively there is also the option of starting it graphically
from within intelij.

---

### Back end

> Backend runs on port 8080

- Command line

        mvn spring-boot:run

- Intelij
  > There should be a configuration ready, if not it is simple enough to create

---

### Front end

> The frontend runs on port 3000

If Node modules are missing run

    yarn install

To start the project run

    yarn run dev

or

    yarn dev

---
