package model.Expressions;

import java.util.Map;
import java.util.regex.Pattern;
import model.Parser;
import model.RGBColor;


public class TimeExpression extends Expression
{
    private static final Pattern MYKINDOFEXPRESSION = Pattern.compile("t");



    public TimeExpression ()
    {

    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variables, double time)
    {
        return new RGBColor(time*2 -1);
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
        parser.updatePosition(ONE);
        return new TimeExpression();
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new TimeExpression());
    }


}