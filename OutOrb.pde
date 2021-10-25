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
  
  void display()
  {
    fill(200);
    ellipse(OO_x, OO_y, OO_r, OO_r);
    fill(0);
    ellipse(OO_x, OO_y, Out_r, Out_r);
  }
}