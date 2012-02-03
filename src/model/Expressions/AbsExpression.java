package model.Expressions;

import java.util.*;
import java.util.Map;
import model.RGBColor;


public class AbsExpression extends ParenExpression
{
    private AbsExpression ()
    {

    }


    public AbsExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> abs = new ArrayList<String>();
        abs.add("abs");
        return abs;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new AbsExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        RGBColor toMake = results.get(0);
        return new RGBColor(Math.abs(toMake.getRed()),
                            Math.abs(toMake.getGreen()),
                            Math.abs(toMake.getBlue()));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new AbsExpression());
    }

}
