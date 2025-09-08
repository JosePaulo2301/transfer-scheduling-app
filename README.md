## Transfer Scheduling App

Concise guide to build and run the application locally. The packaged JAR serves both the REST API and the built SPA.

**Stack**
- Backend: Spring Boot 2.7 (Java 11), Spring Web, JPA, H2 (in‑memory)
- Frontend: Vue 3 + Vite, Axios (built and copied into backend `/static` by Maven)

**Requirements**
- JDK 11+ installed. Maven Wrapper (`mvnw`/`mvnw.cmd`) is included.
- Node is NOT required to run the final JAR.

**Single Build**
- Linux/macOS: `cd backend && ./mvnw clean package -DskipTests`
- Windows PowerShell: `cd backend; .\mvnw.cmd clean package -DskipTests`
- What it does: runs `npm install` and `vite build` in `frontend/transfer-app`, then copies `dist/` to `target/classes/static/` and packages the JAR.

**Run**
- From `backend/`: `java -jar target/app-0.0.1-SNAPSHOT.jar`
- Access: `http://localhost:8080` (SPA served at `/`)

**Main API**
- POST `/api/v1/scheduler` — create a scheduled transfer
- GET `/api/v1/extract` — list scheduled transfers


## Transfer Scheduling App


**INTERFACE**

![alt text](assets/image-1.png)


**FILLING**

![alt text](assets/image-1.png)

**MAKING APPOINTMENT**

![alt text](assets/image-2.png)

**RESCUING APPOINTMENT via LIST DECLARATION**


![alt text](assets/image-4.png)

**SIMILATING ERROR WITH A DATE OUTSIDE THE RULE**

![alt text](assets/image-6.png)


