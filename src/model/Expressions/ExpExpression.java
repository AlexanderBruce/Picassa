package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorCombinations;


public class ExpExpression extends ParenExpression
{

    public ExpExpression (List<Expression> subExpressions)
    {
        super(subExpressions);

    }


    private ExpExpression ()
    {

    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames);
        return ColorCombinations.exp(results.get(0), results.get(1));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new ExpExpression());
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> exp = new ArrayList<String>();
        exp.add("exp");
        exp.add("^");
        return exp;
    }

    @Override
    protected int numberOfParameters ()
    {

        return 2;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new ExpExpression(subExpressions);
    }

}
