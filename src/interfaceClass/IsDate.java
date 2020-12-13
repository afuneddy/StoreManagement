package interfaceClass;

import java.sql.Date;

public class IsDate {
	
	
	
	
	public IsDate(){}
	
	public static void main(String args[] )
	{		IsDate id = new IsDate();
System.out.println(id.isDate("2013-02-12"));
		}



public boolean isDate(String date)
{

boolean retVal = false;
try
{
Date.valueOf(date);
retVal = true;
}
catch (Exception e) {}
return retVal;


}}


