// Motor 1
int dir1PinA = 3;
int dir2PinA = 2;
int speedPinA = 9;
// Motor 2
int dir1PinB = 4;
int dir2PinB = 5;
int speedPinB = 10; 
byte value;
void setup() { // Setup runs once per reset
    // initialize serial communication @ 9600 baud:
    Serial.begin(9600);
    //Setup
    pinMode(dir1PinA, OUTPUT);
    pinMode(dir2PinA, OUTPUT);
    pinMode(speedPinA, OUTPUT);
    pinMode(dir1PinB, OUTPUT);
    pinMode(dir2PinB, OUTPUT);
    pinMode(6, OUTPUT);
    pinMode(7, OUTPUT);
    pinMode(speedPinB, OUTPUT);
}
void Forward() {
    analogWrite(speedPinB, 255);
    digitalWrite(dir1PinA, HIGH);
    digitalWrite(dir2PinA, LOW);
    digitalWrite(dir2PinB, LOW);
    digitalWrite(dir1PinB, HIGH);
    digitalWrite(6, LOW);
    digitalWrite(7, LOW);
}
void Backward() {
    analogWrite(speedPinB, 255);
    digitalWrite(dir1PinA, LOW);
    digitalWrite(dir2PinA, HIGH);
    digitalWrite(dir2PinB, HIGH);
    digitalWrite(dir1PinB, LOW);
    digitalWrite(6, LOW);
    digitalWrite(7, LOW);
}
void TurnLeft() {
    analogWrite(speedPinB, 255);
    digitalWrite(dir1PinA, LOW);
    digitalWrite(dir2PinA, HIGH);
    digitalWrite(dir2PinB, LOW);
    digitalWrite(dir1PinB, HIGH);
    digitalWrite(6, LOW);
    digitalWrite(7, LOW);
}
void TurnRight() {
    analogWrite(speedPinB, 255);
    digitalWrite(dir1PinA, HIGH);
    digitalWrite(dir2PinA, LOW);
    digitalWrite(dir2PinB, HIGH);
    digitalWrite(dir1PinB, LOW);
    digitalWrite(6, LOW);
    digitalWrite(7, LOW);
}
void LiftUp() {
    analogWrite(speedPinB, 255);
    digitalWrite(6, LOW);
    digitalWrite(7, HIGH);
}
void LiftDown() {
    analogWrite(speedPinB, 255);
    digitalWrite(6, HIGH);
    digitalWrite(7, LOW);
}
void Stop() {
    digitalWrite(dir1PinA, LOW);
    digitalWrite(dir2PinA, LOW);
    digitalWrite(dir2PinB, LOW);
    digitalWrite(dir1PinB, LOW);
    digitalWrite(6, LOW);
    digitalWrite(7, LOW);
}
void loop() {
  if ( Serial.available() > 0 )
  {
      value = Serial.read();
  }

    if (value == 1) {
        Forward();
    }

    if (value == 2) {
        Backward();
    }

    if (value == 3) {
        TurnLeft();
    }

    if (value == 4) {
        TurnRight();
    }

    if (value == 5) {
        LiftUp();
    }

    if (value == 6) {
        LiftDown();
    }

    if (value == 0) {
        Stop();
    }
}
