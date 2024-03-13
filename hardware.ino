#include<LiquidCrystal.h>
#define X A0;
#define Y A1;
#define RED = 6;
#define GREEN = 7;
#define YELLOW = 8;
#define BLUE = 9;
int xValue = 0;
int yValue = 0;
String XString, YString = "";
LiquidCrystal device(12, 11, 5, 4, 3, 2);
int LEDList[4] = {RED,BLUE,YELLOW,GREEN}; //IMPORTANT!!!!!!

void setup() 
{
  Serial.begin(9600);
  device.begin(16,2);
}

void LEDControl(bool toggle[], int size)
{
  for(int i=0;i<size;i++)
  {
    digitalWrite(LEDLIST[i],toggle[i]);
  }
}

void controller()
{
  xValue = analogRead(X);
  yValue = analogRead(Y);
  XString = String(xValue);
  YString = String(yValue);
  Serial.print(XString + "," + YString);
}

void writeMessage()
{
  device.setCursor(0,1);
  device.print("DEBUG IAN");
}

void loop() 
{
  controller();
  delay(200);
}


