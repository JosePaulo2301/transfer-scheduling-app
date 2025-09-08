## Transfer Scheduling App

Aplicação full‑stack para agendar transferências financeiras com cálculo automático de taxas.

**Visão Geral**
- Backend: Spring Boot (Java 11), H2 em memória.
- Frontend: Vue 3 + Vite, Axios.
- Módulos: `backend/` e `frontend/transfer-app/`.

**Requisitos**
- Java 11+ e Maven (ou `mvnw/mvnw.cmd`).
- Node.js 18+ e npm.

**Como Rodar (Desenvolvimento)**
- Backend: `cd backend && ./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`) → http://localhost:8080
- Frontend: `cd frontend/transfer-app && npm install && npm run dev` → http://localhost:5173

**Build (Único)**
- Tudo em um comando: `cd backend && ./mvnw clean package`
  - O Maven instala Node/npm localmente, roda `npm ci` e `npm run build` no `frontend/transfer-app` e copia o `dist/` para `target/classes/static/`.
  - Execute o JAR em `backend/target/` para servir a API e os assets do frontend.

**API (principais)**
- POST `/api/v1/scheduler` – agenda uma transferência.
- GET `/api/v1/extract` – lista transferências agendadas.

CORS liberado para `http://localhost:5173`. H2 Console disponível em `/h2-console`.
