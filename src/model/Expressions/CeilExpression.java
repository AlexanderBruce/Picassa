package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;


public class CeilExpression extends ParenExpression
{
    private CeilExpression ()
    {

    }


    public CeilExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> ceil = new ArrayList<String>();
        ceil.add("ceil");
        return ceil;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new CeilExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames);
        RGBColor toMake = results.get(0);
        return new RGBColor(Math.ceil(toMake.getRed()),
                            Math.ceil(toMake.getGreen()),
                            Math.ceil(toMake.getBlue()));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new CeilExpression());
    }

}
