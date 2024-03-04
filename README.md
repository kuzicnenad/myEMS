# myEMS
Allows user to monitor the energy consumption in almost real-time.

Default passwords to access predefined users:<br />
Root -> root (created in SQL, can't be created via app or deleted)<br />
Admin -> admin (full access, can manage all users except root)<br />
User -> user (basic user)<br />

Features:
1. Near-Live and History data monitoring
2. Java spring security role based user Authorisation
3. User management and creation (BCrypted password)
4. Basic operation tests via Postman APIs
5. Manage CRUD operations on Users and Devices

(TBD)
Second version will include:
1. Data Analysis (home page improvement)
2. Report generator
3. Automatic live page update and more realistic consumption numbers
4. Preferences and settings
5. Create server on VM and include transaction tool to send data from SQL to App
6. Full functionality tests
7. Additional authorisation role SUPERVISOR

Technologies & tools
1. Spring Boot 3
2. Spring Security 6
3. BCrypt
4. Maven
5. Tomcat embedded
6. JPA, Hibernate
7. Thymeleaf

Architecture patterns: Monolith MVC Layered.