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
  void display() {
    fill(250,0,0);
    ellipse(ET_x, ET_y, ET_r, ET_r);
    
  }
}