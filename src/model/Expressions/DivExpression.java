package model.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.RGBColor;
import model.util.ColorCombinations;


public class DivExpression extends ParenExpression
{

    public DivExpression (List<Expression> subExpressions)
    {
        super(subExpressions);

    }


    private DivExpression ()
    {

    }


    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames, double time)
    {
        List<RGBColor> result = evaluateSubExpressions(x, y, variableNames, time);
        return ColorCombinations.divide(result.get(0), result.get(1));
    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new DivExpression());
    }


    @Override
    protected List<String> commandName ()
    {
        List<String> div = new ArrayList<String>();
        div.add("div");
        div.add("/");
        return div;
    }


    @Override
    protected int numberOfParameters ()
    {

        return 2;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {

        return new DivExpression(subExpressions);
    }

}
