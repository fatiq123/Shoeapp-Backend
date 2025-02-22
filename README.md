<h1>Shoeapp-Backend</h1>

<p>This repository contains the backend code for the Shoeapp application. It is built using Java and follows a layered architecture.</p>


<h2>Project Dependencies</h2>

<p>The project uses the following dependencies:</p>

<ul>
  <li><strong>Spring Boot Starter Data JPA:</strong> Provides integration with Spring Data JPA for database operations.</li>
  <li><strong>Spring Boot Starter Security:</strong> Adds security features to the application.</li>
  <li><strong>Spring Boot Starter Web:</strong> Used to build web applications, including RESTful services.</li>
  <li><strong>Spring Boot Starter Validation:</strong> Provides validation support.</li>
  <li><strong>PostgreSQL:</strong> PostgreSQL JDBC driver for connecting to a PostgreSQL database.</li>
  <li><strong>Lombok:</strong> Reduces boilerplate code in the Java applications by using annotations.</li>
  <li><strong>Spring Boot Starter Test:</strong> Provides testing support, including JUnit, Hamcrest, and Mockito.</li>
  <li><strong>Spring Security Test:</strong> Adds support for testing Spring Security.</li>
  <li><strong>JJWT (Java JWT):</strong> Used for creating and verifying JSON Web Tokens (JWTs).</li>
</ul>



<h2>Getting Started</h2>

<h3>Prerequisites</h3>
<ul>
  <li>JDK 11 or higher</li>
  <li>Maven</li>
  <li>A database (e.g., MySQL, PostgreSQL)</li>
</ul>

<h3>Installation</h3>
<ol>
  <li>Clone the repository:
    <pre><code>git clone https://github.com/fatiq123/Shoeapp-Backend.git
cd Shoeapp-Backend</code></pre>
  </li>
  <li>Configure the database in <code>src/main/resources/application.properties</code>:
    <pre><code>spring.datasource.url=jdbc:mysql://localhost:3306/shoeapp
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
      
jwt.secret=your_secret_here
jwt.expiration=expiration_here</code></pre>
  </li>
  <li>Build the project:
    <pre><code>mvn clean install</code></pre>
  </li>
  <li>Run the application:
    <pre><code>mvn spring-boot:run</code></pre>
  </li>
</ol>

<h3>Usage</h3>
<p>The application exposes various REST endpoints for managing shoe-related operations. You can use tools like Postman or cURL to interact with these endpoints.</p>

<h2>Contributing</h2>
<p>Contributions are welcome! Please fork the repository and create a pull request with your changes.</p>

<h2>License</h2>
<p>This project is licensed under the MIT License.</p>
