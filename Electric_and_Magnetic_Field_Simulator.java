import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Electric_and_Magnetic_Field_Simulator extends PApplet {

//bounds and position of option box
int OB_x = 80;
int OB_y = 100;
int OB_w = 2*OB_x;
int OB_h = 2*OB_y;
//button positions in options box
float buttonConstant = OB_y/7;
float button_x = 1.5f*OB_x;
float button1_y = 2*buttonConstant;
float button2_y = 4.5f*buttonConstant;
float button3_y = 7.3f*buttonConstant;
float button4_y = 10*buttonConstant;
float button5_y = 12.6f*buttonConstant;
float button_s = 15;
int Data_h = 575; 
//attributes of ETest charge
float EET_x = 350;
float ET_x = 350;
float ET_y = 350;
int ET_r = 10;
double ET_Q = 1*Math.pow(10, -6);
float ET_v = 0;
float ET_m = 10;
float ET_dx = 0;
float ET_dy = 0;
//number of rows and columns for array of orbs
int numCols = 14;
int numRows = 8;
int Eline_y = Data_h - 20;
//attributes of source charge
float SC_x = EET_x - 250;
float S_x = 0;
float S_y = 0;
int S_r = 60;
double S_Q = 1;
//attributes for example source charge and test charge
float ExS_x = EET_x - 200;
float ExS_y = Data_h + 50;
int ExS_r = 70;
float ExET_x = EET_x + 40;
float ExET_y = Data_h + 60;
int ExET_r = 50;
//Variables for labels and text in ETest charge data
float TData_x = EET_x + 400;
float ETData_x = TData_x - 20;
float ETDRect1_x = ETData_x + 230;
float ETDRect2_x = ETData_x + 157;
float ETDRect3_x = ETData_x + 123;
float ETDRect4_x = ETData_x + 180;
float ETDRect1_y = Data_h + 15;
float ETDRect2_y = Data_h + 35;
float ETDRect3_y = Data_h + 55;
float ETDRect4_y = Data_h + 75;
int ETD_w = 70;
int ETD_h = 15;
//Variables for text in EField attributes
float Att_x = EET_x + 200;
float EAtt_x = Att_x - 40;
int ETCharge_y = Data_h + 15;
int ESCharge_y = Data_h + 35;
int EPosition_y = Data_h + 55;
int EMass_y = Data_h + 75;
int ERadius_y = Data_h + 95;
float EChargeBound_x = EAtt_x + 50;
float EPositionBound_x = EAtt_x + 80;
float EMassBound_x = EAtt_x + 60;
float ERadiusBound_x = EAtt_x + 40;
int EYbound = 5;
//Initialization of EField classes
Source source = new Source(S_x, S_y, S_r, S_Q);
Source ExSource = new Source(ExS_x, ExS_y, ExS_r, S_Q);
ETest Etest = new ETest(ET_x, ET_y, ET_r, ET_Q, ET_v, ET_m, ET_dx, ET_dy);
ETest ExTest = new ETest(ExET_x, ExET_y, ExET_r, ET_Q, ET_v, ET_m, ET_dx, ET_dy);
Source[] Source = new Source[100];
int S_i = 0;
//Variables in ETestCharging() and SourceCharging()
boolean sourceCharging = false;
boolean editSource = false;
boolean EtestCharging = false;
boolean EditETest = false;
boolean ETestCreated = false;
boolean SourceCreated = false;
boolean TestChargeSelected = false;
boolean SourceChargeSelected = false;
//ETest Charge Data Components
float EStrength = 0;
float EForce = 0;
float ESpeed = 0;
float EAcceleration = 0;
double XEForce = 0;
double YEForce = 0;
double XEAcceleration = 0;
double YEAcceleration = 0;
double k = (8.998f)*Math.pow(10, 9);
int textSize = 14;
//Freeze variable in EField
boolean Efreeze = false;
//Variables for "pos" and "neg" buttons
float EPosX = EAtt_x + 172;
float ENegX = EAtt_x + 202;
int EchargeY = ETCharge_y - 5;
boolean positiveETest = false;
boolean negativeETest = false;
//X Positions for example MTest charge, attributes text, and MTest Data text
int MMT_x = 350;
float MAtt_x = Att_x - 20;
int MTData_x = MMT_x +390;
//InOrbs Attributes
int IO_x = 0;
int IO_y = 0;
int IO_r = 30;
int IO_co = 200;
float in_x1 = 0;
float in_y1 = 0;
float in_x2 = 0;
float in_y2 = 0;
//OutOrbs Attributes
int OO_x = 0;
int OO_y = 0;
int OO_r = 30;
int OO_co = 0;
int Out_r = OO_r/3;
int Out_co = 0;
//Example inorb and outorb positions
int ExIO_x = MMT_x - 160;
int ExOO_x = MMT_x - 260;
int ExO_y = Data_h + 40;
int O_gap = 40;
double B = 6 * Math.pow(10,-4);
int Mline_y = Data_h - 20;
//Magnetic Test Charge attributes
float MT_x = 0;
float MT_y = 0;
int MT_r = 10;
double MT_Q = 20;
double MT_B = B;
float MT_m = .2f;
float MT_dx = 0;
float MT_dy = 5;
double MT_v = Math.pow(Math.pow(MT_dx,2) + Math.pow(MT_dy,2),.5f);
//Example MTest Charge Attributes
int ExMT_x = MMT_x + 40;
int ExMT_y = Data_h + 60;
int ExMT_r = 50;
//MTest Charge Data labels and text 
int MTDRect1_x = MTData_x + 160;
int MTDRect2_x = MTData_x + 120;
int MTDRect3_x = MTData_x + 170;
int MTDRect1_y = Data_h + 25;
int MTDRect2_y = Data_h + 55;
int MTDRect3_y = Data_h + 85;
int MTD_w = 50;
int MTD_h = 15;
//MTest Charge Data Components
float MForce_y = Data_h + 30;
float MSpeed_y = Data_h + 60;
float MAcceleration_y = Data_h + 90;
float MForce = 0;
float MSpeed = 0;
float MAcceleration = 0;
double XMForce = 0;
double YMForce = 0;
double XMAcceleration = 0;
double YMAcceleration = 0;
double MRadius = 0;
//MField Attributes Text
float MField_y = Data_h + 20;
float MCharge_y = Data_h + 40;
float MPosition_y = Data_h + 80;
float MMass_y = Data_h + 100;
float MFieldBound_x = MAtt_x + 120;
float MChargeBound_x = MAtt_x + 50;
float MSpeedBound_x = MAtt_x + 80;
float MPositionBound_x = MAtt_x + 80;
float MMassBound_x = MAtt_x + 60;
int MYbound = 5;
//MtestCharging(), InOrbing() and OutOrbing() variables
boolean MtestCharging = false;
boolean editMTest = false;
boolean MTestCreated = false;
boolean MTestChargeSelected = false;
boolean EditMTest = false;
float MT_yi = 0;
float MT_xi = 0;
boolean InOrbSelected = false;
boolean OutOrbSelected = false;
//Freeze Variable in MField
boolean Mfreeze = false;
//Initialization of MField classes
MTest MTest = new MTest(MT_r, MT_y, MT_r, MT_Q, MT_v, MT_m, MT_B, MT_dx, MT_dy);
MTest ExMTest = new MTest(ExMT_x, ExMT_y, ExMT_r, MT_Q, MT_v, MT_m, MT_B, MT_dx, MT_dy);
InOrb inorb= new InOrb(IO_x, IO_y, IO_r, in_x1, in_y1, in_x2, in_y2);
OutOrb outorb = new OutOrb(OO_x, OO_y, OO_r, Out_r);
InOrb[] InOrb = new InOrb[numRows*numCols];
OutOrb[] OutOrb = new OutOrb[numRows*numCols];
float startTime = 0;
float t = 0;
//Positions and Sizes of pos, neg, +, and - buttons in MField
int MPosX = (int)MAtt_x + 160;
int MNegX = (int)MAtt_x + 190;
int MChargeY = Data_h + 35;
int CButtonlength = 25;
int CButtonheight = 15;
int PlusX = (int)MAtt_x + 135;
int MinusX = (int)MAtt_x + 150;
int SignY = Data_h + 55;
int Signlength = 10;
//Efield and Mfield selection booleans
boolean EField = false;
boolean MField = false;
public void setup() 
{
  
  System.out.println("Please select the electric field or magnetic field");
}

public void EClearScreen() {
  ET_x = 350;
  ET_y = 350;
  ET_r = 10;
  ET_v = 0;
  ET_m = 10;
  ET_dx = 0;
  ET_dy = 0;
  for (int i = 0; i < S_i; i++) {
    Source[i].S_x = 0;
    Source[i].S_y = 0;
    S_r = 60;
  }
  SourceCreated = false;
  ETestCreated = false;
  S_i = 0;
  Efreeze = false;
}

public void EtestCharging() {
      ET_v = 0;
      ET_dx = 0;
      ET_dy = 0;
      EditETest = true;
      Efreeze = false;
      boolean ETestPositionSelected = false;      
      System.out.println("Select a test charge position");
      if(ETestPositionSelected == false) {
        if((mouseY+ET_r<=Eline_y && mouseY-ET_r>=0 && mouseX-ET_r>=OB_w && mouseX+ET_r<= 1000) || (mouseX-ET_r>=0 && mouseX+ET_r<=1000 && mouseY-ET_r>=OB_h && mouseY+ET_r<=Eline_y)) {
          ET_x = mouseX;
          ET_y = mouseY;
          ETestPositionSelected = true;
        } 
        else {
          System.out.println("Please select a different position");
        }
      }
      negativeETest = false;
      positiveETest = false;
      if(ETestPositionSelected == true) {
        EtestCharging = false;
        ETestCreated = true;
        System.out.println("Position of Test Charge set at (" + ET_x + ", " + ET_y + ")");
        EditETest = false;
      }
    }

public void sourceCharging() {
      editSource = true;
      Source[S_i] = new Source(S_x, S_y, S_r, S_Q);
      boolean SourcePositionSelected = false;
      if(SourcePositionSelected == false) {
        if((mouseY+S_r<=Eline_y && mouseY-S_r>=0 && mouseX-S_r>=OB_w && mouseX+S_r<= 1000)||((mouseX-S_r>=0) && (mouseX+S_r<=1000) && mouseY-S_r>=OB_h && mouseY+S_r<=Eline_y)){
          Source[S_i].S_x = mouseX;
          Source[S_i].S_y = mouseY;
          SourcePositionSelected = true;
        } 
        else {
          System.out.println("Please select a different position");
        }
      }
      if(SourcePositionSelected == true) {
        System.out.println("Position of Source Charge " + S_i + " set at (" + Source[S_i].S_x + ", " + Source[S_i].S_y + ")");
        sourceCharging = false;
        SourceCreated = true;
        editSource = false;
        S_i++;
      }
    }
public void MClearScreen() {
  MT_x = 0;
  MT_y = 0;
  MT_r = 10;
  MT_Q = 20;
  MT_B = B;
  MT_m = .2f;
  MT_dx = 0;
  MT_dy = 5;
  MTestCreated = false;
  InOrbSelected = false;
  OutOrbSelected = false;
  Mfreeze = false;
}

public void MtestCharging() {
      t = 0;
      EditMTest = true;
      Mfreeze = false;
      boolean MTestPositionSelected = false;
      System.out.println("Select a test charge position");
      if(MTestPositionSelected == false) {
        if((mouseY+MT_r<=Mline_y && mouseY-MT_r>=0 && mouseX-MT_r>=OB_w && mouseX+MT_r<=1000) || (mouseX-MT_r>=0 && mouseX+MT_r<=1000 && mouseY-MT_r>=OB_h && mouseY+MT_r<=Mline_y)) {
          MT_x = mouseX;
          MT_y = mouseY;
          MTestPositionSelected = true;
        } 
        else {
          System.out.println("Please select a different position");
        }
      }
      negativeETest = false;
      positiveETest = false;
      if(MTestPositionSelected == true) {
        MtestCharging = false;
        MTestCreated = true;
        System.out.println("Position of Test Charge set at (" + MT_x + ", " + MT_y + ")");
        EditMTest = false;
        MT_xi = MT_x;
        MT_yi = MT_y;
        startTime = 1000*millis();
      }
    }
    
public void OutOrbing() {
  InOrbSelected = false;
        for(int i = 0 ; i < numRows*numCols; i++)
        {
           int r = PApplet.parseInt(i/numCols);
           int c = i%numCols;
           int OO_x = (O_gap+c*(IO_r+O_gap));
           int OO_y = (O_gap+r*(IO_r+O_gap));
           if(OO_x - OO_r >= OB_w - 10  || OO_y - OO_r > OB_h - 10) {
               OutOrb[i] = new OutOrb(OO_x, OO_y, OO_r, Out_r);
           }
        }
     OutOrbSelected = true;
     MT_xi = MT_x;
     MT_yi = MT_y;
     t = 0;
}
      
public void InOrbing() {
  OutOrbSelected = false;
        for(int i = 0 ; i < numRows*numCols; i++)
        {
           int r = PApplet.parseInt(i/numCols);
           int c = i%numCols;
           int IO_x = (O_gap+c*(IO_r+O_gap));
           int IO_y = (O_gap+r*(IO_r+O_gap));
           float in_x1 = IO_x - IO_r*sqrt(2)/4;
           float in_y1 = IO_y + IO_r*sqrt(2)/4;
           float in_x2 = IO_x + IO_r*sqrt(2)/4;
           float in_y2 = IO_y - IO_r*sqrt(2)/4;
           if(IO_x - IO_r >= OB_w - 10  || IO_y - IO_r > OB_h - 10) {
               InOrb[i] = new InOrb(IO_x, IO_y, IO_r, in_x1, in_y1, in_x2, in_y2);
            }
         }
         InOrbSelected = true;
         MT_xi = MT_x;
         MT_yi = MT_y;
         t = 0;
}

public void mouseClicked() {
  if (mouseX <= button_x + button_s && mouseX >= button_x - button_s && mouseY >= button1_y - button_s && mouseY <= button1_y + button_s) {
    EField = true;
    MField = false;
    System.out.println("First create source charges; then create a test charge");
  }
  if (mouseX <= button_x + button_s && mouseX >= button_x - button_s && mouseY >= button2_y - button_s && mouseY <= button2_y + button_s) {
    MField = true;
    EField = false;
    System.out.println("First select the direction of the magnetic field; then create a test charge");
  }
  if(EField == true) {
     if(EtestCharging == true ) {
       EtestCharging();
    }
    
    if(sourceCharging == true) {
        sourceCharging(); 
    }
    
    if (sourceCharging == false && EtestCharging == false && EditETest == false && editSource == false) {
      if (mouseX <= button_x + button_s && mouseX >= button_x - button_s && mouseY >= button3_y - button_s && mouseY <= button3_y + button_s) {
        EClearScreen();
      }
  
      if (mouseX <= button_x + button_s && mouseX >= button_x - button_s && mouseY >= button4_y - button_s && mouseY <= button4_y + button_s) {
        Efreeze = true;
        System.out.println("freeze");
      }
      if (mouseX <= button_x + button_s && mouseX >= button_x - button_s && mouseY >= button5_y - button_s && mouseY <= button5_y + button_s) {
        Efreeze = false;
        System.out.println("Unfreeze");
      }
      if (mouseX >= ExET_x - ExET_r  && mouseX <= ExET_x + ExET_r && mouseY >= ExET_y - ExET_r && mouseY <= ExET_y + ExET_r) {
        EtestCharging = true;
      }
      if (mouseX >= ExS_x - ExS_r && mouseX <= ExS_x + ExS_r && mouseY >= ExS_y - ExS_r && mouseY <= ExS_y + ExS_r) {
        sourceCharging = true;
        System.out.println("Select a source charge position");
      }
      if (mouseX <= EPosX + CButtonlength/2 && mouseX >= EPosX - CButtonlength/2 && mouseY <= EchargeY + CButtonheight/2 && mouseY >= EchargeY - CButtonheight/2) {
        System.out.println("Test Charge is now positive");
        ET_Q = abs((float)ET_Q);
      }
      if (mouseX <= ENegX + CButtonlength/2 && mouseX >= ENegX - CButtonlength/2 && mouseY <= EchargeY + CButtonheight/2 && mouseY >= EchargeY - CButtonheight/2) {
        System.out.println("Test Charge is now negative");
        ET_Q = -1*abs((float)ET_Q);
      }
    }
    }
    if(MField == true) {
        if(MtestCharging == true ) {
           MtestCharging();
         }
          
      if (MtestCharging == false && EditMTest == false) {
        if (mouseX <= button_x + button_s && mouseX >= button_x - button_s && mouseY >= button3_y - button_s && mouseY <= button3_y + button_s) {
          MClearScreen();
        }
    
        if (mouseX <= button_x + button_s && mouseX >= button_x - button_s && mouseY >= button4_y - button_s && mouseY <= button4_y + button_s) {
          Mfreeze = true;
          System.out.println("freeze");
        }
        if (mouseX <= button_x + button_s && mouseX >= button_x - button_s && mouseY >= button5_y - button_s && mouseY <= button5_y + button_s) {
          Mfreeze = false;
          System.out.println("Unfreeze");
        }
        if (mouseX >= ExMT_x - ExMT_r  && mouseX <= ExMT_x + ExMT_r && mouseY >= ExMT_y - ExMT_r && mouseY <= ExMT_y + ExMT_r) {
          MtestCharging = true;
        }
        if (mouseX >= ExOO_x - OO_r && mouseX <= ExOO_x + OO_r && mouseY >= ExO_y - OO_r && mouseY <= ExO_y +OO_r) {
           OutOrbing();
        }
        if (mouseX >= ExIO_x - IO_r && mouseX <= ExIO_x + IO_r && mouseY >= ExO_y - OO_r && mouseY <= ExO_y +OO_r) {
          InOrbing();
        }
        if (mouseX <= MPosX + CButtonlength/2 && mouseX >= MPosX - CButtonlength/2 && mouseY <= MChargeY + CButtonheight/2 && mouseY >= MChargeY - CButtonheight/2) {
          System.out.println("Test Charge is now positive");
          MT_Q = abs((float)MT_Q);
          MT_xi = MT_x;
          MT_yi = MT_y;
          t = 0;
        }
        if (mouseX <= MNegX + CButtonlength/2 && mouseX >= MNegX - CButtonlength/2 && mouseY <= MChargeY + CButtonheight/2 && mouseY >= MChargeY - CButtonheight/2) {
          System.out.println("Test Charge is now negative");
          MT_Q = -1*abs((float)MT_Q);
          MT_xi = MT_x;
          MT_yi = MT_y;
          t = 0;
        }
        if (mouseX <= PlusX + Signlength/2 && mouseX >= PlusX - Signlength/2 && mouseY <= SignY + Signlength/2 && mouseY >= SignY - Signlength/2) {
          MT_dy += 1;
          System.out.println("Speed is now " + MSpeed + " m/s");
        }
        if (mouseX <= MinusX + Signlength/2 && mouseX >= MinusX - Signlength/2 && mouseY <= SignY + Signlength/2 && mouseY >= SignY - Signlength/2) {
          MT_dy -= 1;
          System.out.println("Speed is now " + MSpeed + " m/s");
        }
    }
   }
}


   

public void EStrength() {
  EStrength = 0;
  for (int i = 0; i < S_i; i++) {
    EStrength += k*S_Q/(Math.pow(dist(ET_x, ET_y, Source[i].S_x, Source[i].S_y), 2));
  }
  int RoundedEstrength = round(EStrength);
  str(RoundedEstrength);
  text(RoundedEstrength, ETDRect1_x - ETD_w/2, ETDRect1_y + ETD_h/2);
}
public void EForce() {
  XEForce = 0;
  YEForce = 0;
  EForce = 0;
  for (int i = 0; i < S_i; i++) {
    EForce += k * S_Q * ET_Q/(Math.pow(dist(ET_x, ET_y, Source[i].S_x, Source[i].S_y), 2));
    XEForce += EForce * (ET_x-Source[i].S_x)/(dist(ET_x, ET_y, Source[i].S_x, Source[i].S_y));
    YEForce += EForce * (ET_y-Source[i].S_y)/(dist(ET_x, ET_y, Source[i].S_x, Source[i].S_y));
  }
  text(EForce, ETDRect2_x - ETD_w/2, ETDRect2_y + ETD_h/2);
}

public void ESpeed() {
  ESpeed = 0;
  ESpeed += Math.pow(Math.pow(ET_dx,2) + Math.pow(ET_dy,2), .5f);
  str(ESpeed);
  text(ESpeed, ETDRect3_x - ETD_w/2, ETDRect3_y + ETD_h/2);
}

public void EAcceleration() {
  EAcceleration = 0;
  EAcceleration = EForce/ET_m;
  XEAcceleration = XEForce/ET_m;
  YEAcceleration = YEForce/ET_m;
  text(EAcceleration, ETDRect4_x - ETD_w/2, ETDRect4_y + ETD_h/2);
}

public void EMovement() {
 ET_dx += XEAcceleration;
 ET_dy += YEAcceleration;
 ET_x += ET_dx;
 ET_y += ET_dy;
}
 
public void MForce() {
  if(OutOrbSelected == true) { 
    MForce = (float)(MSpeed * MT_B * MT_Q);
  }
  if(InOrbSelected == true) { 
    MForce = -1*(float)(MSpeed * MT_B * MT_Q);
  }
  str(MForce);
  text(MForce, MTDRect1_x - MTD_w/2, MTDRect1_y + MTD_h/2);
}

public void MSpeed() {
  MSpeed = (float)Math.pow(Math.pow(MT_dx,2) + Math.pow(MT_dy,2), .5f);
  str(MSpeed);
  text(MSpeed, MTDRect2_x - MTD_w/2, MTDRect2_y + MTD_h/2);
}

public void MAcceleration() {
  MAcceleration = MForce/MT_m;
  str(MAcceleration);
  text(MAcceleration, MTDRect3_x - MTD_w/2, MTDRect3_y + MTD_h/2);
}

public void MRadius() {
  MRadius = (MT_m*Math.pow(MSpeed,2)/(MForce));
}

public void MMovement() {
 float endTime = 1000*millis();
 if((endTime - startTime) % 2 == 0) {
   t += 1;
 }
 MT_x = (float)(MT_xi - MRadius + MRadius *cos((float)((MSpeed/MRadius)*(t))));
 MT_y = (float)(MT_yi + MRadius * sin((float)((MSpeed/MRadius)*(t))));
}



public void draw() {
    rectMode(CENTER);
    fill(255);
    rect(OB_x, OB_y, OB_w, OB_h);
    fill(100, 100, 200);
    rect(button_x, button1_y, button_s, button_s);
    rect(button_x, button2_y, button_s, button_s);
    rect(button_x, button3_y, button_s, button_s);
    rect(button_x, button4_y, button_s, button_s);
    rect(button_x, button5_y, button_s, button_s);
    textSize(textSize);
    fill(100, 100, 0);
    text("Electric Field", OB_x/6, 7*buttonConstant/3);
    fill(150, 0, 150);
    text("Magnetic Field", OB_x/6, 15*buttonConstant/3);
    fill(0, 255, 0);
    text("Clear Screen", OB_x/6, 23*buttonConstant/3);
    fill(0, 0, 255);
    text("Freeze Test", OB_x/5, 31*buttonConstant/3);
    text("Unfreeze Test", OB_x/6, 39*buttonConstant/3);
    fill(0);
  if(EField == true) {
    background(255);
    rectMode(CENTER);
    fill(255);
    rect(OB_x, OB_y, OB_w, OB_h);
    fill(100, 100, 200);
    rect(button_x, button1_y, button_s, button_s);
    rect(button_x, button2_y, button_s, button_s);
    rect(button_x, button3_y, button_s, button_s);
    rect(button_x, button4_y, button_s, button_s);
    rect(button_x, button5_y, button_s, button_s);
    textSize(textSize);
    fill(100, 100, 0);
    text("Electric Field", OB_x/6, 7*buttonConstant/3);
    fill(150, 0, 150);
    text("Magnetic Field", OB_x/6, 15*buttonConstant/3);
    fill(0, 255, 0);
    text("Clear Screen", OB_x/6, 23*buttonConstant/3);
    fill(0, 0, 255);
    text("Freeze Test", OB_x/5, 31*buttonConstant/3);
    text("Unfreeze Test", OB_x/6, 39*buttonConstant/3);
    ExSource.display();
    ExTest.display();
    fill(0);
    text("Test Charge", EET_x, Data_h);
    text("Attributes", Att_x, Data_h);
    text("Test Charge Data", TData_x, Data_h);
    line(0, Eline_y, 1000, Data_h - 20);
    fill(0, 0, 255);
    fill(0);
    text("Source Charge", SC_x, Data_h);
    text("T Charge = " + (float)ET_Q + " (C)", EAtt_x, ETCharge_y);
    fill(255);
    rect(EPosX, EchargeY, CButtonlength, CButtonheight);
    fill(0);
    text("pos", EAtt_x + 160, ETCharge_y);
    fill(255);
    rect(ENegX, EchargeY, CButtonlength, CButtonheight);
    fill(0);
    text("neg", EAtt_x + 190, ETCharge_y);
    text("S Charge = " + S_Q + " (C)", EAtt_x, ESCharge_y);
    text("Position (x,y)", EAtt_x, EPosition_y);
    text("Radius (m)", EAtt_x, ERadius_y);
    text("Mass = " + ET_m + " (kg)", EAtt_x, EMass_y);
    text("Electric Field Strength (N/C)", ETData_x, Data_h + 20);
    text("Electric Force (N)", ETData_x, Data_h + 40);
    text("Speed (m/s)", ETData_x, Data_h + 60);
    text("Acceleration (m/s/s)", ETData_x, Data_h + 80);
    fill(150);
    rectMode(CENTER);
    rect(ETDRect1_x, ETDRect1_y, ETD_w, ETD_h);
    rect(ETDRect2_x, ETDRect2_y, ETD_w, ETD_h);
    rect(ETDRect3_x, ETDRect3_y, ETD_w, ETD_h);
    rect(ETDRect4_x, ETDRect4_y, ETD_w, ETD_h);
    
    if(SourceCreated == true) {
      for(int i = 0; i < S_i; i++) {
        Source[i].display();
      }
    }
    if(SourceCreated == true && ETestCreated == true) {
        ETest ETest = new ETest(ET_x, ET_y, ET_r, ET_Q, ET_v, ET_m, ET_dx, ET_dy);
        ETest.display();
    }
    if (SourceCreated == true && ETestCreated == true) {
      fill(0);
      EStrength();
      EForce();
      ESpeed();
      EAcceleration();
      if(Efreeze == false) {
        EMovement();
      }
    }
    if(SourceCreated==true && ETestCreated==true) {
      for(int i = 0; i < S_i; i++) {
        if(ET_x-ET_r<=0 || ET_x+ET_r>=1000 || ET_y+ET_r>=Eline_y || ET_y-ET_r<=0 || ET_x-ET_r<=OB_w && ET_y-ET_r<=OB_h || dist(ET_x,ET_y,Source[i].S_x,Source[i].S_y)<=(S_r+ET_r)/2) {
        Efreeze = true;
        }
      }
    }
  }
  if(MField == true) {
    background(255);
    ExMTest.display();
     rectMode(CENTER);
    fill(255);
    rect(OB_x, OB_y, OB_w, OB_h);
    fill(100, 100, 200);
    rect(button_x, button1_y, button_s, button_s);
    rect(button_x, button2_y, button_s, button_s);
    rect(button_x, button3_y, button_s, button_s);
    rect(button_x, button4_y, button_s, button_s);
    rect(button_x, button5_y, button_s, button_s);
    textSize(textSize);
    fill(100, 100, 0);
    text("Electric Field", OB_x/6, 7*buttonConstant/3);
    fill(150, 0, 150);
    text("Magnetic Field", OB_x/6, 15*buttonConstant/3);
    fill(0, 255, 0);
    text("Clear Screen", OB_x/6, 23*buttonConstant/3);
    fill(0, 0, 255);
    text("Freeze Test", OB_x/5, 31*buttonConstant/3);
    text("Unfreeze Test", OB_x/6, 39*buttonConstant/3);
    fill(0);
    text("Test Charge", MMT_x, Data_h);
    text("Attributes", Att_x, Data_h);
    text("Test Charge Data", MTData_x, Data_h);
    line(0, Mline_y, 1000, Mline_y);
    fill(0, 0, 255);
     text("Direction of Magnetic Field", MMT_x - 300 , Data_h);
     fill(0);
     text("Field Strength = 6E-4 (T)", MAtt_x, MField_y);
     text("T Charge = " + MT_Q + " (C)", MAtt_x, MCharge_y);
     fill(255);
     rect(MPosX, MChargeY, CButtonlength, CButtonheight);
     fill(0);
     text("pos", MAtt_x + 148, Data_h + 40);
     fill(255);
     rect(MNegX, MChargeY, CButtonlength, CButtonheight);
     fill(0);
     text("neg", MAtt_x + 178, Data_h + 40);
     text("Speed = " + MT_v + " (m/s)", MAtt_x, MSpeed_y);
     fill(255);
     rect(PlusX, SignY, Signlength, Signlength);
     fill(0);
     text("+", PlusX - Signlength/2, Data_h + 60);
     fill(255);
     rect(MinusX, SignY, Signlength, Signlength);
     fill(0);
     text("-", MinusX - 5*Signlength/12, Data_h + 60);
     text("Position (x,y)", MAtt_x, MPosition_y);
     text("Mass = " + MT_m + " (kg)", MAtt_x, MMass_y);
     text("Magnetic Force (N):", MTData_x + 1, MForce_y);
     text("Speed (m/s):", MTData_x, MSpeed_y);
     text("Acceleration (m/s/s):", MTData_x, MAcceleration_y);
     fill(150);
     rect(MTDRect1_x, MTDRect1_y, MTD_w, MTD_h);
     rect(MTDRect2_x, MTDRect2_y, MTD_w + 10, MTD_h);
     rect(MTDRect3_x, MTDRect3_y, MTD_w, MTD_h);
     text("Out of Screen", ExOO_x - 50, Data_h + 80);
     text("Into Screen", ExIO_x - 40, Data_h + 80);
     OutOrb outorb = new OutOrb(ExOO_x, ExO_y, OO_r, Out_r);
     outorb.display();
     InOrb inorb = new InOrb(ExIO_x, ExO_y, IO_r, ExIO_x - IO_r * sqrt(2)/4, ExO_y + IO_r * sqrt(2)/4, ExIO_x + IO_r * sqrt(2)/4, ExO_y - IO_r * sqrt(2)/4);
     inorb.display();
     if(InOrbSelected && !OutOrbSelected) {
       for(int i = 0; i < (numRows*numCols); i++)
          {
             if (InOrb[i] != null)
             {
                 InOrb[i].display();
             }
           }
     }
     if(OutOrbSelected && !InOrbSelected) {
       for(int i = 0; i < (numRows*numCols); i++)
          {
             if (OutOrb[i] != null)
             {
                OutOrb[i].display();
             }
          }
     }
     if(MTestCreated == true && (InOrbSelected == true || OutOrbSelected == true)) {
        MTest MTest = new MTest(MT_x, MT_y, MT_r, MT_Q, MT_v, MT_m, MT_B, MT_dx, MT_dy);
        MTest.display();
    }
    
    if (MTestCreated == true && (InOrbSelected == true || OutOrbSelected == true)) {
      fill(0);
      MSpeed();
      MForce();
      MAcceleration();
      MRadius();
      if(Mfreeze == false) {
        MMovement();
      }
    }
    if(MTestCreated == true && (InOrbSelected == true || OutOrbSelected == true)) {
      if(((MT_x + MT_r >= 1000) || (MT_y + MT_r >= Mline_y) || (MT_y - MT_r <= 0) || (MT_x - MT_r <= OB_w && MT_y - MT_r <= OB_h))) {
        Mfreeze = true;
      }
    }
  }
}
class ETest {
  //color?
  float ET_x = 350;
  float ET_y = 350;
  int ET_r = 10;
  double ET_Q = 20;
  float ET_v = 0;
  float ET_m = 1;
  float ET_dx = 0;
  float ET_dy = 0;
  
  ETest(float ET_x, float ET_y, int ET_r, double ET_Q, float ET_v, float ET_m, float ET_dx, float ET_dy) {
    this.ET_x = ET_x;
    this.ET_y = ET_y;
    this.ET_r = ET_r;
    this.ET_Q = ET_Q; 
    this.ET_v = ET_v;
    this.ET_m = ET_m;
    this.ET_dx = ET_dx;
    this.ET_dy = ET_dy;
  }
  public void display() {
    fill(250,0,0);
    ellipse(ET_x, ET_y, ET_r, ET_r);
    
  }
}
class InOrb
{ 
  int IO_x = 0;
  int IO_y = 0;
  int IO_r = 30;
  float in_x1 = 0;
  float in_y1 = 0;
  float in_x2 = 0;
  float in_y2 = 0;
  
  

  InOrb(int IO_x, int IO_y, int IO_r, float in_x1, float in_y1, float in_x2, float in_y2)
  {
    this.IO_x = IO_x;
    this.IO_y = IO_y;
    this.IO_r = IO_r;
    this.in_x1 = in_x1;
    this.in_y1 = in_y1;
    this.in_x2 = in_x2;
    this.in_y2 = in_y2;
  }
  
  public void display()
  {
    fill(200);
    ellipse(IO_x, IO_y, IO_r, IO_r);
    fill(0);
    line(in_x1, in_y1, in_x2, in_y2);
    line(in_x1, in_y2, in_x2, in_y1);
  }
}
class MTest {
  float MT_x = 0;
  float MT_y = 0;
  int MT_r = 10;
  double MT_Q = 20;
  double MT_v = 0;
  float MT_m = 20;
  double MT_B = 6 * Math.pow(10,-4);
  float MT_dx = 20;
  float MT_dy = 20;
  
  MTest(float MT_x, float MT_y, int MT_r, double MT_Q, double MT_v, float MT_m, double MT_B, float MT_dx, float MT_dy) {
    this.MT_x = MT_x;
    this.MT_y = MT_y;
    this.MT_r = MT_r;
    this.MT_Q = MT_Q;
    this.MT_v = MT_v;
    this.MT_m = MT_m;
    this.MT_B = MT_B;
    this.MT_dx = MT_dx;
    this.MT_dy = MT_dy;
  }
  public void display() {
    fill(255,0,0);
    ellipse(MT_x, MT_y, MT_r, MT_r);
  }
}
class OutOrb
{ 
  int OO_x = 0;
  int OO_y = 0;
  int OO_r = 30;
  int Out_r = 15;
  

  OutOrb(int OO_x, int OO_y, int OO_r, int Out_r)
  {
    this.OO_x = OO_x;
    this.OO_y = OO_y;
    this.OO_r = OO_r;
    this.Out_r = Out_r;
  }
  
  public void display()
  {
    fill(200);
    ellipse(OO_x, OO_y, OO_r, OO_r);
    fill(0);
    ellipse(OO_x, OO_y, Out_r, Out_r);
  }
}
class Source {
  float S_x = 0;
  float S_y = 0;
  int S_r = 60;
  double S_Q = 0;

  
  Source(float S_x, float S_y, int S_r, double S_Q) {
    this.S_x = S_x;
    this.S_y = S_y;
    this.S_r = S_r;
    this.S_Q = S_Q;
  }
  
  public void display() {
    fill(0,0,250);
    ellipse(S_x, S_y, S_r, S_r);
  }
}
  public void settings() {  size(1000,1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Electric_and_Magnetic_Field_Simulator" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
