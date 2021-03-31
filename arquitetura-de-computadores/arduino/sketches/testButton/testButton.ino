int verde = 2;
int verme = 3;
int tempe = A1;
int buzze = 12;
void setup(){
  pinMode(verde, OUTPUT);
  pinMode(verme, OUTPUT);
  pinMode(buzze, OUTPUT);
  pinMode(tempe, INPUT);
  
}

void loop(){
  digitalWrite(verde, HIGH);
  digitalWrite(verme, HIGH);
  tone(buzze, 1000);
  delay(2000);
  digitalWrite(verde, LOW);
  digitalWrite(verme, LOW);
  noTone(buzze);
  delay(2000);
  
}
