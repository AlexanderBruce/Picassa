package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;


public class WrapExpression extends ParenExpression
{
    private WrapExpression ()
    {

    }


    public WrapExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> wrap = new ArrayList<String>();
        wrap.add("wrap");
        return wrap;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new WrapExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        results.get(0).wrap();

        return results.get(0);
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new WrapExpression());
    }

}
