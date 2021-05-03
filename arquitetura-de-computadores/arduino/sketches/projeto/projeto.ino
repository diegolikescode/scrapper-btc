int verde = 2;
int verme = 3;
int tempe = A1;
int buzze = 12;
#include <Thermistor.h>

Thermistor temp(1); //VARIÁVEL DO TIPO THERMISTOR, INDICANDO O PINO ANALÓGICO (A2) EM QUE O TERMISTOR ESTÁ CONECTADO
void setup() {
  pinMode(verde, OUTPUT);
  pinMode(verme, OUTPUT);
  pinMode(buzze, OUTPUT);
  Serial.begin(9600); //INICIALIZA A SERIAL
  delay(1000); //INTERVALO DE 1 SEGUNDO
}
void loop() {
  int temperature = temp.getTemp(); //VARIÁVEL DO TIPO INTEIRO QUE RECEBE O VALOR DE TEMPERATURA CALCULADO PELA BIBLIOTECA
  Serial.print("Temperatura: "); //IMPRIME O TEXTO NO MONITOR SERIAL
  Serial.print(temperature+40); //IMPRIME NO MONITOR SERIAL A TEMPERATURA MEDIDA
  Serial.println("*C"); //IMPRIME O TEXTO NO MONITOR SERIAL
  if(temperature <= -19){
    digitalWrite(verde, HIGH);
    digitalWrite(verme, LOW);
  }else{
    digitalWrite(verde, LOW);
    digitalWrite(verme, HIGH);
  }if(temperature>= -15){
    
    tone(buzze, 2000);
  }else{
    noTone(buzze);
  }
  
  delay(1000); //INTERVALO DE 1 SEGUNDO
}
