# Assignment 4 Enterprise App Development
 
# Book Lending Information System Assignment

## Due Date
- **Dec 9 at 11:59 pm**

## Purpose
By completing this assignment, you will practice:
- Developing, coding, and testing micro-services for given requirements.
- Implementing your frontend using SpringBoot MVC application to consume REST APIs, or optionally using a React application.

## Instructions and Rules
- This assignment can be completed individually or in a group (up to 3 students) with equal contribution.
- Submit the completed project and a detailed recorded video demonstration by the due date.
- Name your Eclipse project as follows: `YourName_StudentNo_Assignment#` (e.g., `John_301125XYZ_Assignment2`).

## Description
- Implement a Book Lending Information System using Spring Boot micro-services.
- The application should allow librarians to manage books, including reviewing the list of books, checking out books to customers, and looking up book availability.
- You may need to create projects like Eureka server, micro-services, micro-services client, and front-end app (MVC webapp or React app).
- Define entity classes for Customer, Book, and Transaction with persistence using JPA. Use MySQL to create a database named `LendingDB` with three tables: Customer, Book, and Transaction.

### Data Types
1. **Customer**: FCustomerId, FirstName, LastName, Address, Phone, EmailId
2. **Book**: BookId, Title, AuthorLastName, AuthorFirstName, Rating (1 to 5)
3. **Transaction**: TransactionId, CustomerId, BookId, TrxnDate, TrxnType (Check-in vs Check-out indicator)

### Innovation Requirement
- Use Spring Webflux with MongoDB.
- Explore and implement additional Spring Framework features.
- Example: Replace RestTemplate with WebClient for HTTP calls.
- Implement other relevant innovations.

## Video Demonstration Guidelines
- Include yourself speaking in the video.
- Show a running demo of the project, including input/output pages and error scenarios.
- Highlight UI design elements.
- Provide a detailed explanation demonstrating your understanding of the concepts.
- Walkthrough all the code and relevant files.

## Assessment Rubrics

| Criteria | Points (Marks) |
|----------|----------------|
| Micro-services implementation, service Registry usage, entities, repositories, controllers setup, frontend implementation, application properties, and POM.xml dependencies. | 50 points |
| JPARepository or ReactiveRepository implementation, SQL CRUD operations, Server-side validations in entity classes. | 15 points |
| Adherence to coding standards, UI friendliness, use of CSS/bootstrap. | 5 points |
| Fulfill innovation requirement. | 10 points |
| Submission of recorded video as per guidelines. | 20 points |
| **Total** | **100 points** |

