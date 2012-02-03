package model.Expressions;

import java.util.*;
import model.RGBColor;
import model.util.ColorCombinations;


public class AverageExpression extends UnlimitedParenExpression
{

    public AverageExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }


    private AverageExpression ()
    {

    }


    @Override
    protected List<String> commandName ()
    {
        List<String> average = new ArrayList<String>();
        average.add("average");
        return average;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {
        return new AverageExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames,
                              double time)
    {
        List<RGBColor> result =
            evaluateSubExpressions(x, y, variableNames, time);
        RGBColor sum = new RGBColor(0);
        for (RGBColor toAdd : result)
        {
            sum = ColorCombinations.add(sum, toAdd);
        }
        return new RGBColor(sum.getRed() / numberOfParameters(),
                            sum.getGreen() / numberOfParameters(),
                            sum.getBlue() / numberOfParameters());

    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new AverageExpression());
    }

}
