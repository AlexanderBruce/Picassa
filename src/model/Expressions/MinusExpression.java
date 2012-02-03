package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorCombinations;


public class MinusExpression extends ParenExpression
{

    public MinusExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    private MinusExpression ()
    {

    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> result = evaluateSubExpressions(x, y, variableNames, time);
        return ColorCombinations.subtract(result.get(0), result.get(1));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new MinusExpression());
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> minus = new ArrayList<String>();
        minus.add("minus");
        minus.add("-");
        return minus;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 2;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {
        return new MinusExpression(subExpressions);
    }

}
