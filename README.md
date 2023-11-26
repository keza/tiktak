**1.** sorunun cozumu https://github.com/keza/tiktak/blob/main/src/main/java/com/tiktak/backend/util/Tiktak.java

**2.** soru icin servis cagrilari

GET http://localhost:8080/api/inspections/47


POST http://localhost:8080/api/inspections
Content-Type: application/json

{
  "carId": "45",
  "answers": [
    {
      "questionId": 1,
      "answer": true,
      "comment": "arizabbbcc",
      "photoUrls": ["https://www.google.com.tr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png", "https://www.google.com.tr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png", "https://www.google.com.tr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"]
    },
    {
      "questionId": 2,
      "answer": true,
      "comment": "sıkıntıbbbcc",
      "photoUrls": ["https://www.google.com.tr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png", "https://www.google.com.tr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png", "https://www.google.com.tr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"]
    },
    {
      "questionId": 3,
      "answer": false,
      "comment": null,
      "photoUrls": []
    }
  ]
}
