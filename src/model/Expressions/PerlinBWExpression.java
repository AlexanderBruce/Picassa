package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.PerlinNoise;


public class PerlinBWExpression extends ParenExpression
{
    private PerlinBWExpression ()
    {

    }


    public PerlinBWExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> perlinBW = new ArrayList<String>();
        perlinBW.add("perlinBW");
        return perlinBW;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 2;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new PerlinBWExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames);
        return PerlinNoise.greyNoise(results.get(0), results.get(1));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new PerlinBWExpression());
    }

}
