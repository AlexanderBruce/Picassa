package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorCombinations;


public class NegExpression extends ParenExpression
{

    public NegExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    private NegExpression ()
    {

    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new NegExpression());
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> results = evaluateSubExpressions(x, y, variableNames, time);
        return ColorCombinations.invert(results.get(0));
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> neg = new ArrayList<String>();
        neg.add("neg");
        neg.add("!");
        return neg;
    }


    @Override
    protected int numberOfParameters ()
    {
        return 1;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new NegExpression(subExpressions);
    }
}
