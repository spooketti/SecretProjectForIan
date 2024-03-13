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
bool isComplete = false;
int section = 0;

//expected serial input:
// //redBool, blueBool, yellowBool, greenBool, completedBool
//possibly compress data down to just 0s and 1s
//red blue yellow and green control leds
//completed should control whether or not leds shoudl print

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

void writeMessage(int section)
{
  switch(section)
  {
    case 1:
    device.setCursor(0,0);
  	device.print("would");
    break;
    
    case 2:
    device.setCursor(6,0);
  	device.print("you");
    break;
    
    case 3:
    device.setCursor(10,0);
  	device.print("AA");
    break;
    
    case 4:
    device.setCursor(13,0);
  	device.print("BB");
    break;
    
    case 5:
    device.setCursor(0,1);
  	device.print("CD?");
    isComplete = false;
    break;
  }
}

void loop() 
{
  controller();
  if(isComplete)
  {
    section++;
    writeMessage()
  }
  delay(200);
}


