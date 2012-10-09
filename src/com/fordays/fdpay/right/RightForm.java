package com.fordays.fdpay.right;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class RightForm extends ActionForm
{
  private boolean tsa1 = false;
  private boolean tsa2 = false;
  private boolean gsa1 = false;
  private boolean gsa2 = false;
  private boolean gsa3 = false;
  private boolean gsa4 = false;
  private boolean gsb1 = false;
  private boolean msa1 = false;
  private boolean msa2 = false;
  private boolean msa3 = false;
  private boolean msa4 = false;
  private boolean msb1 = false;
  private boolean msb2 = false;
  private boolean msb3 = false;
  private boolean msb4 = false;
  private boolean msc1 = false;
  private boolean msc2 = false;
  private boolean msc3 = false;
  private boolean msc4 = false;
  private boolean msd1 = false;
  private boolean msd2 = false;
  private boolean msd3 = false;
  private boolean msd4 = false;
  private boolean mse1 = false;
  private boolean mse2 = false;
  private boolean msf1 = false;
  private boolean msf2 = false;
  private boolean msg1 = false;
  private boolean msg2 = false;
  private boolean msh1 = false;
  private boolean msh2 = false;
  private boolean msi1 = false;
  private boolean msi2 = false;
  private boolean msj1 = false;
  private boolean msj2 = false;
  private boolean ssa1 = false;
  private boolean ssa2 = false;
  private boolean ssa3 = false;
  private boolean ssb1 = false;
  private boolean ssb2 = false;
  private boolean ssc1 = false;
  private boolean ssc2 = false;

  private String thisAction = "";
  private String userId = "";
  private boolean ssd1;

  public String getThisAction ()
  {
    return thisAction;
  }

  public boolean isGsa2 ()
  {
    return gsa2;
  }

  public boolean isGsa3 ()
  {
    return gsa3;
  }

  public boolean isGsa4 ()
  {
    return gsa4;
  }

  public boolean isGsb1 ()
  {
    return gsb1;
  }

  public boolean isMsa1 ()
  {
    return msa1;
  }

  public boolean isMsa2 ()
  {
    return msa2;
  }

  public boolean isMsa3 ()
  {
    return msa3;
  }

  public boolean isMsa4 ()
  {
    return msa4;
  }

  public boolean isMsb1 ()
  {
    return msb1;
  }

  public boolean isMsb2 ()
  {
    return msb2;
  }

  public boolean isMsb3 ()
  {
    return msb3;
  }

  public boolean isMsb4 ()
  {
    return msb4;
  }

  public boolean isMsc1 ()
  {
    return msc1;
  }

  public boolean isMsc2 ()
  {
    return msc2;
  }

  public boolean isMsd1 ()
  {
    return msd1;
  }

  public boolean isMse1 ()
  {
    return mse1;
  }

  public boolean isMse2 ()
  {
    return mse2;
  }

  public boolean isMsf1 ()
  {
    return msf1;
  }

  public boolean isMsg1 ()
  {
    return msg1;
  }

  public boolean isMsg2 ()
  {
    return msg2;
  }



  public boolean isSsa1 ()
  {
    return ssa1;

  }

  public boolean isSsa2 ()
  {
    return ssa2;
  }

  public boolean isSsa3 ()
  {
    return ssa3;
  }

  public boolean isSsb1 ()
  {
    return ssb1;
  }

  public boolean isTsa1 ()
  {
    return tsa1;
  }

  public boolean isTsa2 ()
  {
    return tsa2;
  }

  public void setTsa2 (boolean tsa2)
  {
    this.tsa2 = tsa2;
  }

  public void setTsa1 (boolean tsa1)
  {
    this.tsa1 = tsa1;
  }

  public void setThisAction (String thisAction)
  {
    this.thisAction = thisAction;
  }

  public void setSsb1 (boolean ssb1)
  {
    this.ssb1 = ssb1;
  }

  public void setSsa3 (boolean ssa3)
  {
    this.ssa3 = ssa3;
  }

  public void setSsa2 (boolean ssa2)
  {
    this.ssa2 = ssa2;
  }

  public void setSsa1 (boolean ssa1)
  {
    this.ssa1 = ssa1;
  }


  public void setMsg2 (boolean msg2)
  {
    this.msg2 = msg2;
  }

  public void setMsg1 (boolean msg1)
  {
    this.msg1 = msg1;
  }

  public void setMsf1 (boolean msf1)
  {
    this.msf1 = msf1;
  }

  public void setMse2 (boolean mse2)
  {
    this.mse2 = mse2;
  }

  public void setMse1 (boolean mse1)
  {
    this.mse1 = mse1;
  }

  public void setMsd1 (boolean msd1)
  {
    this.msd1 = msd1;
  }

  public void setMsc2 (boolean msc2)
  {
    this.msc2 = msc2;
  }

  public void setMsc1 (boolean msc1)
  {
    this.msc1 = msc1;
  }

  public void setMsb4 (boolean msb4)
  {
    this.msb4 = msb4;
  }

  public void setMsb3 (boolean msb3)
  {
    this.msb3 = msb3;
  }

  public void setMsb2 (boolean msb2)
  {
    this.msb2 = msb2;
  }

  public void setMsb1 (boolean msb1)
  {
    this.msb1 = msb1;
  }

  public void setMsa4 (boolean msa4)
  {
    this.msa4 = msa4;
  }

  public void setMsa3 (boolean msa3)
  {
    this.msa3 = msa3;
  }

  public void setMsa2 (boolean msa2)
  {
    this.msa2 = msa2;
  }

  public void setMsa1 (boolean msa1)
  {
    this.msa1 = msa1;
  }

  public void setGsb1 (boolean gsb1)
  {
    this.gsb1 = gsb1;
  }

  public void setGsa4 (boolean gsa4)
  {
    this.gsa4 = gsa4;
  }

  public void setGsa3 (boolean gsa3)
  {
    this.gsa3 = gsa3;
  }

  public void setGsa2 (boolean gsa2)
  {
    this.gsa2 = gsa2;
  }

  public String getUserId ()
  {
    return userId;
  }

  public void setUserId (String userId)
  {
    this.userId = userId;
  }

  public boolean isGsa1 ()
  {
    return gsa1;
  }

  public void setGsa1 (boolean gsa1)
  {
    this.gsa1 = gsa1;
  }

  public void reset (ActionMapping mpping, HttpServletRequest request)
  {
    this.setTsa1(false);
    this.setTsa2(false);
    this.setGsa1(false);
    this.setGsa2(false);
    this.setGsa3(false);
    this.setGsa4(false);
    this.setGsb1(false);
    this.setMsa1(false);
    this.setMsa2(false);
    this.setMsa3(false);
    this.setMsa4(false);
    this.setMsb1(false);
    this.setMsb2(false);
    this.setMsb3(false);
    this.setMsb4(false);
    this.setMsc1(false);
    this.setMsc2(false);
    this.setMsc3(false);
    this.setMsc4(false);
    this.setMsd1(false);
    this.setMsd2(false);
    this.setMsd3(false);
    this.setMsd4(false);
    this.setMse1(false);
    this.setMse2(false);
    this.setMsf1(false);
    this.setMsf2(false);
    this.setMsg1(false);
    this.setMsg2(false);
    this.setMsh1(false);
    this.setMsh2(false);
    this.setMsi1(false);
    this.setMsi2(false);
    this.setMsj1(false);
    this.setMsj2(false);
    this.setSsa1(false);
    this.setSsa2(false);
    this.setSsa3(false);
    this.setSsb1(false);
    this.setSsb2(false);
    this.setSsc1(false);
    this.setSsc2(false);
  }

  public boolean isMsd4 ()
  {
    return msd4;
  }

  public boolean isMsd3 ()
  {
    return msd3;
  }

  public boolean isMsd2 ()
  {
    return msd2;
  }

  public void setMsd2 (boolean msd2)
  {
    this.msd2 = msd2;
  }

  public void setMsd3 (boolean msd3)
  {
    this.msd3 = msd3;
  }

  public void setMsd4 (boolean msd4)
  {
    this.msd4 = msd4;
  }

  public boolean isSsb2 ()
  {
    return ssb2;
  }

  public void setSsb2 (boolean ssb2)
  {
    this.ssb2 = ssb2;
  }

  public boolean isMsc4 ()
  {
    return msc4;
  }

  public boolean isMsc3 ()
  {
    return msc3;
  }

  public void setMsc3 (boolean msc3)
  {
    this.msc3 = msc3;
  }

  public void setMsc4 (boolean msc4)
  {
    this.msc4 = msc4;
  }

  public boolean isSsc2 ()
  {
    return ssc2;
  }

  public boolean isSsc1 ()
  {
    return ssc1;
  }

  public void setSsc1 (boolean ssc1)
  {
    this.ssc1 = ssc1;
  }

  public void setSsc2 (boolean ssc2)
  {
    this.ssc2 = ssc2;
  }
  public boolean isMsf2()
  {
    return msf2;
  }
  public void setMsf2(boolean msf2)
  {
    this.msf2 = msf2;
  }
  public boolean isMsh1()
  {
    return msh1;
  }
  public void setMsh1(boolean msh1)
  {
    this.msh1 = msh1;
  }
  public boolean isMsh2()
  {
    return msh2;
  }
  public void setMsh2(boolean msh2)
  {
    this.msh2 = msh2;
  }
  public boolean isMsi1()
  {
    return msi1;
  }
  public void setMsi1(boolean msi1)
  {
    this.msi1 = msi1;
  }
  public boolean isMsi2()
  {
    return msi2;
  }
  public void setMsi2(boolean msi2)
  {
    this.msi2 = msi2;
  }
  public boolean isMsj1()
  {
    return msj1;
  }
  public void setMsj1(boolean msj1)
  {
    this.msj1 = msj1;
  }
  public boolean isMsj2()
  {
    return msj2;
  }
  public void setMsj2(boolean msj2)
  {
    this.msj2 = msj2;
  }
  public boolean isSsd1() {
    return ssd1;
  }
  public void setSsd1(boolean ssd1) {
    this.ssd1 = ssd1;
  }

}