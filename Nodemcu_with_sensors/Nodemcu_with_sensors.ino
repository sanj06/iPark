
#include <ESP8266WiFi.h>                                                // esp8266 library
#include <FirebaseArduino.h>  
#define SENSORS 2
// defines pins numbers
const int trigPin[SENSORS] = {D1,D5};
const int echoPin[SENSORS] = {D2,D6};
const int greenLED[SENSORS] = {D3,D7};
const int redLED[SENSORS] = {D4,D8};
String sensor[] = {"sensor0","sensor1"};
int sensor_status;
String sensor_number;
int i;
int duration,distance;                                           // firebase library

#define FIREBASE_HOST "nodemcutry-788b4.firebaseio.com"                         // the project name address from firebase id
#define FIREBASE_AUTH "GWTZY7vNJoqpkpglNhJ3NQ8mLkfoY8ULDR7gAEwC"                    // the secret key generated from firebase
#define WIFI_SSID "Sanj"                                          // input your home or public wifi name 
#define WIFI_PASSWORD "12345678" //password of wifi ssid

  
void setup() {

for(i=0;i<SENSORS;i++){
  pinMode(trigPin[i], OUTPUT); // Sets the trigPin as an Output
  pinMode(echoPin[i], INPUT); // Sets the echoPin as an Input
 }

for(i=0;i<SENSORS;i++){
  pinMode(greenLED[i], OUTPUT);
  pinMode(redLED[i], OUTPUT);
 }

  
  // Initialize Serial port
  Serial.begin(115200);
  while (!Serial) continue;
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);                                      //try to connect with wifi
  Serial.print("Connecting to ");
  Serial.print(WIFI_SSID);
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
    }
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(WIFI_SSID);
  Serial.print("IP Address is : ");
  Serial.println(WiFi.localIP());                                                      //print local IP address
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH); 
 // Firebase.setString("sensor1/status", "0");
 for(i=0;i<SENSORS;i++){
    sensor_number = "sensors/" + sensor[i]+"/status" ;
  // Serial.println(sensor_number);
   Firebase.setString(sensor_number, "0");
  }
}
 
void loop() {
  
for(i=0;i<SENSORS;i++){
    // Clears the trigPin
digitalWrite(trigPin[i], LOW);
delayMicroseconds(2);
// Sets the trigPin on HIGH state for 10 micro seconds
digitalWrite(trigPin[i], HIGH);
delayMicroseconds(10);
digitalWrite(trigPin[i], LOW);
// Reads the echoPin, returns the sound wave travel time in microseconds
duration = pulseIn(echoPin[i], HIGH);
// Calculating the distance
distance= duration*0.034/2;
// Prints the distance on the Serial Monitor THIS LINE SHOULD BE COMMENTED FOR IT TO WORK!!!
Serial.print("Distance: ");
Serial.println(distance);
sensor_number = "sensors/" + sensor[i] + "/status" ;
if (distance <= 10 )
{
  digitalWrite(greenLED[i], LOW); 
  
  digitalWrite(redLED[i], HIGH);
  delay(3000);
  sensor_status=1;
  Serial.println(sensor_number);
  Serial.println(sensor_status);
  Firebase.setString(sensor_number, "1");
}
else 
{
  digitalWrite(redLED[i], LOW);
  // delay(3000);
  digitalWrite(greenLED[i], HIGH); 
  delay(3000);
  sensor_status=0;
  Serial.println(sensor_number);
  Serial.println(sensor_status);
  Firebase.setString(sensor_number, "0");
}
 Serial.println(""); 
}
Serial.println("");
 Serial.println("---------------------xxxxx--------------------");
 Serial.println("");
}
