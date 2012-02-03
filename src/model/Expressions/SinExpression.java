package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;


public class SinExpression extends ParenExpression
{
    private SinExpression ()
    {

    }


    public SinExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> sin = new ArrayList<String>();
        sin.add("sin");
        return sin;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new SinExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        RGBColor toMake = results.get(0);
        return new RGBColor(Math.sin(toMake.getRed()),
                            Math.sin(toMake.getGreen()),
                            Math.sin(toMake.getBlue()));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new SinExpression());
    }

}
