package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorCombinations;


public class MulExpression extends ParenExpression
{

    public MulExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    private MulExpression ()
    {

    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        return ColorCombinations.multiply(results.get(0), results.get(1));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new MulExpression());
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> mul = new ArrayList<String>();
        mul.add("mul");
        mul.add("*");
        return mul;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 2;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new MulExpression(subExpressions);
    }

}
