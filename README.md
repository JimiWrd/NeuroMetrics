# 🧠 NeuroMetrics

**NeuroMetrics** is a lightweight, stateless **Micronaut** microservice written in **Groovy**, designed to provide basic health and fitness metric calculations (e.g., BMI, VO₂ Max, heart rate zones) via simple REST endpoints.

It is designed to fit in with the NeuroFit platform, but is application-agnostic due to its stateless nature. It takes in given params and returns calculated metrics.

---

## 🚀 Features

- 🔬 **Extensible metric API** for health-related calculations:
  - ⚖️ **BMI Calculation**
  - 💨 **VO₂ Max Estimation**
- 🧪 **Spock-powered unit tests** for clean BDD-style testing  
- 🧱 Built with **Micronaut** (lightweight, fast-starting, DI-powered)

---

## 📦 Tech Stack

| Tool      | Role                         |
|-----------|------------------------------|
| Groovy    | Main language                |
| Micronaut | REST microservice framework  |
| Spock     | Unit testing / specification |
| Gradle    | Build tool                   |
| Docker    | Containerisation             |  


---

## 🛠️ Getting Started

### Requirements

- Java 17+  
- Gradle 7+  
- Micronaut CLI *(optional but helpful)*
- Docker (optional)

### Clone & Run

```bash
git clone https://github.com/JimiWrd/neurometrics.git
cd neurometrics
./gradlew run
````
### (Optional) Docker

```bash
docker compose up --build -d #first time to build app

docker compose up -d #can then run as normal
```

The app will start on `http://localhost:8080`

---

## 📡 Current Endpoints

### ➤ BMI Metric Calculator

```
GET /bmi/metric?units=metric&weight=70&height=1.75
```

**Response**

```json
{
  "bmi": 22.86,
  "category": "Normal"
}
```

### ➤ BMI Imperial Calculator

```
GET /bmi/imperial?&weight=11&height=5.9
```

**Response**

```json
{
  "bmi": 22.4,
  "category": "Normal"
}
```

---

### ➤ VO₂ Max Estimator

```
GET /vo2max?hr=160&age=30
```

**Response**

```json
{
  "vo2max": 42.5,
  "category": "Excellent"
}
```

---

## 🧪 Testing

Run all tests using Spock:

```bash
./gradlew test
```

---

## 📁 Project Structure

```
src/
├── main/groovy/io/github/jimiwrd/neurometrics/
│   ├── controller
│   ├── service        
│   └── model
│
└── test/groovy/io/github/jimiwrd/neurometrics/       
    ├── controller
    ├── service        
    └── model
    
```

---

## 🧱 Planned Features

* [ ] Heart Rate Zone calculation
* [ ] Basal Metabolic Rate (BMR)
* [ ] Total Daily Energy Expenditure (TDEE)
* [ ] Body Fat Percentage (BFP)
* [ ] Max Heart Rate (MHR)
* [ ] Waist-to-Height Ratio (WhtR)
* [x] RESTful error handling
* [ ] OpenAPI (Swagger) integration
* [x] Docker support
* [x] CI/CD pipeline
* [ ] Migrate datasets to DB i.e. VO₂ Max lookup tables

---

## 📖 License

MIT © [Jimi Ward](https://github.com/JimiWrd)
