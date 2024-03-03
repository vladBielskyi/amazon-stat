# amazon-stat README

## Technologies Stack

The project is developed using the following technologies:

- **Java** 17
- **Spring Boot** version 3.2.3
- **Gradle**
- **MongoDB**
- **Spring Cache**

## Documentation

The API documentation is available at: `/swagger-ui/index.html`

## Project Description

The project aims to develop a Spring Boot RESTful API that updates statistics in a MongoDB database.
The application will handle user registration, authentication, and provide various
endpoints for retrieving and updating statistics based on specified dates, ASINs, and overall summaries.

## Requirements

- **Database:** MongoDB
- **Authentication:** Spring Security with JWT
- **Caching:** Spring Cache

## Main Functions

1. **User Registration:** Allows the registration of a new user.
2. **User Authentication:** Enables user authentication using JWT.
3. **Retrieve Statistics by Date:** Provides statistics for a specified date or date range.
4. **Retrieve Statistics by ASIN:** Returns statistics for a specified ASIN or a list of ASINs.
5. **Retrieve Overall Date Summary:** Displays the overall statistics for all dates.
6. **Retrieve Overall ASIN Summary:** Shows the overall statistics for all ASINs.

## Access Control

All functions, except for user registration and authentication, are accessible only to authenticated users.

