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
  
  void display()
  {
    fill(200);
    ellipse(IO_x, IO_y, IO_r, IO_r);
    fill(0);
    line(in_x1, in_y1, in_x2, in_y2);
    line(in_x1, in_y2, in_x2, in_y1);
  }
}