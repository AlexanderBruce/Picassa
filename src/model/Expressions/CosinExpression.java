package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;


public class CosinExpression extends ParenExpression
{
    private CosinExpression ()
    {

    }


    public CosinExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> cosin = new ArrayList<String>();
        cosin.add("cosin");
        return cosin;
    }

    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new CosinExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames);
        RGBColor toMake = results.get(0);
        return new RGBColor(Math.cos(toMake.getRed()),
                            Math.cos(toMake.getGreen()),
                            Math.cos(toMake.getBlue()));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new CosinExpression());
    }

}
