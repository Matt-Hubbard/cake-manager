# Cake Manager

## Introduction

This project is a basic Java SpringBoot Application and front end interface that allows users to:

1) View existing cakes
2) Add a new cake

This application uses the following technologies:

- Gradle
- Spring Boot
- H2 (would look for a better persistence solution longer term)
- Lombok
- Cucumber

## Usage

### Start server

To start run the backend server please use the following commands

- Windows: `gradlew bootRun`
- Unix: `./gradlew bootRun`

### Login

Once the server has started, you can now visit `http://localhost:8282` in your browser to go to login page. You now have
choice to either login using Basic Authentication or OAuth 2.0 via GitHub.

#### Basic Authentication

For the scope of this exercise you can enter the following credentials:

```
    username : user
    password : password
```

#### OAuth 2.0

If you select this you will re-directed to login via GitHub. After entering your GitHub credentials you will be required
to accept some authorisations in order to login successfully. Please use the basic authentication if you are not willing
to accept these.

In both cases once logged in you will be redirected the Cake Manage home page.

### Cake Manage home page ###

Once you have finished admiring the basic UI, you have a couple options:

- Logout - Will return you to login page
- Add a new cake - Adds a new cake (which will refresh table)
- View existing cakes - Shows cakes and can be sorted, navigated, searched and manually refreshed (via the refresh
  button)

## Developer notes

### Data

The link provided to get initial data contained duplicate data. Unsure on whether that is expected behaviour or not, I
created an application property `waracle.data.remove.duplicates` (defaults to false) that when enabled will remove
duplicates before inserting the data.

In the interest of progressing I had to remove the unique constraint on title due to the duplicates.

### CI

I added a GitHub Actions simple build pipeline which will build master if there is a push to it.

You see it here: https://github.com/Matt-Hubbard/cake-manager/actions

### Cucumber

There are Cucumber tests that will run as part of the build and subsequently generate a html report located:

```
{project}/build/reports/cucumber/cucumber-html-reports/overview-features.html
```

## Changes

Below are the changes I made during the project (via git commit messages):

- Migrated from maven to gradle

- Added integration test module (defined in gradle/integrationTest.gradle)
- Added html cucumber report generation plugin (link to html report in build log)
- Added basic scenarios for getting all cakes and adding new cakes
- Added empty step definitions to be populated later
- build folder added to .gitignore

- Created build pipeline for CI
- Set up initial Springboot application
- Started implementing basic main classes
- Added request and response to cucumber reports
- Removed existing hibernate and servlet code

- Try to fix build failure in GitHub

- Load initial data from github URL (in DataConfiguration class)
- Temporarily removed unique constraint on title (will need to check if this is expected from consumer) as there are
  duplicates in the data
- Added basic unit tests for Controller and Service (other classes don't contain logic and hence probably don't need
  unit tests)

- index.html allows user to see existing cakes in a data table (via GET "/cakes" endpoint)
- index.html allows user to see add new cake (via POST "/cakes" endpoint)
- Added basic security configuration (defaults with "user" and "password" credentials)
- Added OAuth2 security configuration (via GitHub)
- User is prompted to login at base path (http://localhost:8282) via the above methods
- Added datatable.js that contains javascript logic
- Implement createCake logic in CakeService
- Removed thymeleaf as it isn't being used

- Added some (basic) css to some html elements

- Improved look of index.html page slightly
- Added application property (waracle.data.remove.duplicates) that, when enabled, will remove duplicates from initial
  data retrieved from GitHub link. Defaults to false.

- Added size constraints on CakeDto for title, description and image (based on database constraints)
- Added basic error handling in UI (error is displayed)

- Fleshed out "Add Cakes" scenarios (including validation)
- Fleshed out "Get Cakes" scenarios
- Integrated Basic Authentication into requests for cucumber tests