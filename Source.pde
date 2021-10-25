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
  
  void display() {
    fill(0,0,250);
    ellipse(S_x, S_y, S_r, S_r);
  }
}