# 🧠 NeuroMetrics

**NeuroMetrics** is a lightweight, stateless **Micronaut** microservice written in **Groovy**, designed to provide basic health and fitness metric calculations (e.g., BMI, VO₂ Max, heart rate zones) via simple REST endpoints.

It is designed to fit in with the NeuroFit platform, but is application-agnostic due to its stateless nature. It takes in given params and returns calculated metrics.

---

## 🚀 Features

- ⚖️ **BMI Calculation**  
- 💨 **VO₂ Max Estimation**
- 🔬 **Extensible metric API** for health-related calculations  
- 🧪 **Spock-powered unit tests** for clean BDD-style testing  
- 🧱 Built with **Micronaut** (lightweight, fast-starting, DI-powered)

---

## 📦 Tech Stack

| Tool        | Role                             |
|-------------|----------------------------------|
| Groovy      | Main language                    |
| Micronaut   | REST microservice framework      |
| Spock       | Unit testing / specification     |
| Gradle      | Build tool                       |

---

## 🛠️ Getting Started

### Requirements

- Java 17+  
- Gradle 7+  
- Micronaut CLI *(optional but helpful)*

### Clone & Run

```bash
git clone https://github.com/JimiWrd/neurometrics.git
cd neurometrics
./gradlew run
````

The app will start on `http://localhost:8080`

---

## 📡 Example Endpoints

### ➤ BMI Calculator

```
GET /bmi?units=metric&weight=70&height=1.75
```

**Response**

```json
{
  "bmi": 22.86,
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
    └── unit
```

---

## 🧱 Planned Features

* [ ] Heart Rate Zone calculation
* [ ] Basal Metabolic Rate (BMR)
* [ ] Total Daily Energy Expenditure (TDEE)
* [ ] Body Fat Percentage (BFP)
* [ ] MAx Heart Rate (MHR)
* [ ] Waist-to-Height Ratio (WhtR)
* [ ] RESTful error handling
* [ ] OpenAPI (Swagger) integration
* [ ] Docker support
* [ ] CI/CD pipeline

---

## 📖 License

MIT © [Jimi Ward](https://github.com/JimiWrd)

```
