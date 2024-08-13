# Authentication System

## Overview

The **Authentication System** is a simple web application designed to demonstrate user authentication functionalities using Java Servlets, Hibernate ORM, and MySQL database. The project is built using Ant in the NetBeans IDE and is deployed on a Tomcat server.

## Project Structure

The project is organized as follows:

- **src/**: Contains the source code for the application.
  - **main/**:
    - **java/**: Contains Java source files for servlets, models, and controllers.
    - **resources/**: Includes configuration files such as `hibernate.cfg.xml`.
  - **webapp/**:
    - **WEB-INF/**: Contains web application configuration files.
      - **web.xml**: Deployment descriptor for the web application.
    - **jsp/**: Contains JSP files for the user interface.
    - **static/**: Contains static resources such as CSS and JavaScript files.

- **build.xml**: Apache Ant build script for compiling and packaging the application.

- **lib/**: Contains external libraries and dependencies required for the project.

- **README.md**: This file, providing an overview and instructions for the project.

## Technology Stack

- **Java Servlets**: Used for handling HTTP requests and responses.
- **Hibernate**: ORM framework used for interacting with the MySQL database.
- **MySQL**: Database for storing user data and authentication details.
- **Apache Tomcat**: Java Servlet container used for running the web application.
- **Ant**: Build tool used for compiling and packaging the application.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/AdityaByte/JavaProjects.git
   cd JavaProjects/AuthenicationSystem
