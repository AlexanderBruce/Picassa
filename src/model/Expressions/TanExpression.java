package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;


public class TanExpression extends ParenExpression
{
    private TanExpression ()
    {

    }


    public TanExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> tan = new ArrayList<String>();
        tan.add("tan");
        return tan;
    }

    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new TanExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        RGBColor toMake = results.get(0);
        return new RGBColor(Math.tan(toMake.getRed()),
                            Math.tan(toMake.getGreen()),
                            Math.tan(toMake.getBlue()));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new TanExpression());
    }

}
