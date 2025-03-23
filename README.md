# Markdown Note-taking App

## Overview
The **Markdown Note-taking App** is a RESTful API built with **Spring Boot** and **PostgreSQL** that allows users to upload, save, and retrieve markdown notes. Additionally, it provides grammar-checking functionality and returns a rendered HTML version of the notes.

## Features
- **Upload Markdown Files**: Users can upload markdown notes to the system.
- **Grammar Checking**: An API endpoint to check the grammar of the notes.
- **Save Notes**: Save markdown notes in the database.
- **List Saved Notes**: Retrieve a list of all saved notes.
- **Render Markdown to HTML**: Convert markdown content to HTML and retrieve the rendered output.

## Technologies Used
- **Spring Boot** (Backend Framework)
- **PostgreSQL** (Database)
- **Spring Data JPA** (ORM for database interactions)
- **Spring Web** (RESTful API Development)
- **Markdown Processing Library** (e.g., flexmark-java)
- **Grammar Checking API** (e.g., LanguageTool API)

## Installation & Setup
### Prerequisites
- Java 17+
- PostgreSQL
- Maven

### Steps to Run
1. **Clone the Repository**
   ```sh
   git clone https://github.com/AzamatAbraev/MarkdowNotetakingAPI.git
   cd MarkdowNotetakingAPI
   ```

2. **Configure Database**
    - Update `application.properties` in `src/main/resources/` with your PostgreSQL credentials:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/markdown_db
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      spring.jpa.hibernate.ddl-auto=update
      ```

3. **Build and Run the Application**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the API**
   The API will be running at `http://localhost:8080`

## API Endpoints
| Method   | Endpoint                  | Description                      |
|----------|---------------------------|----------------------------------|
| `POST`   | `/api/v1/files/upload`    | Upload a markdown file           |
| `GET`    | `/api/v1/files/{id}`      | Retrieve a saved file            |
| `DELETE` | `/api/v1/files/{id}`      | Delete a file                    | 
| `POST`   | `/api/v1/notes`           | Save a markdown note             |
| `GET`    | `/api/v1/notes`           | List all saved notes             |
| `GET`    | `/api/v1/notes/{id}`      | Retrieve a saved note            |
| `GET`    | `/api/v1/notes/{id}/html` | Get the HTML version of the note |

## Example Usage
### Upload a Markdown File
```sh
curl -X POST -F "file=@note.md" http://localhost:8080/api/v1/notes or http://localhost:8080/api/v1/files/upload 
```

### Retrieve HTML Rendered Note
```sh
curl -X GET http://localhost:8080/api/notes/1/html
```

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.

## Contact
For any questions, feel free to reach out via [azamatabraev07@gmail.com](mailto:your-email@example.com).

