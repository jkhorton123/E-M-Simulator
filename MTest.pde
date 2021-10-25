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
  void display() {
    fill(255,0,0);
    ellipse(MT_x, MT_y, MT_r, MT_r);
  }
}