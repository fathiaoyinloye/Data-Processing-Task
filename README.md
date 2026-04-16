# Data Processing Task API

A Spring Boot REST API that predicts the gender of a name by integrating with the **Genderize.io** API. This service processes raw external data, applies custom confidence logic, and returns a standardized JSON response.

## 🚀 Live Link
**Base URL:** [https://data-processing-task-production.up.railway.app](https://data-processing-task-production.up.railway.app)

---

## 🛠 Tech Stack
* **Java**
* **Spring Boot**
* **REST APIs**
* **Maven**
* **Railway** (Deployment)

---

## ⚙️ How It Works
1. You send a name as a query parameter.
2. The API calls **Genderize.io** with that name.
3. The response is processed and returned to you with extra calculated fields.

### Data Processing Rules
* **sample_size**: Renamed from `count` in the raw Genderize response.
* **is_confident**: `true` only when probability is **0.70 or above** AND sample_size is **100 or above**. If either condition fails, it becomes `false`.
* **processed_at**: The exact UTC timestamp of when your request was processed.

---

## 🛰 API Specification

### Endpoint
GET /api/classify



Example Request
GET https://data-processing-task-production.up.railway.app/api/classify?name=John

### Example Success Response

---

## ✅ Example Success Response
```json
{
  "status": "success",
  "data": {
    "name": "John",
    "gender": "male",
    "probability": 0.99,
    "sample_size": 1234,
    "is_confident": true,
    "processed_at": "2026-04-01T12:00:00Z"
  }
}


Error Responses
All errors follows this format:

{
  "status": "error",
  "message": "description of what went wrong"
}


Example — missing name:
https://data-processing-task-production.up.railway.app/api/classify?
{
  "status": "error",
  "message": "description of what went wrong"
}
CORS
This API includes the Access-Control-Allow-Origin: * header, allowing it to be accessed by external grading scripts and frontend applications.
  }
}

