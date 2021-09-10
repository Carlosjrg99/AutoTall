long tiempo;
int disparador = 7;   // triger
int entrada = 8;      // echo
float distancia;

void setup()
{
  pinMode(disparador, OUTPUT);
  pinMode(entrada, INPUT);
  
  Serial.begin(9600);
}


void loop()
{
  digitalWrite(disparador, HIGH);
  delayMicroseconds(10);
  digitalWrite(disparador, LOW);
  tiempo = (pulseIn(entrada, HIGH)/2); 
  distancia = float(tiempo * 0.0343);
  Serial.println(distancia);
  delay(1000);
}
