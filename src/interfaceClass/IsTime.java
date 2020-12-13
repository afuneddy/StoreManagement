package interfaceClass;

import java.sql.Time;

public class IsTime {
	
	
	
	
	public IsTime(){}
	
	public static void main(String args[] )
	{		}



public boolean isTime(String time)
{

boolean retVal = false;
try
{
Time.valueOf(time);
retVal = true;
}
catch (Exception e) {}
return retVal;


}}


