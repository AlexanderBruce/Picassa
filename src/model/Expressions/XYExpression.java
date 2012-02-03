package model.Expressions;

import java.util.Map;
import java.util.regex.Pattern;
import model.Parser;
import model.RGBColor;


public class XYExpression extends Expression
{
    private static final Pattern MYKINDOFEXPRESSION = Pattern.compile("x|y");
    private String myXOrY;


    public XYExpression (String xOrY)
    {
        myXOrY = xOrY;
    }


    private XYExpression ()
    {

    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variables)
    {
        if (myXOrY.equals("x")) return new RGBColor(x, x, x);
        return new RGBColor(y, y, y);
    }


    @Override
    public boolean isThisKindOfExpression (Parser parser)
    {
        parser.skipWhiteSpace();
        if (parser.checkPattern(MYKINDOFEXPRESSION))
        {

            return true;
        }

        return false;
    }


    @Override
    public Expression parseExpression (Parser parser)
    {

        parser.skipWhiteSpace();
        String result = "" + parser.currentCharacter();
        parser.updatePosition(ONE);
        return new XYExpression(result);
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new XYExpression());
    }
    
    public String toString()
    {
        return myXOrY;
    }

}
