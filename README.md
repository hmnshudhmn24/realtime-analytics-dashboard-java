# Real-Time Analytics Dashboard

## Overview
This project streams real-time data using Apache Kafka, processes it using Apache Spark, and visualizes it using a Spring Boot WebSocket server and a frontend with Chart.js.

## Technologies Used
- **Apache Kafka** - Real-time data streaming
- **Apache Spark Streaming** - Data processing
- **Spring Boot WebSocket** - Backend communication
- **Chart.js** - Frontend visualization

## How to Run
1. **Start Kafka Broker & Zookeeper**
2. **Run Kafka Producer** (`KafkaDataProducer.java`)
3. **Start Spark Streaming Consumer** (`SparkKafkaConsumer.java`)
4. **Run Spring Boot Server**
5. **Open `index.html` in a browser**

## Future Enhancements
- Deploy on AWS with Kinesis.
- Store processed data in MongoDB.
- Implement AI-based analytics.

