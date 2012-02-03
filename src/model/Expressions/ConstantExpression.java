package model.Expressions;

import java.util.Map;
import java.util.regex.Pattern;
import model.Parser;
import model.ParserException;
import model.RGBColor;


public class ConstantExpression extends Expression
{
    private static final Pattern MYKINDOFEXPRESSION =
        Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");
    private RGBColor myValue;


    public ConstantExpression (RGBColor value)
    {
        myValue = value;
    }


    private ConstantExpression ()
    {

    };


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        return myValue;
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new ConstantExpression());
    }


    @Override
    public boolean isThisKindOfExpression (Parser parser)
    {

        parser.skipWhiteSpace();

        return parser.checkPattern(MYKINDOFEXPRESSION);
    }


    @Override
    public Expression parseExpression (Parser parser)
    {
        String result = parser.grabExpressionKey(MYKINDOFEXPRESSION);

        try
        {
            double value = Double.parseDouble(result);
            RGBColor gray = new RGBColor(value);
            parser.updatePosition(result.length());
            return new ConstantExpression(gray);
        }

        catch (NullPointerException e)
        {
            throw new ParserException("Please place a 0 before the decimal");
        }

    }

}
