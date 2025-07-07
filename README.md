# Similar Products API

REST API to retrieve products similar to a given product, built with Spring Boot.

The API requirements and design can be found [here](./proposal/readme.md).

## Main Features
- Endpoint to query similar products for a given product.
- Centralized exception handling.
- Interactive documentation with Swagger-UI.
- Unit and integration tests with JUnit, Mockito, and MockMvc.

## Requirements
- Java 17+
- Maven 3.8+

## Getting Started
Before running the application, make sure to execute `mvn clean install` to build the project. 
This will download all dependencies and generate any necessary code automatically.
## API Usage

### Get similar products

```http
GET /product/{productId}/similar
```

Example:
```bash
curl 'http://localhost:8080/product/1/similar'
```

Response:
```json
[
  {
    "id": "2",
    "name": "Dress",
    "price": 19.99,
    "availability": true
  },
  ...
]
```

## Swagger Documentation

Access the interactive documentation at (once the application is running in local environment):

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoint performance

elow are the results of two consecutive executions of the performance test using k6.
Refer to [this section](./proposal/readme.md#testing-and-self-evaluation) for instructions on how to run the test.


![Performance Result](./assets/performance-result.png "Performance Result")

```
    data_received..............: 3.3 MB 36 kB/s
    data_sent..................: 1.2 MB 13 kB/s
    http_req_blocked...........: avg=4.58ms   min=6µs      med=616µs    max=506.08ms p(90)=1.69ms   p(95)=4.58ms  
    http_req_connecting........: avg=1.34ms   min=0s       med=0s       max=249.69ms p(90)=0s       p(95)=0s      
    http_req_duration..........: avg=147.27ms min=399µs    med=11.36ms  max=6.02s    p(90)=142.04ms p(95)=428.55ms
    http_req_receiving.........: avg=2.23ms   min=22µs     med=1.34ms   max=267.02ms p(90)=3.51ms   p(95)=6.38ms  
    http_req_sending...........: avg=6.32ms   min=11µs     med=684µs    max=371.93ms p(90)=14.48ms  p(95)=36.89ms 
    http_req_tls_handshaking...: avg=0s       min=0s       med=0s       max=0s       p(90)=0s       p(95)=0s      
    http_req_waiting...........: avg=138.71ms min=350µs    med=6.5ms    max=6.02s    p(90)=114.35ms p(95)=402.61ms
    http_reqs..................: 11881  131.162947/s
    iteration_duration.........: avg=678.81ms min=500.62ms med=521.95ms max=6.52s    p(90)=724.82ms p(95)=1.08s   
    iterations.................: 11334  125.124219/s
    vus........................: 200    min=0   max=200
    vus_max....................: 200    min=200 max=200


    data_received..............: 4.8 MB 79 kB/s
    data_sent..................: 1.6 MB 27 kB/s
    http_req_blocked...........: avg=4.3ms    min=6µs      med=655µs    max=1s       p(90)=1.95ms   p(95)=5.23ms  
    http_req_connecting........: avg=339.07µs min=0s       med=0s       max=73.12ms  p(90)=0s       p(95)=0s      
    http_req_duration..........: avg=63.64ms  min=370µs    med=20.97ms  max=1.15s    p(90)=174.96ms p(95)=267.15ms
    http_req_receiving.........: avg=3.52ms   min=25µs     med=1.51ms   max=457.82ms p(90)=4.47ms   p(95)=9.78ms  
    http_req_sending...........: avg=9.21ms   min=10µs     med=777µs    max=806.83ms p(90)=22.33ms  p(95)=56.03ms 
    http_req_tls_handshaking...: avg=0s       min=0s       med=0s       max=0s       p(90)=0s       p(95)=0s      
    http_req_waiting...........: avg=50.9ms   min=318µs    med=13.64ms  max=1s       p(90)=134.34ms p(95)=214.39ms
    http_reqs..................: 16425  271.186447/s
    iteration_duration.........: avg=599.45ms min=500.64ms med=533.93ms max=2.21s    p(90)=775.2ms  p(95)=886.56ms
    iterations.................: 15905  262.60094/s
    vus........................: 200    min=0   max=200
    vus_max....................: 200    min=200 max=200

```
