package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;


public class ClampExpression extends ParenExpression
{
    private ClampExpression ()
    {

    }


    public ClampExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> clamp = new ArrayList<String>();
        clamp.add("clamp");
        return clamp;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new ClampExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        results.get(0).clamp();
        return results.get(0);
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new ClampExpression());
    }

}
