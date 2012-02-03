package model.Expressions;

import java.util.*;
import java.util.Map;
import model.RGBColor;
import model.util.ColorCombinations;


public class PlusExpression extends ParenExpression
{

    public PlusExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {

        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        return ColorCombinations.add(results.get(0), results.get(1));
    }


    private PlusExpression ()
    {

    }


    public static ExpressionFactory getFactory ()
    {

        return new ExpressionFactory(new PlusExpression());
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> plus = new ArrayList<String>();
        plus.add("plus");
        plus.add("+");

        return plus;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 2;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new PlusExpression(subExpressions);
    }

}
