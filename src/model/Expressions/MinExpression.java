package model.Expressions;

import java.util.*;
import model.RGBColor;
import model.util.ColorCombinations;


public class MinExpression extends UnlimitedParenExpression
{

    public MinExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }

    private MinExpression(){
        
    }

    @Override
    protected List<String> commandName ()
    {
        List<String> sum = new ArrayList<String>();
        sum.add("min");
        return sum;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {
        return new MinExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames,
                              double time)
    {
        List<RGBColor> result = evaluateSubExpressions(x,y,variableNames,time);
        RGBColor min = new RGBColor(1);
        for(RGBColor current :result){
            min = ColorCombinations.min(min, current);
        }
        return min;

    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new MinExpression());
    }

}
