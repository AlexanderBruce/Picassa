package model.Expressions;

import java.util.*;
import model.RGBColor;
import model.util.ColorCombinations;


public class MaxExpression extends UnlimitedParenExpression
{

    public MaxExpression (List<Expression> subExpressions)
    {
        super(subExpressions);
    }

    private MaxExpression(){
        
    }

    @Override
    protected List<String> commandName ()
    {
        List<String> max = new ArrayList<String>();
        max.add("max");
        return max;
    }


    @Override
    protected ParenExpression constructParenExpression (List<Expression> subExpressions)
    {
        return new MaxExpression(subExpressions);
    }


    @Override
    public RGBColor evaluate (double x,
                              double y,
                              Map<String, Expression> variableNames,
                              double time)
    {
        List<RGBColor> result = evaluateSubExpressions(x,y,variableNames,time);
        RGBColor max = new RGBColor(-1);
        for(RGBColor current :result){
            max = ColorCombinations.max(max, current);
        }
        return max;

    }


    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new MaxExpression());
    }

}