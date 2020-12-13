package interfaceClass;




public class IntegerInput
{
public IntegerInput()
{

}

public boolean isInteger(String input)
{
boolean retVal = false;
try
{
Integer.parseInt(input);
retVal = true;
}
catch (Exception e) {}
return retVal;
}}
